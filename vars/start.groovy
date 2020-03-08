#!groovy
import com.stratio.rocket.rocketUtils.RocketConstants

def call(Map props = [:]) {

    env[RocketConstants.ROCKET_API_URL_DEV] = "172.17.0.5:8080"
    env[RocketConstants.ROCKET_API_URL_PRO] = "localhost:8081"

    context.props = props
    rocket.initInstances()
    flow.executor.execute(flow.builder.create())
}
