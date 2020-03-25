package com.stratio.rocket.http


def execute(String command) {
    sh(script: '#!/bin/sh -e\n' + command)
}

def executeWithOutput(String command) {
    def response = sh(script: '#!/bin/sh -e\n' + command, returnStdout: true)
    return response
}

def executeWithStatus(String command) {
    def response = sh(script: '#!/bin/sh -e\n' + command, returnStatus: true)
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
        log.error message + ": ${responseJson.exception}"
        error message + ": ${responseJson.exception}"
    }

    return responseJson
}

return this
