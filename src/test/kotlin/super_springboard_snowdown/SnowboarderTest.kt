package super_springboard_snowdown

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import super_springboard_snowdown.models.Event
import super_springboard_snowdown.models.Snowboarder
import super_springboard_snowdown.repositories.EventRepository
import super_springboard_snowdown.repositories.SnowboarderService

@SpringBootTest
class SnowboarderTest(
    private val snowboarderService: SnowboarderService,
    private val eventRepository: EventRepository
): StringSpec() {
    private val craigMcMo = Snowboarder(
        name = "Craig McMorris",
        age = 29, profile = "https://upload.wikimedia.org/wikipedia/commons/6/65/Craig_McMorris_%282011%29.jpg"
    )

    private val railsJam = Event(
        name = "Rails Jam",
        location = "Buttermilk Mountain",
        prizeMoney = 9000
    )

    init {
        "Snowboarder should have name, age and img url" {
            craigMcMo.name shouldBe "Craig McMorris"
            craigMcMo.age shouldBe 29
            craigMcMo.profile shouldBe
                    "https://upload.wikimedia.org/wikipedia/commons/6/65/Craig_McMorris_%282011%29.jpg"
            craigMcMo.id shouldBe null
        }

        "Snowboarder should start with no events" {
            craigMcMo.events.size shouldBe 0
        }

        "Can add events to snowboarder" {
            craigMcMo.events.add(railsJam)
            craigMcMo.events.size shouldBe 1
        }

        "Can save Craig to DB, should have id" {
            eventRepository.save(railsJam)
            snowboarderService.post(craigMcMo)
            craigMcMo.id shouldNotBe null
        }

    }
}