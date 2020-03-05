#!groovy

def call(Map props = [:]) {

    println(props)
    service.rocketDev
    service.rocketPre
    service.rocketPro
}