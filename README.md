# D3A Hackathon: Unified Data Analytics with Apache Wayang

[Apache Wayang (Incubating)](https://wayang.apache.org/) is the first
open-source framework that unifies data systems alleviating users from
the need of system selection. It decouples applications from platforms
so that they can run analytics on one or more platforms seamlessly and
efficiently.

## Clone repository
Clone this repository in your preferred location using this command:
```shell
git clone https://github.com/juripetersen/d3a-hackathon-wayang.git
```
Go to the root directory:
```shell
cd d3a-hackathon-wayang
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

Make sure that docker is up and running.

### Step 1: Building the container
In order to create the container, we need to build and start it:

```shell
docker compose up -d --build
```
Note: In case of Apple Silicon M1 Chips, please edit the docker-compose.yaml file and change the image type to apache/incubator-wayang:0.7.1-arm64

### Step 2: Connecting to the app container

In order to get an interactive bash session that allows running commands
inside of the app container, run the following:

```shell
docker exec -it apache-wayang-app bash
```

## Install
Within the root directory (/var/www/html in our container),
run the following command to install all required packages, including Wayang:

```shell
mvn clean install
```

## Examples
To run any of the following example applications, use this format:

```shell
mvn exec:java -Dexec.mainClass="org.apache.wayang.hackathon.Main" -Dexec.args="java,spark file://$(pwd)/README.md"
```

Replace `<arg1> <arg2>, ...` with the application-specific parameters that you want to use.

### WordCount
**Description.** This app takes a text input file and counts the number occurrences of each word in the text. This simple app has become some sort of _"Hello World"_ program for data processing systems.
**Running the app.** To run the app, launch the main class:

```shell

```
Even though this app is written in Scala, you can launch it in a regular JVM. Run the app without parameters to get a description of the required parameters.

## Tasks
We prepared tasks that can be found in [TASKS.md](TASKS.md)
