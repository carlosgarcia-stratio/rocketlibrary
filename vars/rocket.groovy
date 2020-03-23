#!groovy

import groovy.transform.Field
import com.stratio.rocket.rocketUtils.RocketConstants
import com.stratio.rocket.rocketUtils.Rocket

@Field static def dev = new Rocket()
@Field static def pre = new Rocket()
@Field static def pro = new Rocket()

def initInstances() {

    def dev_url = getFromPropsOrEnv(RocketConstants.ROCKET_API_URL_DEV)
    if (dev_url) {
        def dev_auth_method = getFromPropsOrEnv(RocketConstants.ROCKET_AUTH_METHOD_DEV, RocketConstants.ROCKET_AUTH_USER_PASS)
        def dev_tenant = getFromPropsOrEnv(RocketConstants.ROCKET_TENANT_DEV)
        def authProps = [authMethod: dev_auth_method,
                        url: dev_url,
                        tenant: dev_tenant
                        ]
        def auth = getAuth(authProps)
        dev.api.initialize(dev_url, auth)
    }

    def pre_url = getFromPropsOrEnv(RocketConstants.ROCKET_API_URL_PRE)
    if (pre_url) {
        def pre_auth_method = getFromPropsOrEnv(RocketConstants.ROCKET_AUTH_METHOD_PRE, RocketConstants.ROCKET_AUTH_USER_PASS)
        def pre_tenant = getFromPropsOrEnv(RocketConstants.ROCKET_TENANT_PRE)
        def authProps = [authMethod: pre_auth_method,
                         url: pre_url,
                         tenant: pre_tenant
                        ]
        def auth = getAuth(authProps)
        pre.api.initialize(pre_url, auth)
    }

    def pro_url = getFromPropsOrEnv(RocketConstants.ROCKET_API_URL_PRO)
    if (pro_url) {
        def pro_auth_method = getFromPropsOrEnv(RocketConstants.ROCKET_AUTH_METHOD_PRO, RocketConstants.ROCKET_AUTH_USER_PASS)
        def pro_tenant = getFromPropsOrEnv(RocketConstants.ROCKET_TENANT_PRO)
        def authProps = [authMethod: pro_auth_method,
                         url: pro_url,
                         tenant: pro_tenant
                        ]
        def auth = getAuth(authProps)
        pro.api.initialize(pro_url, auth)
    }
}

def getFromPropsOrEnv(String key, String defaultValue = null) {
    return context.props.containsKey(key) ? context.props[key]  : (env[key] ? env[key] : defaultValue)
}

def getAuth(Map props) {
    if (props.authMethod == RocketConstants.ROCKET_AUTH_USER_PASS) {
        withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: "$credentialsId", usernameVariable: 'USER', passwordVariable: 'PASS']]) {
            def exists = fileExist RocketConstants.AUTH_TOKEN_TEMP_PATH
            if(!exists) {
                def authScript = libraryResource RocketConstants.AUTH_TOKEN_RESOURCE_PATH
                writeFile file: RocketConstants.AUTH_TOKEN_TEMP_PATH, text: authScript
            }
            def token = sh(script: "source ${RocketConstants.AUTH_TOKEN_TEMP_PATH} ${props.url} ${USER} ${PASS} ${props.tenant}", returnStdout = true)
            return " -H Cookie: user=${token}"
        }
    } else if(props.authMethod == RocketConstants.ROCKET_AUTH_MUTUAL_TLS) {
        log.error "Auth Method not implemented yet"
        error "Auth Method not implemented yet"
    } else {
        log.debug "No auth..."
        return ""
    }
}
