FROM redhat/ubi8

MAINTAINER aksarav@middlewareinventory.com

RUN mkdir /opt/tomcat/

WORKDIR /opt/tomcat
RUN curl -O https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.34/bin/apache-tomcat-9.0.1.tar.gz
RUN gunzip apache-tomcat-9.0.1.tar.gz
RUN tar xvf apache-tomcat-9.0.1.tar
RUN mv apache-tomcat-9.0.1/* /opt/tomcat/.
RUN yum -y install java
RUN java -version

WORKDIR /opt/tomcat/webapps
ADD ./target/hello-world.war /usr/local/tomcat/webapps

EXPOSE 8080

CMD ["/opt/tomcat/bin/catalina.sh", "run"]




