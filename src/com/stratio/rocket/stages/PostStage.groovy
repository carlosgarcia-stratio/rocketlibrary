package com.stratio.rocket.stages

import com.stratio.rocket.constants.FlowConstants
import com.stratio.rocket.constants.RocketConstants

def executeStage(s) {

    if(s.status == FlowConstants.FAILURE){
        rocket.dev.addReleaseStageState(s.name, RocketConstants.STAGE_FAILED, "Stage ${s.name} failed: ${s.message}.")
    } else {
        def message = s.message == "" ? "Stage ${s.name} finished successfully" : s.message
        rocket.dev.addReleaseStageState(s.name, RocketConstants.STAGE_FINISHED, message)
    }
}
