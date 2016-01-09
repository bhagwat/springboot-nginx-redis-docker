FROM java:8
MAINTAINER Bhagwat Kumar "bhagwat.kumar@tothenew.com"
#Mount /tmp folder for Tomcat temp files
VOLUME ["/tmp"]
#Add latest build jar file
ADD springboot-redis-0.1.0.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8080
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]