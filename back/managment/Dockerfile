FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} prestabanco-management.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/prestabanco-management.jar"]