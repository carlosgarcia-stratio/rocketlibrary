package com.stratio.rocket.flow

import com.stratio.rocket.constants.FlowConstants
import com.stratio.rocket.stages.PreBuild
import com.stratio.rocket.stages.PostBuild
import com.stratio.rocket.stages.PreStage
import com.stratio.rocket.stages.PostStage

def execute(flow) {
    node{
        try {
            new PreBuild().executeStage()
            flow.each { s ->
                executeStage(s)
            }
        } catch(Exception e) {
            context.buildStatus = FlowConstants.FAILURE
            context.error = e.getMessage()
        } finally {
            new PostBuild().executeStage()
            currentBuild.result = context.buildStatus
        }
    }
}

def executeStage(fStage){
    stage(fStage.name){
        try {
            new PreStage().executeStage(fStage)
            fStage.executeStage()
        } catch(Exception e) {
            handleStageError(fStage, e)
        } finally {
            new PostStage().executeStage(fStage)
        }

    }
}

def handleStageError(stage, Exception e) {
    def errorMsg = "Error executing stage ${stage.name}: ${e.getMessage()}"
    log.error errorMsg
    stage.message = errorMsg
    stage.status = FlowConstants.FAILURE
    context.error = errorMsg
    context.buildStatus = FlowConstants.FAILURE
    error errorMsg
}

return this
