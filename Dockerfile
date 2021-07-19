FROM openjdk:11

ARG PROPOSAL_PROFILE
ENV PROPOSAL_PROFILE=${PROPOSAL_PROFILE}

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
CMD java -jar /app.jar --spring.profiles.active=${PROPOSAL_PROFILE}