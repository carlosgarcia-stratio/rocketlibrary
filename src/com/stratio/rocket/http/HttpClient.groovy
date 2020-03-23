package com.stratio.rocket.http


def String execute(String command) {
    def response = sh(script: command, returnStdout: true, returnSta)
    return response
}

return this
