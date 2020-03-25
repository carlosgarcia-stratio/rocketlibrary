package com.stratio.rocket.stages

import com.stratio.rocket.rocketUtils.RocketConstants

def executeStage(s) {

    rocket.dev.addReleaseStageState(s.name, RocketConstants.STAGE_STARTED, "Stage ${s.name} started...")
}
