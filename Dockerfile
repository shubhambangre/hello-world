FROM openjdk 8
COPY ./target/hello-world.war /usr/local/tomcat/webapps
EXPOSE 8080
CMD ["catalina.sh","run"]

