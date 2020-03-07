package com.stratio.rocket.flow

def execute(flow) {
    node{
        flow.each { stage ->
            executeStage(stage)
        }
    }
}

def executeStage(stage){
    stage(stage.name){
        stage.executeStage()
    }
}

return this
