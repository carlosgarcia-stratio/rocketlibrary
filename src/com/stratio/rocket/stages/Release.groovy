package com.stratio.rocket.stages

import groovy.transform.Field
import com.stratio.rocket.constants.FlowConstants
import com.stratio.rocket.flow.Stages

@Field def name = Stages.RELEASE
@Field def status = FlowConstants.SUCCESS
@Field def message = ""

def executeStage() {
    log.info("Executing stage ${name}...")

    //Set workflow state as release and lock in dev
    log.info "Locking and setting as relased workflow version in dev environment"
    rocket.dev.releaseAndLock("Released")
    log.info "Workflow version updated successfully"

    //Lock workflow version in prod
    log.info "Locking workflow version in production environment"
    rocket.pro.lock()
    log.info "Workflow version updated successfully"

    message = "Workflow version ${rocket.dev.workflow.getId()} locked and set as released successfully"
    log.info("Stage ${name} finished successfully")
}

return this
