package com.stratio.rocket.http


def String execute(String command) {
    def response = sh(script: command)
    return response
}

def String executeWithOutput(String command) {
    def response = sh(script: command, returnStdout: true)
    return response
}

def String executeWithStatus(String command) {
    def response = sh(script: command, returnStatus: true)
    return response
}

return this
