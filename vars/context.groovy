#!groovy

import groovy.transform.Field
import com.stratio.rocket.rocketUtils.Workflow
import com.stratio.rocket.rocketUtils.Project

@Field def static props = [:]
@Field def static workflowDev = new Workflow()
@Field def static project = new Project()

def init(Map p) {
    props << p
}
