package com.stratio.rocket.http

class HttpRequest implements Serializable {

    String command
    
    HttpRequest() {
        this.command = "curl"
        println(this.command)
    }

    HttpRequest withUrl(String url) {
        this.command += " " + url
        println(this.command)
        return this
    }

    HttpRequest get() {
        this.command += " -XGET"
        println(this.command)
        return this
    }

    HttpRequest post() {
        this.command += " -XPOST"
        println(this.command)
        return this
    }

    HttpRequest put() {
        this.command += " -XPUT"
        println(this.command)
        return this
    }

    HttpRequest delete() {
        this.command += " -XDELETE"
        println(this.command)
        return this
    }
    
    HttpRequest verbose(boolean flag) {
        if(flag) {
            this.command += " -vvv "
        }
        println(this.command)
        return this
    }

    HttpRequest withHeader(String header) {
        this.command += " -H '" + aHeader + "'"
        println(this.command)
        return this
    }

    HttpRequest insecure() {
        this.command += " -k"
        println(this.command)
        return this
    }

    HttpRequest silent() {
        this.command += " -s"
        println(this.command)
        return this
    }


    HttpRequest withCredentials(String aCredentials) {
        this.command += " -u '" + aCredentials + "'"
        println(this.command)
        return this
    }

    HttpRequest withBody(String body) {
        if(!body || body.isEmpty()){
            return this
        }
        this.command += " -d '" + body + "'"
        println(this.command)
        return this
    }

    HttpRequest upload(String file) {
        this.command += " -T " + file
        println(this.command)
        return this
    }

    String getCommand() {
        return this.command.toString()
    }
}
