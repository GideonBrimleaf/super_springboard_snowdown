package super_springboard_snowdown.seeds

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import super_springboard_snowdown.models.Snowboarder
import super_springboard_snowdown.repositories.SnowboarderService

@Component
class Seeds(val snowboarderService: SnowboarderService): ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        val maxParrot = Snowboarder(
            name = "Max Parrot",
            age = 30,
            profile = "https://i0.heartyhosting.com/www.dewtour.com/wp-content/uploads/2020/02/max_parrot_RESIZED.jpg")

        snowboarderService.post(maxParrot)
    }
}