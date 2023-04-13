package home.test.kotlinSpring.dataproviders.testfirst

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "test_first")
open class SecondEntity {

    @field:Id
    @field:Column(name = "id")
    lateinit var id: UUID

    @field:Column(name = "field")
    lateinit var field: String

}
