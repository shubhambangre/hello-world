FROM tomcat:latest
LABEL maintainer=hello
ADD ./target/hello-world.war /usr/share/tomcat/webapps/
EXPOSE 9090
CMD ["catalina.sh","run"]

