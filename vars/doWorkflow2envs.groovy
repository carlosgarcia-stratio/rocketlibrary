#!groovy

def call(Map props = [:]) {

    node {
        println("Rocket flow for workflow 2 envs")


        stage('Checkout Workflow') {
            sh 'echo "Checkout workflow"'
        }

        stage('QA') {
            sh 'echo "QA"'
        }

        stage('Deploy') {
            sh 'echo "Deploy"'
        }
    }


}
