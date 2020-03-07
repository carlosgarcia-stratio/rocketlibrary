#!groovy

import groovy.transform.Field
import com.stratio.rocket.rocketUtils.RocketConstants
import com.stratio.rocket.rocketUtils.RocketClient

@Field static def rocketDev = new RocketClient()
@Field static def rocketPre = new RocketClient()
@Field static def rocketPro = new RocketClient()

def initRocketInstances() {

    if (env[RocketConstants.ROCKET_API_URL_DEV]) {
      rocketDev.initialize(env[RocketConstants.ROCKET_API_URL_DEV], "")
    }

    if (env[RocketConstants.ROCKET_API_URL_PRE]) {
        rocketPre.initialize(env[RocketConstants.ROCKET_API_URL_PRE], "")
    }

    if (env[RocketConstants.ROCKET_API_URL_PRO]) {
        rocketPro.initialize(env[RocketConstants.ROCKET_API_URL_PRO], "")
    }
}
