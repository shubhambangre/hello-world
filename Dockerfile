FROM tomcat
COPY hello-world.war /usr/local/tomcat/webapps
CMD ["catalina.sh","run"]
ENTRYPOINT /usr/bin/init
