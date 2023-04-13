package home.test.kotlinSpring.services

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FirstTransUseCase {

    @Transactional
    fun execute() {

    }

}
