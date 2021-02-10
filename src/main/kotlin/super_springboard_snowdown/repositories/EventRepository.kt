package super_springboard_snowdown.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import super_springboard_snowdown.models.Event

// We can't straight up use the CrudRepository interface directly
// So we need this even if it's empty
interface EventRepository: JpaRepository<Event, String>