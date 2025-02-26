FROM openjdk:17-jdk-slim
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN ./mvnw clean package -DskipTests
COPY target/spring-api-mcp-client.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]