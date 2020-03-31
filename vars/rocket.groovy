
import groovy.transform.Field

import com.stratio.rocket.constants.RocketConstants
import com.stratio.rocket.api.Rocket

@Field static Rocket dev = new Rocket()
@Field static Rocket pre = new Rocket()
@Field static Rocket pro = new Rocket()

void initInstances() {

    def dev_url = context.getFromPropsOrEnv(RocketConstants.ROCKET_API_URL_DEV)
    if (dev_url) {
        dev.init(RocketConstants.DEV, dev_url)
        dev.initRelease(context.props["releaseId"])
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
