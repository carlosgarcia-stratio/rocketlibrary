package com.stratio.rocket.flow

def execute(flow) {
    flow.each { stage ->
        executeStage(stage)
    }
}

def executeStage(stage){
    node{
        stage.executeStage()
    }
}

return this
