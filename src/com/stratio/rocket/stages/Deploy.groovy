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
    String project = rocket.pro.createProject(context.props["projectId"], "")
    def projectJson = readJSON text: project

    String workflow = rocket.pro.importWorkflow(context.props["workflow"], project["groupId"], project["id"], context.props["workflowName"], context.props["workflowDescription"])

    sleep 15
}

return this