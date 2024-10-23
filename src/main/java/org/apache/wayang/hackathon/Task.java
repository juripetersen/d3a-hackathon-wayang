
package org.apache.wayang.hackathon;

import org.apache.wayang.api.JavaPlanBuilder;
import org.apache.wayang.basic.data.Record;
import org.apache.wayang.basic.data.Tuple2;
import org.apache.wayang.core.api.Configuration;
import org.apache.wayang.core.api.WayangContext;
import org.apache.wayang.core.optimizer.cardinality.FixedSizeCardinalityEstimator;
import org.apache.wayang.core.platform.Platform;
import org.apache.wayang.spark.Spark;
import org.apache.wayang.java.Java;

import java.util.Arrays;
import java.util.Collection;

public class Task {
    public static void main(String[] args){

        /* Get a plan builder */
        WayangContext wayangContext = new WayangContext(new Configuration())
                .withPlugin(Spark.basicPlugin())
                .withPlugin(Java.basicPlugin());
        JavaPlanBuilder planBuilder = new JavaPlanBuilder(wayangContext)
                .withJobName("Task")
                .withUdfJarOf(Task.class);

        /* Start building the Apache Wayang Plan */
        Collection<Record> output = planBuilder
                /* Read the text file */
                .readTextFile("file:///var/www/html/src/main/resources/input/Stock_Prices_2022.csv")
                .map(line -> {
                    String [] vals = line.split("\\,");
                    return new Record(vals[0], vals [1], Double.parseDouble(vals[2]), 1);
                })
                /**
                 * Fill in your way of parsing data
                 */
                .collect();

        /* Print out the results */
        System.out.println(output);
    }
}
