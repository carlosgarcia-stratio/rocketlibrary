package com.stratio.rocket.stages

import com.stratio.rocket.rocketUtils.RocketConstants

def executeStage() {

    if(context.buildStatus == "FAILURE") {
        rocket.dev.updateReleaseExecutionState(RocketConstants.RELEASE_FAILED)
        rocket.dev.addReleaseInfo("Duration", currentBuild.durationString)
        rocket.dev.addReleaseInfo("Error", context.error)
    } else {
        rocket.dev.addReleaseInfo("Duration", currentBuild.durationString)
        rocket.dev.updateReleaseExecutionState(RocketConstants.RELEASE_FINISHED)
    }
}
