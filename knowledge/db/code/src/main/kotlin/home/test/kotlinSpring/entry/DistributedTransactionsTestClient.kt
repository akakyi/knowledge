package home.test.kotlinSpring.entry

import home.test.kotlinSpring.dataproviders.testfirst.FirstEntity
import home.test.kotlinSpring.dataproviders.testfirst.FirstEntityRepo
import home.test.kotlinSpring.dataproviders.testfirst.SecondEntity
import home.test.kotlinSpring.dataproviders.testfirst.SecondEntityRepo
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.util.UUID

@RestController
class DistributedTransactionsTestClient(
    private val firstEntityRepo: FirstEntityRepo,
    private val secondEntityRepo: SecondEntityRepo,
    private val testClient: RestTemplate
) {

    @GetMapping("/test1")
    @Transactional
    fun test1() {
        firstEntityRepo.save(
            FirstEntity().apply {
                id = UUID.randomUUID()
                field = "test"
            }
        )
        val res = testClient.getForObject<Any>("http://localhost:8085/test2")
    }


    @GetMapping("/test2")
    @Transactional
    fun test2() {
        secondEntityRepo.save(
            SecondEntity().apply {
                id = UUID.randomUUID()
                field = "test"
            }
        )
    }

}