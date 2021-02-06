package super_springboard_snowdown.repositories

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import super_springboard_snowdown.models.Snowboarder

@Service
class SnowboarderService(val db: SnowboarderRepository) {

    fun findSnowboarders(): List<Snowboarder> = db.findSnowboarders()

    fun post(snowboarder: Snowboarder){
        db.save(snowboarder)
    }
}

interface SnowboarderRepository : CrudRepository<Snowboarder, String> {

    @Query("select * from snowboarders")
    fun findSnowboarders(): List<Snowboarder>
}