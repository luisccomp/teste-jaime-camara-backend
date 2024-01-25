FROM eclipse-temurin:17 as builder

WORKDIR /usr/app

RUN apt update -y && \
    apt upgrade -y && \
    apt install -y maven

COPY . .

RUN mvn clean install

FROM eclipse-temurin:17

WORKDIR /usr/app

COPY --from=builder /usr/app/target/*.jar ./app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
