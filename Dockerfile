FROM openjdk:11
ADD /target/*.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]