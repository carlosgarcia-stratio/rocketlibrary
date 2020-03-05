#!groovy

def call(Map props = [:]) {

    println(props)
    service.initRocketInstances()
    println(service.instances)
}