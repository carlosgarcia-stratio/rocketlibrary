package com.stratio.rocket.stages

import com.stratio.rocket.rocketUtils.RocketConstants

def executeStage(s) {

    if(s.status == "FAILED"){
        rocket.dev.addReleaseStageState(s.name, RocketConstants.STAGE_FAILED, "Stage ${s.name} failed: ${s.message}.")
    } else {
        def message = s.message == "" ? "Stage ${s.name} finished successfully" : s.message
        rocket.dev.addReleaseStageState(s.name, RocketConstants.STAGE_FINISHED, message)
    }

}
