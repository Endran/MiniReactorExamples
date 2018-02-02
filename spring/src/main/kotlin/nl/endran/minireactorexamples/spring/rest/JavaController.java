package nl.endran.minireactorexamples.spring.rest;

import io.reactivex.Observable;
import nl.endran.minireactor.MiniReactor;
import nl.endran.minireactorexamples.spring.SomeJavaRequest;
import nl.endran.minireactorexamples.spring.SomeJavaResponse;
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
    public Observable<SomeJavaResponse> hello() {
        return miniReactor.lurkAndDispatch(SomeJavaResponse.class, new SomeJavaRequest("Hello Java request!!! (" + (count++) + ")"))
                .take(1).toObservable();
    }


    @RequestMapping(path = "hi", method = RequestMethod.GET)
    public Observable<SomeJavaResponse> hi() {
        return miniReactor.lurkAndDispatch(SomeJavaResponse.class, new SomeJavaRequest("Hi Java request:) (" + (count++) + ")"))
                .take(1).toObservable();
    }
}
