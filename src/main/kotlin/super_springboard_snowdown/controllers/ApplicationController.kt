package super_springboard_snowdown.controllers

import com.mitchellbosecke.pebble.PebbleEngine
import org.springframework.web.bind.annotation.RestController
import java.io.StringWriter

@RestController
abstract class ApplicationController {
    fun renderTemplate(templateName:String, templateArguments:Map<String, Any>?=null):String {
        val template = PebbleEngine.Builder().build().getTemplate("templates/$templateName.peb")
        val writer = StringWriter()
        template.evaluate(writer, templateArguments)
        return writer.toString()
    }
}