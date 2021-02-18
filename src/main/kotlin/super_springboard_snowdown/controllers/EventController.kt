package super_springboard_snowdown.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
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
}