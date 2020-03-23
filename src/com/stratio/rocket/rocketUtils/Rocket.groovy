package com.stratio.rocket.rocketUtils

import groovy.transform.Field
import com.stratio.rocket.rocketUtils.RocketClient
import com.stratio.rocket.rocketUtils.Workflow
import com.stratio.rocket.rocketUtils.Project

@Field def api = new RocketClient()
@Field def workflow = new Workflow()
@Field def project = new Project()

def createProjectIfNotExist(String projectName) {

    try {
        api.findProjectByName(rocket.dev.project.getName())
    } catch {}

}

return this
