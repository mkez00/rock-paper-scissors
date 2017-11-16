FROM openjdk:8-jdk-alpine

ADD target/rock-paper-scissors-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENV CACHE_SERVICE="ActionRepositoryHazelcast"
ENV REDIS_URL=""
ENV REDIS_PORT="6379"

ENTRYPOINT exec java -Dcache.service=$CACHE_SERVICE -Dredis.url=$REDIS_URL -Dredis.port=$REDIS_PORT -Djava.security.egd=file:/dev/./urandom -jar /app.jar