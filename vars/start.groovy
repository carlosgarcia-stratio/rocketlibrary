#!groovy

def call(Map props = [:]) {

    if (props["assetType"] == "Workflow") {
        doWorkflow2envs(props)
    } else if (props["assetType"] == "MLModel") {
        doMLModel2envs(props)
    } else if (props["assetType"] == "MLProject") {
        doMLProject2envs(props)
    } else if (props["assetType"] == "AutoML") {
        doAutoML2envs(props)
    } else if (props["assetType"] == "MLTrainer") {
        doMLTrainer2envs(props)
    } else {
        error "Invalid asset type ${props['assetType']}"
    }


}
