package super_springboard_snowdown.repositories

import org.springframework.data.repository.CrudRepository
import super_springboard_snowdown.models.Event

interface EventRepository: CrudRepository<Event, String>