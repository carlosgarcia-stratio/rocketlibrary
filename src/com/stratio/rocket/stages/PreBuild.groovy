package com.stratio.rocket.stages

import com.stratio.rocket.rocketUtils.RocketConstants

def executeStage() {

    rocket.dev.updateReleaseExecutionState(RocketConstants.RELEASE_STARTED)
    rocket.dev.addReleaseInfo(["Build URL", env.BUILD_URL])
}
