package home.test.kotlinSpring.config

import com.atomikos.remoting.jaxrs.TransactionAwareRestClientFilter
import com.atomikos.remoting.spring.rest.TransactionAwareRestClientInterceptor
import com.atomikos.remoting.spring.rest.TransactionAwareRestContainerFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder

@Configuration
class Config {

//    @Bean
//    fun testClient() = ClientBuilder.newClient()
//        .also { it.register(TransactionAwareRestClientFilter::class) }

    @Bean
    fun testClient() = RestTemplate()
        .also { it.interceptors.add(TransactionAwareRestClientInterceptor()) }

    @Bean
    fun filter() = TransactionAwareRestContainerFilter()

}
