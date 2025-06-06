FROM maven:3.9.6-eclipse-temurin as build
WORKDIR /app
LABEL authors="eduardomarques"
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ARG AWS_ACCESS_KEY_ID
ARG AWS_SECRET_ACCESS_KEY

ENV AWS_REGION=us-east-1
ENV AWS_S3_BUCKET=testes-portifolio-bucket

ENTRYPOINT ["java", "-jar", "app.jar"]
