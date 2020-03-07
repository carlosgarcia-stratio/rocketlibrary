package com.stratio.rocket.flow

def execute(flow) {
    node{
        flow.each { s ->
            executeStage(s)
        }
    }
}

def executeStage(fStage){
    stage(fStage.name){
        fStage.executeStage()
    }
}

return this
