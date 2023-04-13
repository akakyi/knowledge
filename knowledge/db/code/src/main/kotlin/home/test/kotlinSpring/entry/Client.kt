package home.test.kotlinSpring.entry

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Client {

    @GetMapping("/test1")
    fun test1() {

    }

}