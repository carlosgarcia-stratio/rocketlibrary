package com.stratio.rocket.http


def String execute(String command) {
    sh(script: '#!/bin/sh -e\n' + command)
}

def String executeWithOutput(String command) {
    def response = sh(script: '#!/bin/sh -e\n' + command, returnStdout: true)
    return response
}

def String executeWithStatus(String command) {
    def response = sh(script: '#!/bin/sh -e\n' + command, returnStatus: true)
    return response
}

return this
