package super_springboard_snowdown

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import super_springboard_snowdown.models.Snowboarder

class SnowboarderTest: StringSpec() {
    private val craigMcMo = Snowboarder(
        name = "Craig McMorris",
        age = 29, profile = "https://upload.wikimedia.org/wikipedia/commons/6/65/Craig_McMorris_%282011%29.jpg"
    )

    init {
        "Snowboarder should have name, age and img url" {
            craigMcMo.name shouldBe "Craig McMorris"
            craigMcMo.age shouldBe 29
            craigMcMo.profile shouldBe "https://upload.wikimedia.org/wikipedia/commons/6/65/Craig_McMorris_%282011%29.jpg"
        }
    }
}