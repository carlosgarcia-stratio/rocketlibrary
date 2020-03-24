#!groovy

import groovy.transform.Field

@Field def static props = [:]

def init(Map p) {
    props << p
}

def getFromPropsOrEnv(String key, String defaultValue = null) {
    return props.containsKey(key) ? props[key]  : (env[key] ? env[key] : defaultValue)
}
