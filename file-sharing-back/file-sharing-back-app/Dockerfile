FROM java:8-jdk-alpine

RUN sh -c 'mkdir -p /usr/app'

RUN sh -c 'export SPRING_PROFILES_ACTIVE=dev'

COPY ./target/file-sharing-back-app-0.0.1-SNAPSHOT.jar /usr/app

WORKDIR /usr/app

RUN sh -c 'touch file-sharing-back-app-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "file-sharing-back-app-0.0.1-SNAPSHOT.jar"]