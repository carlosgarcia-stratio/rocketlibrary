package com.stratio.rocket.http


def String execute(String command) {
    def response = sh(script: command)
    return request
}

def String executeWithOutput(String command) {
    def response = sh(script: command, returnStdout: true)
    return request
}

def String executeWithStatus(String command) {
    def response = sh(script: command, returnStatus: true)
    return request
}

return this
