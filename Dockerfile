FROM centos

MAINTAINER aksarav@middlewareinventory.com

RUN mkdir /opt/tomcat/

WORKDIR /opt/tomcat
RUN curl -o https://tomcat.apache.org/download-90.cgi/apache-tomcat-9.0.65-src.tar.gz
RUN tar xvzf apache-tomcat-9.0.65-src.tar.gz
RUN mv apache-tomcat-9.0.65/* /opt/tomcat/.
RUN yum -y install java
RUN java -version

WORKDIR /opt/tomcat/webapps
ADD ./target/hello-world.war /usr/local/tomcat/webapps

EXPOSE 8080

CMD ["/opt/tomcat/bin/catalina.sh", "run"]




