package super_springboard_snowdown.controllers

import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView
import super_springboard_snowdown.models.Snowboarder
import super_springboard_snowdown.repositories.EventRepository
import super_springboard_snowdown.repositories.SnowboarderService

@RestController
class SnowboarderController(val snowboarderService: SnowboarderService, val eventRepository: EventRepository):
    ApplicationController() {

    @GetMapping("/snowboarders")
    fun index(): String {
        val snowboarders = mapOf("snowboarders" to snowboarderService.findSnowboarders())
        return renderTemplate("snowboarders/snowboarders_list", snowboarders)
    }

    @GetMapping("/snowboarders/{id}")
    fun show(@PathVariable id:Long):String {
        val snowboarder = snowboarderService.findSnowboarderById(id).get()
        val events = eventRepository.findAll()
        return renderTemplate("snowboarders/snowboarder_show",
            mapOf("snowboarder" to snowboarder, "events" to events)
        )
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

    @PostMapping("/snowboarders/{id}/add-event")
    fun addEvent(@PathVariable id:Long, @RequestBody formData:MultiValueMap<String, String>):RedirectView {
        val foundSnowboarder = snowboarderService.findSnowboarderById(id).get()
        val eventId = formData["event_id"]!!.first().toLong()
        val foundEvent = eventRepository.findById(eventId).get()

        foundSnowboarder.events.add(foundEvent)
        snowboarderService.post(foundSnowboarder)
        return RedirectView("/snowboarders/$id")
    }

    @PostMapping("/api/snowboarders")
    fun post(@RequestBody snowboarder: Snowboarder) {
        snowboarderService.post(snowboarder)
    }
}