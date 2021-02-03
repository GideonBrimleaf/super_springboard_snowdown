package super_springboard_snowdown

import com.mitchellbosecke.pebble.PebbleEngine
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import org.springframework.ui.Model
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
class MessageResource(val service: MessageService) {
	@GetMapping
	fun index(): List<Message> {
		return service.findMessages()
	}

	@GetMapping("/messages")
	fun messageTemplate(): String {
		val messages = mapOf("messages" to service.findMessages())
		return renderTemplate("messages_list", messages)
	}

	@PostMapping
	fun post(@RequestBody message: Message) {
		service.post(message)
	}

	private fun renderTemplate(templateName:String, templateArguments:Map<String, List<Any>> = mapOf("None" to listOf())):String {
		val template = PebbleEngine.Builder().build().getTemplate("templates/$templateName.peb")
		val writer = StringWriter()
		template.evaluate(writer, templateArguments)
		return writer.toString()
	}

}

@Service
class MessageService(val db: MessageRepository) {

	fun findMessages(): List<Message> = db.findMessages()

	fun post(message: Message){
		db.save(message)
	}
}

interface MessageRepository : CrudRepository<Message, String>{

	@Query("select * from messages")
	fun findMessages(): List<Message>
}

@Table("MESSAGES")
data class Message(@Id val id: String?, val text: String)