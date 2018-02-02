package nl.endran.minireactorexamples.spring.reactions

import io.reactivex.schedulers.Schedulers
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
            it.observeOn(Schedulers.io())
                    .map { it.toString() }
                    .map {
                        Thread.sleep(100)
                        SomeResponse("Kotlin says: $it")
                    }
        }
    }
}
