package finalexam;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import java.net.URI;

public class PlayerOrTeamNotFoundException extends AbstractThrowableProblem {

    public PlayerOrTeamNotFoundException(String entity) {
        super(URI.create("entity-not-found"),
                entity + " not found",
                Status.NOT_FOUND,
                entity + " not found"
        );
    }
}
