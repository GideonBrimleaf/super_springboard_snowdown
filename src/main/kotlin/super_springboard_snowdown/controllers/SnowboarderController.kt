package super_springboard_snowdown.controllers

import org.springframework.web.bind.annotation.*
import super_springboard_snowdown.models.Snowboarder
import super_springboard_snowdown.repositories.SnowboarderService

@RestController
class SnowboarderController(val service: SnowboarderService) {
    @GetMapping("/snowboarders")
    fun index(): String {
        val snowboarders = mapOf("snowboarders" to service.findSnowboarders())
        return renderTemplate("snowboarders_list", snowboarders)
    }

    @GetMapping("/snowboarders/{id}")
    fun show(@PathVariable id:String):String {
        val snowboarder = mapOf("snowboarder" to service.findSnowboarderById(id).get())
        return renderTemplate("snowboarder_show", snowboarder)
    }

    @PostMapping("/snowboarders")
    fun post(@RequestBody snowboarder: Snowboarder) {
        service.post(snowboarder)
    }
}