#!groovy
import com.stratio.rocket.rocketUtils.RocketConstants

def call(Map props = [:]) {

    env[RocketConstants.ROCKET_API_URL_DEV] = "localhost:8080"
    env[RocketConstants.ROCKET_API_URL_PRO] = "localhost:8081"

    println(props)
    service.initRocketInstances()
    println(service.instances)
}