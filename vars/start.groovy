#!groovy
import com.stratio.rocket.rocketUtils.RocketConstants

def call(Map props = [:]) {

    env[RocketConstants.ROCKET_API_URL_DEV] = "172.17.0.5:9090"
    env[RocketConstants.ROCKET_API_URL_PRO] = "localhost:9091"

    println(props)
    println(props["releaseId"])
    context.init(props)
    println(context.props)
    println(context.props["releaseId"])
    rocket.initInstances()
    flow.executor.execute(flow.builder.create())
}
