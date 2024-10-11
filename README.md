# D3A Hackathon: Unified Data Analytics with Apache Wayang

[Apache Wayang (Incubating)](https://wayang.apache.org/) is the first
open-source framework that unifies data systems alleviating users from
the need of system selection. It decouples applications from platforms
so that they can run analytics on one or more platforms seamlessly and
efficiently.

## Installing Wayang
Download from Apache Wayang into this folder from
[GitHub](https://github.com/apache/incubator-wayang) using the following
command:

```shell
git clone https://github.com/apache/incubator-wayang.git
```

## Docker
For ease of use, we recommend installing
[Docker](https://www.docker.com/products/docker-desktop/), as it allows
to pull a Docker Container that comes with all of Apache Wayangs
required dependencies. Additionally, we utilize
[Docker Compose](https://docs.docker.com/compose/install/) to start our
container with all options in one command.
We provide a [pre-built docker image](https://hub.docker.com/r/apache/incubator-wayang)
that contains the necessary tooling in order to run or develop Apache Wayang.
The tools necessary for this are:
    - Java 11
    - Apache Spark
    - Hadoop
    - Maven

### Step 1: Building the container
In order to create the container, we need to build and start it:

```shell
docker compose up -d --build
```

### Step 2: Connecting to the app container

In order to get a interactive bash session that allows running commands
inside of the app container, run the following:

```shell
docker exec -it apache-wayang-app bash
```

## Tooling
Within the root directory of Wayang (/var/www/html in our container),
run the following command to install all packages in Wayang:
```shell
mvn clean install -DskipTests
```

Packaging the project to build the executable:
```shell
mvn clean package -pl :wayang-assembly -Pdistribution
```

Execute your code:
```shell
cd wayang-assembly/target/
tar -xvf apache-wayang-assembly-0.7.1-incubating-dist.tar.gz
cd wayang-0.7.1
./bin/wayang-submit org.apache.wayang.<main_class> <parameters>
```

## Examples
To run any of the following example applications, use this format:

```shell
<main class> exp(<ID>[,tags=<tag>,...][,conf=<key>:<value>,...]) <plugin>(,<plugin>)* <arg1> <arg2> ...
```

Replace `<arg1> <arg2>, ...` with the application-specific parameters that you want to use.

### WordCount
**Description.** This app takes a text input file and counts the number occurrences of each word in the text. This simple app has become some sort of _"Hello World"_ program for data processing systems.

**Running the app.** To run the app, launch the main class:
```java
org.apache.wayang.apps.wordcount.WordCountScala
```

```bash
./bin/wayang-submit org.apache.wayang.apps.wordcount.WordCountScala exp\(123\) java file://$(pwd)/README.md
```

Even though this app is written in Scala, you can launch it in a regular JVM. Run the app without parameters to get a description of the required parameters.

### Stocks
