pipeline {
    agent any
    tools {
        maven 'maven'
    }
    stages {
        stage ('Build Maven'){
            steps{
             sh 'mvn clean install'
            }

        }
        stage ("deploy project to tomcat") {
            steps {
                script {
                    sh 'sudo sshpass -p \'123\' scp /var/lib/jenkins/workspace/hello-world-multibranch_deploy/target/hello-world.war root@3.13.184.127:/usr/share/tomcat9/webapps/'
                }
            }
        }
}
}
