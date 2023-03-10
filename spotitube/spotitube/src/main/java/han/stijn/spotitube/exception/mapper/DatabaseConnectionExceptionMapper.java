package han.stijn.spotitube.exception.mapper;

import han.stijn.spotitube.exception.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.*;

public class DatabaseConnectionExceptionMapper implements ExceptionMapper<DatabaseConnectionException> {
    @Override
    public Response toResponse(DatabaseConnectionException e) {
        return Response.status(500).build();
    }
}
