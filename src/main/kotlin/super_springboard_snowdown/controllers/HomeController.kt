package super_springboard_snowdown.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController: ApplicationController() {
    @GetMapping
    fun home(): String {
        return renderTemplate("home")
    }
}