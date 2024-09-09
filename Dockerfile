FROM eclipse-temurin:17-jdk

COPY build/libs/nttdata-app-clientPerson-backend-0.0.1-SNAPSHOT.jar nttdata-clientPerson-wscc-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","nttdata-clientPerson-ws-0.0.1-SNAPSHOT.jar"]