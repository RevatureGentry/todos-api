FROM maven:3.6.1-jdk-8 as builder
WORKDIR /app
COPY . .
RUN mvn clean package

FROM gcr.io/distroless/java:8
COPY --from=builder /app/target /app
WORKDIR /app
CMD ["todos-api-1.0.jar"]
