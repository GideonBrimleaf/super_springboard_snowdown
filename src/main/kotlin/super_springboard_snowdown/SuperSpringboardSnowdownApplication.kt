package super_springboard_snowdown

import com.mitchellbosecke.pebble.PebbleEngine
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import super_springboard_snowdown.kotlin_extensions.uuid
import java.io.StringWriter

@SpringBootApplication
class SuperSpringboardSnowdownApplication

fun main(args: Array<String>) {
	runApplication<SuperSpringboardSnowdownApplication>(*args)
}

@RestController
class MessageResource(val service: MessageService) {
	@GetMapping
	fun home(): List<Message> {
		return service.findMessages()
	}

	@GetMapping("{id}")
	fun show(@PathVariable id: String): List<Message> {
		return service.findMessagesById(id)
	}

	@GetMapping("/messages")
	fun index(): String {
		val messages = mapOf("messages" to service.findMessages())
		return renderTemplate("messages_list", messages)
	}

	@GetMapping("/selector")
	fun staticTemplate(): String {
		return renderTemplate("selector")
	}

	@PostMapping
	fun post(@RequestBody message: Message) {
		service.post(message)
	}

	private fun renderTemplate(templateName:String, templateArguments:Map<String, List<Any>>?=null):String {
		val template = PebbleEngine.Builder().build().getTemplate("templates/$templateName.peb")
		val writer = StringWriter()
		template.evaluate(writer, templateArguments)
		return writer.toString()
	}

}

@Service
class MessageService(val db: JdbcTemplate) {

	fun findMessages(): List<Message> = db.query("SELECT * FROM messages") { resSet, _ ->
		Message(resSet.getString("id"), resSet.getString("text"))
	}

	fun findMessagesById(id:String): List<Message> = db.query("SELECT * FROM messages WHERE id = ?", id) {
			resSet, _ -> Message(resSet.getString("id"), resSet.getString("text"))
	}

	fun post(message: Message){
		db.update("INSERT INTO messages VALUES (?, ?)",
			message.id ?: message.text.uuid(), message.text
		)
	}
}

data class Message(val id: String?, val text: String)