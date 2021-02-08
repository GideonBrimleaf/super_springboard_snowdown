package super_springboard_snowdown.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import super_springboard_snowdown.repositories.EventRepository

@RestController
class EventController(val eventRepository: EventRepository) {
    @GetMapping("/events")
    fun index():String {
        val events = eventRepository.findAll().map { it }
        return renderTemplate("events_list", mapOf("events" to events))
    }
}