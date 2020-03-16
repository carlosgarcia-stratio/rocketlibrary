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
    String project = rocket.pro.createProject(context.project.getName(), context.project.getDescription())
    println(project)
    String workflow = rocket.pro.importWorkflow(context.workflow.getAsEscapedJson(),
            context.project.getGroupId(),
            context.project.getId(),
            context.workflow.getName(),
            context.workflow.getDescription())
    println(workflow)
}

return this
