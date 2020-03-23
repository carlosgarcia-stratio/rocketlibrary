#!groovy

def call(Map props = [:]) {

    node{
        context.init(props)
        rocket.initInstances()
    }
    flow.executor.execute(flow.builder.create())
}
