package nl.endran.minireactorexamples.spring.reactions

import nl.endran.minireactor.MiniReactor
import javax.annotation.PostConstruct

class LoggingReaction(private val miniReactor: MiniReactor) {

    @PostConstruct
    fun start() {
        miniReactor.lurker(Object::class.java)
                .subscribe {
                    System.out.println("Reactor: $it")
                }
    }
}
