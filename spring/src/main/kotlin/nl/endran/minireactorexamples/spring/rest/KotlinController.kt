package nl.endran.minireactorexamples.spring.rest

import io.reactivex.Observable
import nl.endran.minireactor.MiniReactor
import nl.endran.minireactorexamples.spring.SomeRequest
import nl.endran.minireactorexamples.spring.SomeResponse
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kotlin")
class KotlinController(private val miniReactor: MiniReactor) {

    var count = 0

    @RequestMapping(path = arrayOf("/hello"), method = arrayOf(RequestMethod.GET))
    fun hello(): Observable<SomeResponse> {
        return miniReactor.lurkAndDispatch(SomeResponse::class.java, SomeRequest("Hello Kotlin request!!! (${count++})"))
                .take(1).toObservable()
    }

    @RequestMapping(path = arrayOf("/hi"), method = arrayOf(RequestMethod.GET))
    fun hi(): Observable<SomeResponse> {
        return miniReactor.lurkAndDispatch(SomeResponse::class.java, SomeRequest("Hi Kotlin request :) (${count++})"))
                .take(1).toObservable()
    }

    @RequestMapping(path = arrayOf("/overload"), method = arrayOf(RequestMethod.GET))
    fun overload(): Observable<SomeResponse> {
        val max = 10000L
        val myCount = count++
        return miniReactor.lurker(SomeResponse::class.java)
                .doOnSubscribe {
                    for (i in 0..max) {
                        miniReactor.dispatch(SomeRequest("Overloading request ($myCount:$i)"), "OverloadingRequest")
                    }
                }
                .take(max).toObservable()
    }
}
