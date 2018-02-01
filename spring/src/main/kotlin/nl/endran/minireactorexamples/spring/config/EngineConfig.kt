package nl.endran.minireactorexamples.spring.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.reactivex.internal.schedulers.SingleScheduler
import io.reactivex.plugins.RxJavaPlugins
import nl.endran.minireactor.MiniReactor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration


@Configuration
@ComponentScan(basePackages = arrayOf("nl.endran.minireactorexamples.spring"))
open class EngineConfig {

    @Bean
    open fun objectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()

        objectMapper.findAndRegisterModules()

        val javaTimeModule = JavaTimeModule()
        objectMapper.registerModule(javaTimeModule)

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)

        return objectMapper
    }

    @Bean
    open fun miniReactor(): MiniReactor {
        val reactorScheduler = RxJavaPlugins.onSingleScheduler( RxJavaPlugins.initSingleScheduler({ SingleScheduler() }));
        return MiniReactor(reactorScheduler)
//        return MiniReactor(Schedulers.computation())
    }
}
