#!groovy

def call(Map props = [:]) {

    node("rocket") {

        println("Rocket flow for AutoML 2 envs")

        def sleep_time = 4
        def ROCKET_URL = props["ROCKET_API_URL_DEV"]
        def ROCKET_COOKIE = ""
        def ROCKET_URL_PROD = props["ROCKET_API_URL_PRO"]
        def ROCKET_COOKIE_PROD = ""
        def ASSET_VERSION_ID = props["assetVersionId"]
        def RELEASE_ID = props["releaseId"]

        stage('Init release') {
            sh "mvn com.stratio.rocket:rocket-maven-plugin:0.1.0-SNAPSHOT:init -DrocketBaseUrl=$ROCKET_URL -Dcookie=$ROCKET_COOKIE -DreleaseId=$RELEASE_ID -DassetVersionId=$ASSET_VERSION_ID -DbuildUrl=$BUILD_URL"
        }

        sleep(time:sleep_time, unit:"SECONDS")

        stage('Finalize release') {
            sh "mvn com.stratio.rocket:rocket-maven-plugin:0.1.0-SNAPSHOT:finish -DrocketBaseUrl=$ROCKET_URL -Dcookie=$ROCKET_URL -DreleaseId=$RELEASE_ID"
        }

    }

}
