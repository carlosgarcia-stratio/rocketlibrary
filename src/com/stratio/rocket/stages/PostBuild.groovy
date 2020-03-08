package com.stratio.rocket.stages

import com.stratio.rocket.rocketUtils.RocketConstants

def executeStage() {

    rocket.dev.addWorkflowReleaseStage(s.name, RocketConstants.RELEASE_FINISHED)
}
