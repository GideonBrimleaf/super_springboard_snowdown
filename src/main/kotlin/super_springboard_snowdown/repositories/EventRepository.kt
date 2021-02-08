package super_springboard_snowdown.repositories

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import super_springboard_snowdown.models.Event

@Service
class EventService(val db:EventRepository) {
    fun findEvents(): List<Event> = db.findEvents()

    fun saveEvent(event:Event) = db.save(event)
}


interface EventRepository: CrudRepository<Event, String> {

    @Query("select * from events")
    fun findEvents(): List<Event>
}