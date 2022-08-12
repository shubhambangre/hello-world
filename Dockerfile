FROM openjdk:8
COPY ./target/hello-world.war /usr/local/tomcat/webapps
EXPOSE 8080
ENTRYPOINT ["/usr/sbin/init, "run"]
CMD ["/usr/local/tomcat/bin/catalina.sh","run"]



