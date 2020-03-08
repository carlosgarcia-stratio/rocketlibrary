#!groovy

import groovy.transform.Field

@Field def props = [:]

def init(Map p) {
    p.each { key ->
        props[key] = p[key]
    }
}
