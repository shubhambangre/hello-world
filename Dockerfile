FROM centos

MAINTAINER aksarav@middlewareinventory.com

RUN mkdir /opt/tomcat/

WORKDIR /opt/tomcat
RUN curl -O curl -O https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.63/bin/apache-tomcat-9.0.63.tar.gz
RUN gunzip -f apache-tomcat-9.0.63.tar.gz
RUN tar -xvfz apache-tomcat-9.0.63.tar
RUN mv apache-tomcat-9.0.63/* /opt/tomcat/.
RUN yum -y install java
RUN java -version

WORKDIR /opt/tomcat/webapps
ADD ./target/hello-world.war /usr/local/tomcat/webapps

EXPOSE 8080

CMD ["/opt/tomcat/bin/catalina.sh", "run"]




