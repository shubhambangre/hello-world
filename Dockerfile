FROM tomcat.9:latest
LABEL maintainer="shubham"
COPY ./target/hello-world.war /usr/local/tomcat/webapps
EXPOSE 8000
CMD ["catalina.sh","run"]
