package super_springboard_snowdown

import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import super_springboard_snowdown.models.Event
import super_springboard_snowdown.models.Snowboarder
import super_springboard_snowdown.repositories.EventRepository

@SpringBootTest
class EventTest(private val eventRepository: EventRepository): StringSpec() {
    lateinit var naturalSelection: Event

    override fun beforeEach(testCase: TestCase) {
        naturalSelection = Event(
            name = "Natural Selection",
            location = "Jackson Hole",
            prizeMoney = 200_000
        )
    }

    init {
        "Event should have name, location and prize money" {
            naturalSelection.name shouldBe "Natural Selection"
            naturalSelection.location shouldBe "Jackson Hole"
            naturalSelection.prizeMoney shouldBe 200_000
            naturalSelection.id shouldBe null
        }

        "Event should start off with no Snowboarders" {
            naturalSelection.snowboarders.size shouldBe 0
        }

        "Snowboarders can be added to events" {
            val bFerg = Snowboarder(
                name = "Ben Ferguson",
                age = 26,
                profile = "https://i.pinimg.com/474x/88/e2/ba/88e2baece5e84623de3e6aa1fe42b9ff.jpg"
            )
            naturalSelection.snowboarders.add(bFerg)
            naturalSelection.snowboarders.size shouldBe 1
        }

        "Can save event to DB, Id should not be null" {
            eventRepository.save(naturalSelection)
            naturalSelection.id shouldNotBe null
        }
    }
}