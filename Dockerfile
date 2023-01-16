FROM eclipse-temurin:17.0.5_8-jre
COPY build/libs/smybteapi-*-all.jar /smybteapi.jar

USER 1000:1000

ENV SMYBTEAPI_INTERFACE=0.0.0.0
ENV SMYBTEAPI_PORT=8080

CMD java -jar /smybteapi.jar