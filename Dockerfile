FROM akshpawardocker/tomcat9.0.63:latest
LABEL maintainer="shubham"
RUN mvn clean install
COPY /target/hello-world.war /usr/share/tomcat/webapps/
COPY /target/hello-world.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["/usr/share/tomcat/bin/catalina.sh", "run"] 

