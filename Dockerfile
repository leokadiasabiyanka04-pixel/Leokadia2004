FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -B -DskipTests

CMD ["sh", "-c","java -jar target/*.jar"]