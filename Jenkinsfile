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
                    deploy adapters: [tomcat9(credentialsId: 'c97c4111-21b7-498e-8bcf-8a144cc73273', path: '', url: 'http://3.13.184.127:8080/')], contextPath: null, war: '**/*.war'
                }
            }
        }
}
}
