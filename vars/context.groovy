#!groovy

import groovy.transform.Field

@Field def props = [:]

def init(Map p) {
    p.each { key, value ->
        props[key] = value
    }
    println(props)
}
