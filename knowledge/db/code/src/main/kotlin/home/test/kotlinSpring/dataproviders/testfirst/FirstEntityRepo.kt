package home.test.kotlinSpring.dataproviders.testfirst

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface FirstEntityRepo : JpaRepository<FirstEntity, UUID>
