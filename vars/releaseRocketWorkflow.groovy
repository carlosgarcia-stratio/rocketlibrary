#!groovy

def call(Map props = [:]) {

    println(props)
    println(env)
    service.initRocketInstances()
    println(service.instances)
}