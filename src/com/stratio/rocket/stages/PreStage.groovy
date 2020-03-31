package com.stratio.rocket.stages

import com.stratio.rocket.constants.RocketConstants

def executeStage(s) {

    rocket.dev.addReleaseStageState(s.name, RocketConstants.STAGE_STARTED, "Stage ${s.name} started...")
}
