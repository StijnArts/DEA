package han.stijn.spotitube.exception.mapper;

import han.stijn.spotitube.exception.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.*;

public class PreparedStatementExceptionMapper implements ExceptionMapper<PreparedStatementException> {
    @Override
    public Response toResponse(PreparedStatementException e) {
        return Response.status(500).build();
    }
}
