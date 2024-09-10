FROM maven:3.8.5-openjdk-17-slim AS builder

RUN mkdir -p /app/auth-service

WORKDIR /app
COPY ./ /app/auth-service/

WORKDIR /app/auth-service
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:17-ea-jdk-oracle

WORKDIR /app
COPY --from=builder /app/auth-service/target/belajarkotlin-0.0.1-SNAPSHOT.jar .

EXPOSE 8080/tcp

ENTRYPOINT ["java","-jar","belajarkotlin-0.0.1-SNAPSHOT.jar"]