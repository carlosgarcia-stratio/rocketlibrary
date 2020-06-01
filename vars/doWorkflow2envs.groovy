#!groovy

def call(Map props = [:]) {

    node("maven") {
        println("Rocket flow for workflow 2 envs")


        stage('Checkout Workflow') {
            sh 'mvn --version'
        }

        stage('QA') {
            sh 'echo "QA"'
        }

        stage('Deploy') {
            sh 'echo "Deploy"'
        }
    }


}
