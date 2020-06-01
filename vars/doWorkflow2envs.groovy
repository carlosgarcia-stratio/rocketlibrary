#!groovy

def call(Map props = [:]) {

    node {
        println("Rocket flow for workflow 2 envs")


        stage('Checkout Workflow') {
            sh 'Checkwork workflow'
        }

        stage('QA') {
            sh 'QA'
        }

        stage('Deploy') {
            sh 'Deploy'
        }
    }


}
