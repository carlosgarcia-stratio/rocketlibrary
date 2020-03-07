package com.stratio.rocket.flow

def execute(flow) {
    flow.each { stages ->
        executeStage(stage)
    }
}

def executeStage(stage){
    return flow.singleStage(stage.name, {
        stage.executeStage()
    })
}

return this
