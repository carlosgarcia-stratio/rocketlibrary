package com.stratio.rocket.stages

import groovy.transform.Field
import com.stratio.rocket.flow.Stages

@Field def name = Stages.VERIFY

def executeStage() {
    log.info("Verify Stage started")
    execute()
    log.info("Verify Stage finished")
}

def execute() {
    log.info("Verify Stages execute")
    sleep 1
}

return this