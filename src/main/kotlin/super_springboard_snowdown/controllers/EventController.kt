package super_springboard_snowdown.controllers

import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView
import super_springboard_snowdown.models.Event
import super_springboard_snowdown.repositories.EventRepository
import super_springboard_snowdown.repositories.SnowboarderRepository

@RestController
class EventController(val eventRepository: EventRepository, val snowboarderRepository: SnowboarderRepository): ApplicationController() {
    @GetMapping("/events")
    fun index():String {
        val events = eventRepository.findAll()
        return renderTemplate("events_list", mapOf("events" to events))
    }

    @GetMapping("/events/{id}")
    fun show(@PathVariable id: Long): String {
        val event = eventRepository.findById(id).get()
        val snowboarders = snowboarderRepository.findAll()
        return renderTemplate("events_show", mapOf("event" to event, "snowboarders" to snowboarders))
    }

    @PostMapping("/events")
    fun create(@RequestBody formData: MultiValueMap<String, String>): RedirectView {
        val name = formData["name"]!!.first()
        val location = formData["location"]!!.first()
        val prizeMoney = formData["prize_money"]!!.first().toInt()

        val newEvent = Event(name = name, location = location, prizeMoney = prizeMoney)
        eventRepository.save(newEvent)
        return RedirectView("/events")
    }

    @PostMapping("/events/{id}/add-snowboarder")
    fun addSnowboarder(@PathVariable id:Long, @RequestBody formData: MultiValueMap<String, String>):RedirectView {
        val foundEvent = eventRepository.findById(id).get()
        val snowboarderId = formData["snowboarder_id"]!!.first().toLong()
        val foundSnowboarder = snowboarderRepository.findById(snowboarderId).get()

        foundEvent.snowboarders.add(foundSnowboarder)
        eventRepository.saveAndFlush(foundEvent)
        return RedirectView("/events/$id")
    }
}