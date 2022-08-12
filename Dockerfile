FROM openjdk:8
COPY ./target/hello-world.war /usr/local/tomcat/webapps
EXPOSE 8080
ENTRYPOINT ["/usr/sbin/init", "run"]
CMD ["/usr/share/apache-tomcat-9.0.63/bin/catalina.sh", "run"]




