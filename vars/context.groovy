#!groovy

import groovy.transform.Field

@Field def static props = [:]

def init(Map p) {
    props << p
}
