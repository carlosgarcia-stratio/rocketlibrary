package com.stratio.rocket.flow

def execute(flow) {
    println(flow)
    flow.each { stage ->
        executeStage(stage)
    }
}

def executeStage(stage){
    println(stage)
    stage.executeStage()
}

return this
