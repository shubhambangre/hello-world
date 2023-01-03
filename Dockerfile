FROM tomcat:8.0-alpine

LABEL maintainer=”shubhamb”

ADD  /target/hello-world.war /usr/share/tomcat/webapps

EXPOSE 8080

CMD [“/usr/share/tomcat/catalina.sh”, “run”]

