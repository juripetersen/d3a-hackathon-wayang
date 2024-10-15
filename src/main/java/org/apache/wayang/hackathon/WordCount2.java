package org.apache.wayang.hackathon;

import org.apache.wayang.api.JavaPlanBuilder;
import org.apache.wayang.basic.data.Tuple2;
import org.apache.wayang.core.api.Configuration;
import org.apache.wayang.core.api.WayangContext;
import org.apache.wayang.java.Java;
import org.apache.wayang.spark.Spark;

import java.util.Arrays;
import java.util.Collection;

public class WordCount2 {

    public static void main(String[] args){

        /* Get a plan builder */
        WayangContext wayangContext = new WayangContext(new Configuration())
                .withPlugin(Java.basicPlugin())
                .withPlugin(Spark.basicPlugin());
        JavaPlanBuilder planBuilder = new JavaPlanBuilder(wayangContext)
                .withJobName("WordCount")
                .withUdfJarOf(WordCount2.class);

        /* Start building the Apache WayangPlan */
        Collection<Tuple2<String, Integer>> wordcounts = planBuilder
                /* Read the text file */
                .readTextFile("file:///var/www/html/src/main/resources/input/test.txt")
                /* Split each line by non-word characters */
                .flatMap(line -> Arrays.asList(line.split("\\W+")))
                /* Filter empty tokens */
                .filter(token -> !token.isEmpty())
                .withTargetPlatform(Spark.platform())
                /* Attach counter to each word */
                .map(word -> new Tuple2<>(word.toLowerCase(), 1)).withName("To lower case, add counter")
                // Sum up counters for every word.
                .reduceByKey(
                        Tuple2::getField0,
                        (t1, t2) -> new Tuple2<>(t1.getField0(), t1.getField1() + t2.getField1())
                )
                /* Execute the plan and collect the results */
                .collect();

        System.out.println(wordcounts);
    }
}

