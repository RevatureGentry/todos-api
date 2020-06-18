FROM maven:3.6.1-jdk-8 as builder
WORKDIR /usr/src/app
COPY . .
RUN mvn clean package
COPY target/todos-api-0.0.1-SNAPSHOT.jar /todos.jar

FROM gcr.io/distroless/java:8
COPY --from=builder /todos.jar /todos.jar
CMD ["todos.jar"]
