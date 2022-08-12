FROM tomcat:8.0-jre8
COPY ./target/hello-world.war /usr/local/tomcat/webapps
EXPOSE 8080
CMD ["catalina.sh","run"]

