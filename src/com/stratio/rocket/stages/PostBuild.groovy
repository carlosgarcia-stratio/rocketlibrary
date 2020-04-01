package com.stratio.rocket.stages

import com.stratio.rocket.constants.FlowConstants
import com.stratio.rocket.constants.RocketConstants

def executeStage(String buildStatus) {

    if(buildStatus == FlowConstants.FAILURE) {
        rocket.dev.updateReleaseExecutionState(RocketConstants.RELEASE_FAILED)
        rocket.dev.addReleaseInfo("Duration", "${currentBuild.duration/1000} seconds")
        rocket.dev.addReleaseInfo("Error", context.error)
    } else {
        rocket.dev.addReleaseInfo("Duration", "${currentBuild.duration/1000} seconds")
        rocket.dev.updateReleaseExecutionState(RocketConstants.RELEASE_FINISHED)
    }
}
