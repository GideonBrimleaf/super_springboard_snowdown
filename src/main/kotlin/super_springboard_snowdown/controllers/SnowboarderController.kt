package super_springboard_snowdown.controllers

import com.mitchellbosecke.pebble.PebbleEngine
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import super_springboard_snowdown.Snowboarder
import super_springboard_snowdown.SnowboarderService
import java.io.StringWriter

@RestController
class SnowboarderController(val service: SnowboarderService) {
    @GetMapping
    fun home(): String {
        return renderTemplate("home")
    }

    @GetMapping("/snowboarders")
    fun index(): String {
        val snowboarders = mapOf("snowboarders" to service.findSnowboarders())
        return renderTemplate("snowboarders_list", snowboarders)
    }

    @PostMapping("/snowboarders")
    fun post(@RequestBody snowboarder: Snowboarder) {
        service.post(snowboarder)
    }

    private fun renderTemplate(templateName:String, templateArguments:Map<String, List<Any>>?=null):String {
        val template = PebbleEngine.Builder().build().getTemplate("templates/$templateName.peb")
        val writer = StringWriter()
        template.evaluate(writer, templateArguments)
        return writer.toString()
    }

}