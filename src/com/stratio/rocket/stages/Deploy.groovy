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

    //Create if project exist
    //String project = rocket.pro.createProjectIfNotExist(rocket.dev.project.getName())

    String project = rocket.pro.api.createProject(rocket.dev.project.getName(), rocket.dev.project.getDescription())
    println(project)
    String workflow = rocket.pro.api.importWorkflow(rocket.dev.workflow.getAsEscapedJson(),
            rocket.dev.project.getGroupId(),
            rocket.dev.project.getId(),
            rocket.dev.workflow.getName(),
            rocket.dev.workflow.getDescription())
    println(workflow)
}

return this
