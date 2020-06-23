#!groovy

def call(Map props = [:]) {

    node("rocket") {

        println("Rocket flow for MLProject 2 envs")

        def sleep_time = 4
        def ROCKET_URL = props["ROCKET_API_URL_DEV"]
        def ROCKET_COOKIE = ""
        def ROCKET_URL_PROD = props["ROCKET_API_URL_PRO"]
        def ROCKET_COOKIE_PROD = ""
        def ASSET_VERSION_ID = props["assetVersionId"]
        def RELEASE_ID = props["releaseId"]
        def ARCHIVE_PATH = "${BUILD_TAG}.zip"

        stage('Init release') {
            sh "mvn com.stratio.rocket:rocket-maven-plugin:1.1.0-SNAPSHOT:init -DrocketBaseUrl=$ROCKET_URL -Dcookie=$ROCKET_COOKIE -DreleaseId=$RELEASE_ID -DassetVersionId=$ASSET_VERSION_ID -DbuildUrl=$BUILD_URL"
        }

        sleep(time: sleep_time, unit:"SECONDS")

        stage('Check prod instance') {
            sh "mvn com.stratio.rocket:rocket-maven-plugin:1.1.0-SNAPSHOT:checkEnv -DrocketBaseUrl=$ROCKET_URL -Dcookie=$ROCKET_COOKIE -DtargetEnvBaseUrl=$ROCKET_URL_PROD -DtargetEnvCookie=$ROCKET_COOKIE_PROD -DreleaseId=$RELEASE_ID"
        }

        sleep(time: sleep_time, unit:"SECONDS")

        stage('Check asset dependencies') {
            sh "mvn com.stratio.rocket:rocket-maven-plugin:1.1.0-SNAPSHOT:checkDependencies -DrocketBaseUrl=$ROCKET_URL -Dcookie=$ROCKET_COOKIE -DassetVersionId=$ASSET_VERSION_ID -DrocketBaseUrlProd=$ROCKET_URL_PROD -DcookieProd=$ROCKET_COOKIE_PROD -DreleaseId=$RELEASE_ID"
        }

        sleep(time: sleep_time, unit:"SECONDS")

        stage('Export asset') {
            sh "mvn com.stratio.rocket:rocket-maven-plugin:1.1.0-SNAPSHOT:exportAsset -DrocketBaseUrl=$ROCKET_URL -Dcookie=$ROCKET_COOKIE -DassetVersionId=$ASSET_VERSION_ID -DreleaseId=$RELEASE_ID -DexportPath=$ARCHIVE_PATH"
            archiveArtifacts $ARCHIVE_PATH
        }

        sleep(time: sleep_time, unit: "SECONDS")

        stage('Import asset') {
            sh "mvn com.stratio.rocket:rocket-maven-plugin:1.1.0-SNAPSHOT:importAsset -DrocketBaseUrl=$ROCKET_URL -Dcookie=$ROCKET_COOKIE -DassetVersionId=$ASSET_VERSION_ID -DreleaseId=$RELEASE_ID -DrocketBaseUrlProd=$ROCKET_URL_PROD -DcookieProd=$ROCKET_COOKIE_PROD -DimportPath=$ARCHIVE_PATH"
        }

        sleep(time: sleep_time, unit:"SECONDS")

        stage('Lock dev model') {
            sh "mvn com.stratio.rocket:rocket-maven-plugin:1.1.0-SNAPSHOT:lockDev -DrocketBaseUrl=$ROCKET_URL -Dcookie=$ROCKET_URL -DreleaseId=$RELEASE_ID -DassetVersionId=$ASSET_VERSION_ID"
        }

        sleep(time: sleep_time, unit:"SECONDS")

        stage('Lock prod model') {
            sh "mvn com.stratio.rocket:rocket-maven-plugin:1.1.0-SNAPSHOT:lockProd -DrocketBaseUrl=$ROCKET_URL -Dcookie=$ROCKET_URL -DreleaseId=$RELEASE_ID -DassetVersionId=$ASSET_VERSION_ID -DrocketBaseUrlProd=$ROCKET_URL_PROD -DcookieProd=$ROCKET_COOKIE_PROD"
        }

        sleep(time: sleep_time, unit:"SECONDS")

        stage('Finalize release') {
            sh "mvn com.stratio.rocket:rocket-maven-plugin:1.1.0-SNAPSHOT:finish -DrocketBaseUrl=$ROCKET_URL -Dcookie=$ROCKET_URL -DreleaseId=$RELEASE_ID"
        }

    }

}
