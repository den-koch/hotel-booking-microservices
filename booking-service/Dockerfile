
FROM openjdk:17-jdk-alpine AS builder

RUN apk --no-cache add maven

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:resolve

COPY src ./src
RUN mvn clean package -DskipTests


FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar booking-service.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "booking-service.jar"]

