package super_springboard_snowdown.controllers

import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView
import super_springboard_snowdown.models.Snowboarder
import super_springboard_snowdown.repositories.SnowboarderService

@RestController
class SnowboarderController(val snowboarderService: SnowboarderService): ApplicationController() {
    @GetMapping("/snowboarders")
    fun index(): String {
        val snowboarders = mapOf("snowboarders" to snowboarderService.findSnowboarders())
        return renderTemplate("snowboarders_list", snowboarders)
    }

    @GetMapping("/snowboarders/{id}")
    fun show(@PathVariable id:String):String {
        val snowboarder = mapOf("snowboarder" to snowboarderService.findSnowboarderById(id).get())
        return renderTemplate("snowboarder_show", snowboarder)
    }

    @PostMapping("/snowboarders")
    fun create(@RequestBody formData:MultiValueMap<String, String>):RedirectView {
//      This way is more flexible than straight up mapping the model attribute as you can
//      capture the form values and create an object manually
        val name = formData["name"]!!.first()
        val age = formData["age"]!!.first().toInt()
        val profile = formData["profile"]!!.first()

        val newSnowboarder = Snowboarder(name = name, age = age, profile = profile)
        snowboarderService.post(newSnowboarder)
        return RedirectView("/snowboarders")
    }

    @PostMapping("/api/snowboarders")
    fun post(@RequestBody snowboarder: Snowboarder) {
        snowboarderService.post(snowboarder)
    }
}