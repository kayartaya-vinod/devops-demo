pipeline {
    agent any

    environment {
        ORG_NAME = "learnwithvinod"
        APP_NAME = "devops-demo"
        APP_VERSION = "0.0.1-SNAPSHOT"
        APP_CONTEXT_ROOT = "/"
        APP_LISTENING_PORT = "8888"
        TEST_CONTAINER_NAME = "ci-${APP_NAME}-${BUILD_NUMBER}"
        DOCKER_HUB = credentials("${ORG_NAME}-docker-hub")
    }

    stages{

        stage('compile'){
            steps {
                echo 'compiling...'
                sh 'mvn clean compile'
            }
        }

        stage('unit test'){
            steps {
                echo 'unit tests...'
                sh 'mvn test'
            }
        }

        stage('build'){
            steps {
                echo 'creating jar...'
                sh 'mvn package -DskipTests=true'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('dockerize'){
            steps {
                echo 'creating docker image...'
                sh "docker build -t ${ORG_NAME}/${APP_NAME}:${APP_VERSION} -t ${ORG_NAME}/${APP_NAME}:latest ."
            }
        }

        stage('performance test'){
            steps {
                echo 'testing for performance...'
                sh "jmeter -jjmeter.save.saveservice.output_format=xml -n -t ./devops-demo.jmx -l ./target/devops-demo.jtl"
                archiveArtifacts artifacts: 'target/*.jtl', fingerprint: true
            }
        }

        stage('quality gate'){
            steps {
                echo 'checking for quality...'
            }
        }

        stage('publish docker image'){
            steps {
                echo 'publishing docker image to docker repository...'
            }
        }
    }
}