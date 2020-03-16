package com.stratio.rocket.stages

import com.stratio.rocket.rocketUtils.RocketConstants

def executeStage(s) {

    rocket.dev.api.addWorkflowReleaseStage(context.props["releaseId"], s.name, RocketConstants.STAGE_STARTED, "Stage ${s.name} started...")
}
