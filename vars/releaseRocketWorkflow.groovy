#!groovy
import com.stratio.rocket.rocketUtils.RocketConstants
import com.stratio.rocket.rocketUtils.RocketClient

def call(Map props = [:]) {

    env[RocketConstants.ROCKET_API_URL_DEV] = "localhost:8080"
    env[RocketConstants.ROCKET_API_URL_PRO] = "localhost:8081"

    println(props)
    service.initRocketInstances()

    service.rocketDev.getWorkflowRelease(props["releaseId"])
    service.rocketPro.getWorkflowRelease(props["releaseId"])
}