package nl.endran.minireactorexamples.spring.reactions

import nl.endran.minireactor.MiniReactor
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class LoggingReaction(private val miniReactor: MiniReactor) {

    @PostConstruct
    fun start() {
        miniReactor.lurker(Object::class.java)
                .subscribe {
                    System.out.println("Reactor: $it")
                }
    }
}
