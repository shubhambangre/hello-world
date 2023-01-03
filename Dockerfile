FROM tomcat:8.0-alpine

LABEL maintainer=”shubhamb”

ADD  ./target/hello-world.war /usr/local/tomcat/webapps

EXPOSE 8000

CMD [“/usr/local/tomcat catalina.sh”, “run”]
CMD ["/bin/bash", "-c", "sleep 1500"]
