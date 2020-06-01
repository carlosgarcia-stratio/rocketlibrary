#!groovy

def call(Map props = [:]) {

    node("rocket") {

        println("Rocket flow for workflow 2 envs")

        stage('Checkout Workflow') {
            sh 'mvn com.stratio.rocket:rocket-maven-plugin:0.1.0-SNAPSHOT:checkout -DrocketBaseUrl=http://localhost:9090 -Dcookie="" -DassetId="a6e5049e-87b2-4cad-ae09-0150f2f56a95"'
        }

        stage('QA') {
            sh 'echo "QA"'
        }

        stage('Deploy') {
            sh 'echo "Deploy"'
        }
    }


}
