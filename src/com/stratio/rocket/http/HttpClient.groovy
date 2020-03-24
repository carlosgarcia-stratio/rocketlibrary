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

return this
