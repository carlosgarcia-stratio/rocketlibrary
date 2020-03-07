package com.stratio.rocket.http


def String execute(String command) {
    sh(script: command, returnStdout = true)
}

return this
