FROM openjdk:8
EXPOSE 8080
ADD target/hello-world.war hello-world.war  
ENTRYPOINT ["java","-war","/hello-world.war ."]
