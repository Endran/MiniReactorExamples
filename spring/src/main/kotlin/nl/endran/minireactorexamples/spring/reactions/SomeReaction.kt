package nl.endran.minireactorexamples.spring.reactions

import nl.endran.minireactor.MiniReactor
import nl.endran.minireactorexamples.spring.SomeRequest
import nl.endran.minireactorexamples.spring.SomeResponse
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class SomeReaction(private val miniReactor: MiniReactor) {

    @PostConstruct
    fun start() {
        miniReactor.reaction(SomeRequest::class.java) {
            it.map { it.toString() }
                    .map { SomeResponse("Kotlin says: $it") }
        }
    }
}
