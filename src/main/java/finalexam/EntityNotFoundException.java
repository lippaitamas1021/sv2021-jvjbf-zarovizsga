package finalexam;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import java.net.URI;

public class EntityNotFoundException extends AbstractThrowableProblem {

    public EntityNotFoundException(long id, String entity) {
        super(URI.create("not-found"),
                "Not found",
                Status.NOT_FOUND,
                String.format("Entity '%s' with id '%d' not found", entity, id));
    }
}
