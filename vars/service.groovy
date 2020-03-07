#!groovy

import groovy.transform.Field
import com.stratio.rocket.rocketUtils.RocketConstants

@Field static def instances = [:]

def initRocketInstances() {

    if (env[RocketConstants.ROCKET_API_URL_DEV]) {
      instances[RocketConstants.DEV] = env[RocketConstants.ROCKET_API_URL_DEV]
    }

    if (env[RocketConstants.ROCKET_API_URL_DEV]) {
        instances[RocketConstants.PRE] = env[RocketConstants.ROCKET_API_URL_DEV]
    }

    if (env[RocketConstants.ROCKET_API_URL_DEV]) {
        instances[RocketConstants.PRO] = env[RocketConstants.ROCKET_API_URL_DEV]
    }
}
