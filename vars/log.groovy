
import groovy.transform.Field

@Field static ArrayList<String> maskList = []

void debug(message) {
    if(context.ctx.logLevel in ["DEBUG"]) mask(message) { maskedMessage ->
        echo "DEBUG: ${maskedMessage}"
    }
}

void info(message) {
    if(context.ctx.logLevel in ["DEBUG", "INFO"]) mask(message) { maskedMessage ->
        echo "INFO: ${maskedMessage}"
    }
}

void warning(message) {
    if(context.ctx.logLevel in ["DEBUG", "INFO", "WARN"]) mask(message) { maskedMessage ->
        echo "WARNING: ${maskedMessage}"
    }
}

void error(message) {
    if(context.ctx.logLevel in ["DEBUG", "INFO", "WARN", "ERROR"]) mask(message) { maskedMessage ->
        echo "ERROR: ${maskedMessage}"
    }
}


void mask(String message, Closure cl) {

    String maskedMessage = message
    maskList.each { m ->
        maskedMessage = maskedMessage.replace(m, "****")
    }
    cl(maskedMessage)
}

def withLogger(Closure cl) {
    cl()
}
