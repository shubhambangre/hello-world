FROM centos

MAINTAINER aksarav@middlewareinventory.com

RUN mkdir /opt/tomcat/

WORKDIR /opt/tomcat
RUN curl -O https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.65/bin/apache-tomcat-9.0.65.tar.gz
RUN gunzip apache-tomcat-9.0.65.tar.gz
RUN tar xvf apache-tomcat-9.0.65.tar
RUN mv apache-tomcat-9.0.65/* /opt/tomcat/.
RUN yum -y install java
RUN java -version

WORKDIR /opt/tomcat/webapps
ADD ./target/hello-world.war /usr/local/tomcat/webapps

EXPOSE 8080

CMD ["/opt/tomcat/bin/startup.sh", "run"]

