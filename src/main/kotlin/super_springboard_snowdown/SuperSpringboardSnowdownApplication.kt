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

@RestController
class SnowboarderResource(val service: SnowboarderService) {
	@GetMapping
	fun home(): List<Snowboarder> {
		return service.findSnowboarders()
	}

	@GetMapping("/snowboarders")
	fun index(): String {
		val snowboarders = mapOf("snowboarders" to service.findSnowboarders())
		return renderTemplate("snowboarders_list", snowboarders)
	}

	@GetMapping("/selector")
	fun staticTemplate(): String {
		return renderTemplate("selector")
	}

	@PostMapping("/snowboarders")
	fun post(@RequestBody snowboarder: Snowboarder) {
		service.post(snowboarder)
	}

	private fun renderTemplate(templateName:String, templateArguments:Map<String, List<Any>>?=null):String {
		val template = PebbleEngine.Builder().build().getTemplate("templates/$templateName.peb")
		val writer = StringWriter()
		template.evaluate(writer, templateArguments)
		return writer.toString()
	}

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