#!groovy

import groovy.transform.Field

@Field def props = [:]

def init(Map p) {
    println(p)
    p.each { key ->
        println(key)
        println(p[key])
        props[key] = p[key]
    }
    println(props)
}
