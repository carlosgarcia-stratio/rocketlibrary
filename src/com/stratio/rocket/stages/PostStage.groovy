package com.stratio.rocket.stages

import com.stratio.rocket.rocketUtils.RocketConstants

def executeStage(s) {

    rocket.dev.addWorkflowReleaseStage(context.props["releaseId"], s.name, RocketConstants.STAGE_FINISHED, "Stage ${s.name} finished.")
}
