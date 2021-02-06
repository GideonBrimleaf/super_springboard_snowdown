package super_springboard_snowdown.controllers

import com.mitchellbosecke.pebble.PebbleEngine
import java.io.StringWriter

fun renderTemplate(templateName:String, templateArguments:Map<String, List<Any>>?=null):String {
    val template = PebbleEngine.Builder().build().getTemplate("templates/$templateName.peb")
    val writer = StringWriter()
    template.evaluate(writer, templateArguments)
    return writer.toString()
}