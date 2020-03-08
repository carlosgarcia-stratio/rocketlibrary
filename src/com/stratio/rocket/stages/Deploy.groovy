package com.stratio.rocket.stages

import groovy.transform.Field
import com.stratio.rocket.flow.Stages

@Field def name = Stages.DEPLOY


def executeStage() {
    log.info("Deploy Stage started")
    execute()
    log.info("Deploy Stage finished")
}

def execute() {
    log.info("Deploy Stage execute")
    sleep 15
}

return this