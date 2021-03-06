package com.stratio.rocket.stages

import com.stratio.rocket.constants.FlowConstants
import com.stratio.rocket.constants.RocketConstants

def executeStage() {

    if(context.ctx.buildStatus == FlowConstants.FAILURE) {
        rocket.dev.addReleaseInfo("Duration", "${currentBuild.duration/1000} seconds")
        rocket.dev.addReleaseInfo("Error", context.ctx.error)
        rocket.dev.updateReleaseExecutionState(RocketConstants.RELEASE_FAILED)
    } else {
        rocket.dev.addReleaseInfo("Duration", "${currentBuild.duration/1000} seconds")
        rocket.dev.updateReleaseExecutionState(RocketConstants.RELEASE_FINISHED)
    }
}
