#!groovy

import groovy.transform.Field

@Field def props = [:]

def init(Map p) {
    props << p
}
