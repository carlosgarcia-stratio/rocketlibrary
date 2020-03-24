#!groovy

import groovy.transform.Field
import com.stratio.rocket.rocketUtils.RocketConstants
import com.stratio.rocket.rocketUtils.Rocket

@Field static def dev = new Rocket()
@Field static def pre = new Rocket()
@Field static def pro = new Rocket()

def initInstances() {

    def dev_url = context.getFromPropsOrEnv(RocketConstants.ROCKET_API_URL_DEV)
    if (dev_url) {
        dev.init(RocketConstants.DEV, dev_url)
    }

    def pre_url = context.getFromPropsOrEnv(RocketConstants.ROCKET_API_URL_PRE)
    if (pre_url) {
        pre.init(RocketConstants.PRE, pre_url)
    }

    def pro_url = context.getFromPropsOrEnv(RocketConstants.ROCKET_API_URL_PRO)
    if (pro_url) {
        pro.init(RocketConstants.PRO, pro_url)
    }
}
