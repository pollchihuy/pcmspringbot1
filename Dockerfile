FROM khipu/openjdk21-alpine

COPY target/Paul-API.jar /apapun/Paul-API.jar
CMD ["java","-jar","/apapun/Paul-API.jar"]