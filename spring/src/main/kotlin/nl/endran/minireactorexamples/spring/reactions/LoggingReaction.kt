package nl.endran.minireactorexamples.spring.reactions

import nl.endran.minireactor.MiniReactor
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class LoggingReaction(private val miniReactor: MiniReactor) {

    @PostConstruct
    fun start() {
        miniReactor.lurkerForSequences(Object::class.java)
                .subscribe {
                    System.out.println("Reactor: ${getId(it.first)} ${it.second}")
                }
    }

    companion object {
        val sixteenDots = "................"

        private fun getId(id: String): String {
            return if (id.length < 16) {
                "$id$sixteenDots:".substring(0, 16)
            } else {
                "$id:"
            }
        }
    }
}
