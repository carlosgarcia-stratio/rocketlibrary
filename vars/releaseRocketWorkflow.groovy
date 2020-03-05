#!groovy

def call(Map props = [:]) {

    println(props)
    services.initRocketInstances()
    println(instances)
}