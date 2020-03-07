#!groovy
import com.stratio.rocket.rocketUtils.RocketConstants
import com.stratio.rocket.rocketUtils.RocketClient

def call(Map props = [:]) {

    env[RocketConstants.ROCKET_API_URL_DEV] = "localhost:8080"
    env[RocketConstants.ROCKET_API_URL_PRO] = "localhost:8081"

    println(props)
    service.initRocketInstances()
    println(service.instances)

    def dev = new RocketClient()
    dev.initialize(service.instances[RocketConstants.DEV], "")
    dev.getWorkflowRelease(props["releaseId"])

//    def pro = new RocketClient()
//    pro.initialize(service.instances[RocketConstants.DEV], "")
//    pro.getWorkflowRelease(props["releaseId"])
}