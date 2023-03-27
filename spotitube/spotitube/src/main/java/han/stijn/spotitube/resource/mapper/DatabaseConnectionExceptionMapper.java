package han.stijn.spotitube.resource.mapper;

import han.stijn.spotitube.exception.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.*;

@Provider
public class DatabaseConnectionExceptionMapper implements ExceptionMapper<DatabaseConnectionException> {
    @Override
    public Response toResponse(DatabaseConnectionException e) {
        return Response.status(500).build();
    }
}
