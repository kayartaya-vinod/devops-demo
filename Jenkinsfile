pipeline {
    agent any

    environment {
        ORG_NAME = "learnwithvinod"
        APP_NAME = "devops-demo"
        APP_VERSION = "0.0.1-SNAPSHOT"
        APP_CONTEXT_ROOT = "/"
        APP_LISTENING_PORT = "8888"
        TEST_CONTAINER_NAME = "ci-${APP_NAME}-${BUILD_NUMBER}"
        DOCKER_REGISTRY_CREDENTIALS = credentials("ecr-credentials")
        DOCKER_REGISTRY_URL = "153294646920.dkr.ecr.us-east-1.amazonaws.com/asde-batch5"
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
                junit 'target/surefire-reports/*.xml'
                jacoco execPattern: 'target/jacoco.exec'
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
                sh "docker build -t ${APP_NAME}:${APP_VERSION} -t ${APP_NAME}:latest ."
            }
        }

        // stage('docker test container'){
        //     steps {
        //         echo 'booting up docker test container...'
        //         sh "docker run -dp ${APP_LISTENING_PORT}:${APP_LISTENING_PORT} --name ${TEST_CONTAINER_NAME} --rm ${APP_NAME}:latest"
        //     }
        // }

        // stage('performance test'){
        //     steps {
        //         echo 'testing for performance...'
        //         sh "jmeter -n -t ./devops-demo.jmx -l ./target/devops-demo.jtl"
        //         archiveArtifacts artifacts: 'target/*.jtl', fingerprint: true
        //     }
        // }

    //    stage('Code inspection & quality gate') {
    //        steps {
    //            echo "run code inspection & check quality gate..."
    //            withSonarQubeEnv('ci-sonarqube') {
    //                sh "mvn sonar:sonar"
    //            }
    //            timeout(time: 10, unit: 'MINUTES') {
    //                //waitForQualityGate abortPipeline: true
    //                script  {
    //                    def qg = waitForQualityGate()
    //                    if (qg.status != 'OK' && qg.status != 'WARN') {
    //                        error "Pipeline aborted due to quality gate failure: ${qg.status}"
    //                    }
    //                }
    //            }
    //        }
    //    }

        stage('publish docker image'){
            // when {
            //     branch 'origin/release'
            // }
            steps {
                echo 'publishing docker image to docker repository...'
                withDockerRegistry([ 
                    $class: 'AmazonWebServicesCredentialsBinding',
                    accessKeyVariable: 'AWS_ACCESS_KEY_ID',
                    secretKeyVariable: 'AWS_SECRET_ACCESS_KEY',
                    credentialsId: "${DOCKER_REGISTRY_CREDENTIALS}", 
                    url: "${DOCKER_REGISTRY_URL}" 
                    ]) {
                    sh "docker push ${APP_NAME}:${APP_VERSION}"
                    sh "docker push ${APP_NAME}:latest"
                }
            }
        }
    }
    post {
        always {
            echo "removing docker test container..."
            // sh "docker stop ${TEST_CONTAINER_NAME}"
            echo "removing the workspace"
            cleanWs()
        }
    }
}