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

    def reponseJson = [:]
    try{
        reponseJson = readJSON text: response
    } catch (Exception e) {
        log.error message + ": ${e.toString()}"
        error message + ": ${e.toString()}"
    }

    if(responseJson.errorCode) {
        log.error message + ": ${responseJson.exception}"
        error message + ": ${responseJson.exception}"
    }

    return responseJson
}

return this
