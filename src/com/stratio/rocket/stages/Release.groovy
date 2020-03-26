package com.stratio.rocket.stages

import groovy.transform.Field
import com.stratio.rocket.flow.Stages

@Field def name = Stages.RELEASE
@Field def status = "SUCCESS"
@Field def message = ""

def executeStage() {
    log.info("Release Stage started")
    execute()
    log.info("Release Stage finished")
}

def execute() {
    log.info("Release Stage execute")

    //Set workflow state as release and lock in dev
    rocket.dev.releaseAndLock("Released")

    //Lock workflow version in prod
    rocket.pro.lock()
    message = "Workflow version ${workflowId} locked and set as released successfully"
}

return this
