#!groovy

import groovy.transform.Field


@Field def static props = [:]
@Field def static workflow = new Workflow()
@Field def static project = new Project()

def init(Map p) {
    props << p
}
