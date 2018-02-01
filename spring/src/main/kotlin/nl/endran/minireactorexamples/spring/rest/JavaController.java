package nl.endran.minireactorexamples.spring.rest;

import io.reactivex.Observable;
import nl.endran.minireactor.ConcreteMiniReactor;
import nl.endran.minireactor.MiniReactor;
import nl.endran.minireactorexamples.spring.SomeRequest;
import nl.endran.minireactorexamples.spring.SomeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/java")
public class JavaController {

    private MiniReactor miniReactor;
    private int count = 0;

    @Autowired
    JavaController(final MiniReactor miniReactor) {
        this.miniReactor = miniReactor;
    }

    @RequestMapping(path = "hello", method = RequestMethod.GET)
    public Observable<SomeResponse> hello() {
        return miniReactor.lurkAndDispatch(SomeResponse.class, new SomeRequest("Hello Java request!!! (" + (count++) + ")"), ConcreteMiniReactor.Companion.generateId())
                .take(2);
    }


    @RequestMapping(path = "hi", method = RequestMethod.GET)
    public Observable<SomeResponse> hi() {
        return miniReactor.lurkAndDispatch(SomeResponse.class, new SomeRequest("Hi Java request:) (" + (count++) + ")"), ConcreteMiniReactor.Companion.generateId())
                .take(2);
    }
}
