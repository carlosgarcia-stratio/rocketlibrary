package com.stratio.rocket.http

class HttpRequest implements Serializable {

    String command
    
    HttpRequest(){
        this.command = "curl"
    }

    HttpRequest withUrl(String url) {
        this.command += " " + url
        return this
    }

    HttpRequest get() {
        this.command += " -XGET"
    }

    HttpRequest post() {
        this.command += " -XPOST"
    }

    HttpRequest put() {
        this.command += " -XPUT"
    }

    HttpRequest delete() {
        this.command += " -XDELETE"
    }
    
    HttpRequest verbose(boolean flag) {
        if(flag) {
            this.command += " -vvv "
        }
        return this
    }

    HttpRequest withHeader(String header) {
        this.command += " -H '" + aHeader + "'"
        return this
    }

    HttpRequest insecure(){
        this.command += " -k"
        return this
    }

    HttpRequest silent(){
        this.command += " -s"
        return this
    }


    HttpRequest withCredentials(String aCredentials){
        this.str += " -u '" + aCredentials + "'"
        return this
    }

    HttpRequest withBody(String body){
        if(!body || body.isEmpty()){
            return this
        }
        this.command += " -d '" + body + "'"
        return this
    }

    HttpRequest upload(String file){
        this.command += " -T " + file
        return this
    }

    String getCommand(){
        return this.command.toString()
    }
}
