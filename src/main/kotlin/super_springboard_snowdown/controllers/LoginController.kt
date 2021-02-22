package super_springboard_snowdown.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController: ApplicationController() {
    @GetMapping("/login")
    fun login():String {
        return renderTemplate("login")
    }
}