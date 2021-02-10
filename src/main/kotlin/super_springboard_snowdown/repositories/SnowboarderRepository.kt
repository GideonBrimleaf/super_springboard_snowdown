package super_springboard_snowdown.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import super_springboard_snowdown.models.Snowboarder
import java.util.*

@Service
class SnowboarderService(val db: SnowboarderRepository) {

    fun findSnowboarders(): List<Snowboarder> = db.findAll()

    fun findSnowboarderById(id:String): Optional<Snowboarder> = db.findById(id)

    fun post(snowboarder: Snowboarder){
        db.save(snowboarder)
    }
}

interface SnowboarderRepository : JpaRepository<Snowboarder, String> {

//    @Query("SELECT * FROM snowboarders")
//    fun findAllSnowboarders(): List<Snowboarder>
}