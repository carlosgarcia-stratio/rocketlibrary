package com.stratio.rocket.flow

def execute(flow) {
    flow.each { stages ->
        executeStage(stage)
    }
}

def executeStage(stage){
    stage.executeStage()
}

return this
