package super_springboard_snowdown

import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import super_springboard_snowdown.models.Event
import super_springboard_snowdown.models.Snowboarder
import super_springboard_snowdown.repositories.SnowboarderService

@SpringBootTest
class SnowboarderTest(private val snowboarderService: SnowboarderService): StringSpec() {
    lateinit var craigMcMo:Snowboarder
    lateinit var railsJam: Event

    override fun beforeEach(testCase: TestCase) {
        craigMcMo = Snowboarder(
            name = "Craig McMorris",
            age = 29, profile = "https://upload.wikimedia.org/wikipedia/commons/6/65/Craig_McMorris_%282011%29.jpg"
        )
        railsJam = Event(
            name = "Rails Jam",
            location = "Buttermilk Mountain",
            prizeMoney = 9000
        )
    }

    init {
        "Snowboarder should have name, age and img url" {
            craigMcMo.name shouldBe "Craig McMorris"
            craigMcMo.age shouldBe 29
            craigMcMo.profile shouldBe
                    "https://upload.wikimedia.org/wikipedia/commons/6/65/Craig_McMorris_%282011%29.jpg"
            craigMcMo.id shouldBe null
        }

        "Can add events to snowboarder" {
            craigMcMo.events.add(railsJam)
            craigMcMo.events.size shouldBe 1
        }

        "Snowboarder should start with no events" {
            craigMcMo.events.size shouldBe 0
        }

        "Can save Craig to DB, should have id" {
            snowboarderService.post(craigMcMo)
            craigMcMo.id shouldNotBe null
        }
    }
}