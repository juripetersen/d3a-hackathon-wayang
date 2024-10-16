# D3A Hackathon: Unified Data Analytics with Apache Wayang
Example solutions to the tasks in this document can be found in the
[source code](./src/main/java/org/apache/wayang/hackathon)

## WordCount To ensure your instance of Apache Wayang is operational,
try to compile and execute one of the WordCount examples.
We provide [two examples](./src/main/java/org/apache/wayang/hackathon)
with fixed files as input and another example that computes the
wordcount for any file you wish to give.

### Compiling
In your docker container, run:
```shell
mvn clean install
```

### Running fixed input WordCount
```shell
mvn exec:java -Dexec.mainClass="org.apache.wayang.hackathon.WordCount"
```

### Running dynamic input WordCount
```shell
mvn exec:java -Dexec.mainClass="org.apache.wayang.hackathon.Main" -Dexec.args="java,spark file://$(pwd)/README.md"
```

## FinanceQuery

Given a [file of stock prices](./src/main/resources/input/Stock_Prices_2022.csv),
calculate the average value of each stock for the year 2022.
You can decide to either collect your results into a Collection by
invoking a `CollectionSink` with `.collect()`, or to use a `FileSink`
and write it to a .txt or .csv file.

### Writing to File
On an instantiated operator, call:

```java
.writeTextFile("file:///tmp/out.txt", s => s.toString())
```

