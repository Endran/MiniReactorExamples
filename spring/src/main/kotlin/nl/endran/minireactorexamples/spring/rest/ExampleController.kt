package nl.endran.minireactorexamples.spring.rest

import io.reactivex.Observable
import nl.endran.minireactor.MiniReactor
import nl.endran.minireactorexamples.spring.SomeRequest
import nl.endran.minireactorexamples.spring.SomeResponse
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class ExampleController(private val miniReactor: MiniReactor) {

    var count = 0

    @RequestMapping(path = arrayOf("/hello"), method = arrayOf(RequestMethod.GET))
    fun hello(): Observable<SomeResponse> {
        return miniReactor.lurkAndDispatch(SomeResponse::class.java, SomeRequest("Hello!!! (${count++})"))
                .take(1)
    }

    @RequestMapping(path = arrayOf("/hi"), method = arrayOf(RequestMethod.GET))
    fun hi(): Observable<SomeResponse> {
        return miniReactor.lurkAndDispatch(SomeResponse::class.java, SomeRequest("Hi :) (${count++})"))
                .take(1)
    }
}
