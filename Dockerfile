FROM openjdk:25-oraclelinux8

COPY target/Paul-API.jar /apapun/Paul-API.jar
CMD ["java","-jar","/apapun/Paul-API.jar"]