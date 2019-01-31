# Spring Boot Hello World

A spring boot enabled hello world application

[![Build Status](https://travis-ci.org/gazgeek/springboot-helloworld.svg?branch=master)](https://travis-ci.org/gazgeek/springboot-helloworld)

[![Coverage Status](https://coveralls.io/repos/gazgeek/springboot-helloworld/badge.svg)](https://coveralls.io/r/gazgeek/springboot-helloworld)

- Travis CI build and test
- Continuous deployment to Heroku on success

## Usage

- Directly using maven
```
mvn spring-boot:run
```

- From within your IDE right click run 
```
Application.java
```

- From executable jar file
```
mvn clean install
java -jar target/helloworld-0.0.1-SNAPSHOT.jar
```

- To run this as a docker application (assumption docker is installed on your machine)
```
docker pull gazgeek/springboot-helloworld
docker container run -p 8080:8080 -d gazgeek/springboot-helloworld

Go to Browser and type http://localhost:8080/ or do curl http://localhost:8080/ on command prompt
```



