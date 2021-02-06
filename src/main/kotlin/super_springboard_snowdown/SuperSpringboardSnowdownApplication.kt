package super_springboard_snowdown

import com.mitchellbosecke.pebble.PebbleEngine
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.io.StringWriter

@SpringBootApplication
class SuperSpringboardSnowdownApplication

fun main(args: Array<String>) {
	runApplication<SuperSpringboardSnowdownApplication>(*args)
}

@Service
class SnowboarderService(val db: SnowboarderRepository) {

	fun findSnowboarders(): List<Snowboarder> = db.findSnowboarders()

	fun post(snowboarder: Snowboarder){
		db.save(snowboarder)
	}
}

interface SnowboarderRepository : CrudRepository<Snowboarder, String>{

	@Query("select * from snowboarders")
	fun findSnowboarders(): List<Snowboarder>
}

@Table("SNOWBOARDERS")
data class Snowboarder(@Id val id: String?, val name: String, val age:Int, val profile:String)