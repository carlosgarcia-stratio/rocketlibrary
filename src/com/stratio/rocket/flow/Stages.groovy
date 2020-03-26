package com.stratio.rocket.flow

import com.stratio.rocket.stages.*

class Stages implements Serializable {

    static String CHECKOUT = "Checkout"
    static String VERIFY = "Verify"
    static String DEPLOY = "Deploy"
    static String RELEASE = "Release"

    static def instances = [
            (Stages.CHECKOUT) : new Checkout(),
            (Stages.VERIFY) : new Verify(),
            (Stages.DEPLOY) : new Deploy(),
            (Stages.RELEASE): new Release()
    ]

}
