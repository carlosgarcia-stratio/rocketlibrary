package com.stratio.rocket.http

class HttpRequest implements Serializable {

    String command
    
    HttpRequest() {
        this.command = "curl"
    }

    HttpRequest withAuth(String auth) {
        if(auth != "") this.command += " " + auth
        return this
    }


    HttpRequest withUrl(String url) {
        this.command += " " + url
        return this
    }

    HttpRequest get() {
        this.command += " -XGET"
        return this
    }

    HttpRequest post() {
        this.command += " -XPOST"
        return this
    }

    HttpRequest put() {
        this.command += " -XPUT"
        return this
    }

    HttpRequest delete() {
        this.command += " -XDELETE"
        return this
    }
    
    HttpRequest verbose(boolean flag) {
        if(flag) {
            this.command += " -vvv "
        }
        return this
    }

    HttpRequest withFail() {
        this.command += " -f"
        return this
    }

    HttpRequest withHeader(String header) {
        this.command += " -H '" + header + "'"
        return this
    }

    HttpRequest insecure() {
        this.command += " -k"
        return this
    }

    HttpRequest silent() {
        this.command += " -s"
        return this
    }


    HttpRequest withCredentials(String credentials) {
        this.command += " -u '" + credentials + "'"
        return this
    }

    HttpRequest withBody(String body) {
        if(!body || body.isEmpty()){
            return this
        }
        this.command += " -d '" + body + "'"
        return this
    }

    HttpRequest upload(String file) {
        this.command += " -T " + file
        return this
    }

    String getRequest() {
        return this.command.toString()
    }
}
