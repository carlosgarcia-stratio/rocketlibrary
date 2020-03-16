package com.stratio.rocket.stages

import com.stratio.rocket.rocketUtils.RocketConstants

def executeStage() {

    rocket.dev.api.updateWorkflowReleaseExecutionState(context.props["releaseId"], RocketConstants.RELEASE_STARTED)
}
