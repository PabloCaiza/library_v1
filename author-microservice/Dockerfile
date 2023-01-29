FROM eclipse-temurin:17.0.5_8-jre-alpine
RUN mkdir /app
WORKDIR /app
COPY build/author-microservice-1.0.0-SNAPSHOT-runner.jar app.jar
CMD ["java","-jar","app.jar"]