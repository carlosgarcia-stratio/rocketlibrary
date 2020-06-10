#!groovy

def call(Map props = [:]) {

    if (props["assetType"] == "Workflow") {
        doWorkflow2envs(props)
    } else if (props["assetType"] == "MLModel") {

    } else if (props["assetType"] == "MLProject") {

    } else if (props["assetType"] == "AutoML") {

    } else if (props["assetType"] == "MLTrainer") {

    }


}
