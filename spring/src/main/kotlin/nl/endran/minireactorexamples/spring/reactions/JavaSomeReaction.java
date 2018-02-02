package nl.endran.minireactorexamples.spring.reactions;

import nl.endran.minireactor.MiniReactor;
import nl.endran.minireactorexamples.spring.SomeJavaRequest;
import nl.endran.minireactorexamples.spring.SomeJavaResponse;
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
        miniReactor.reaction(SomeJavaRequest.class, o -> o
                .map(SomeJavaRequest::toString)
                .map(s -> new SomeJavaResponse("Java says: " + s)));
    }
}
