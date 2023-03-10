package han.stijn.spotitube.exception.mapper;

import han.stijn.spotitube.exception.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.*;

public class IncompleteQueryResultExceptionMapper implements ExceptionMapper<IncompleteQueryResultException> {
    @Override
    public Response toResponse(IncompleteQueryResultException e) {
        return Response.status(500).build();
    }
}
