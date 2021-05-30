FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-slim
WORKDIR /work
COPY entryPoint.sh /work/
RUN chmod +x /work/*.sh
EXPOSE 8080
COPY target/*.jar /work/app.jar
ENTRYPOINT ["/work/entryPoint.sh"]