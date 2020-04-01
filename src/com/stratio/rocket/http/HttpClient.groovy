package com.stratio.rocket.http

import com.stratio.rocket.constants.HttpConstants

def execute(String command) {

    String mutedCmd = HttpConstants.MUTED + command
    log.debug command
    sh(script: mutedCmd)
}

def executeWithOutput(String command) {

    String mutedCmd = HttpConstants.MUTED + command
    log.debug command
    def response = sh(script: mutedCmd, returnStdout: true)
    log.debug response
    return response
}

def executeWithStatus(String command) {

    String mutedCmd = HttpConstants.MUTED + command
    log.debug command
    def response = sh(script: mutedCmd, returnStatus: true)
    log.debug response
    return response
}

def handleJsonResponse(String response, String message) {

    def responseJson = [:]
    try{
        responseJson = readJSON text: response
    } catch (Exception e) {
        log.error message + ": ${e.getMessage()}"
        error message + ": ${e.getMessage()}"
    }

    if(responseJson.errorCode) {
        error message + ": ${responseJson.exception}"
    }

    return responseJson
}

def handleJsonErrorResponse(String response, String message) {

    def responseJson = [:]
    try{
        responseJson = readJSON text: response
    } catch (Exception e) {}

    if(responseJson.errorCode) {
        error message + ": ${responseJson.exception}"
    }

    return response
}

return this
