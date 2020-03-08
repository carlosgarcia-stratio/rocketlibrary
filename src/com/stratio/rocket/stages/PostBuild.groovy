package com.stratio.rocket.stages

import com.stratio.rocket.rocketUtils.RocketConstants

def executeStage() {

    rocket.dev.updateWorkflowReleaseExecutionState(RocketConstants.RELEASE_FINISHED)
}
