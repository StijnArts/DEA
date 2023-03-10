package han.stijn.spotitube.exception.mapper;

import han.stijn.spotitube.exception.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.*;

public class TokenNotSetExceptionMapper implements ExceptionMapper<TokenNotSetException> {
    @Override
    public Response toResponse(TokenNotSetException throwable) {
        return Response.status(401).build();
    }
}
