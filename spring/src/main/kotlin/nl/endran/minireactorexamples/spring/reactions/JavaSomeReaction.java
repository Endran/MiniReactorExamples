package nl.endran.minireactorexamples.spring.reactions;

import nl.endran.minireactor.MiniReactor;
import nl.endran.minireactorexamples.spring.SomeRequest;
import nl.endran.minireactorexamples.spring.SomeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class JavaSomeReaction {

    final MiniReactor miniReactor;

    @Autowired
    public JavaSomeReaction(MiniReactor miniReactor) {
        this.miniReactor = miniReactor;
    }

    @PostConstruct
    public void start() {
        miniReactor.reaction(SomeRequest.class, o -> o
                .map(SomeRequest::toString)
                .map(s -> new SomeResponse("Java says: " + s)));
    }
}
