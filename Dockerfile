FROM tomcat:8.0-alpine

LABEL maintainer=”kirtb”

ADD  ./target/hello-world.war /usr/local/tomcat/webapps

EXPOSE 8080

CMD [“catalina.sh”, “run”]
CMD ["/bin/bash", "-c", "sleep 1500"]
