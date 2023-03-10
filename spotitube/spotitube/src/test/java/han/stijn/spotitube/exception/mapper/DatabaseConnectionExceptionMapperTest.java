package han.stijn.spotitube.exception.mapper;

import han.stijn.spotitube.exception.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
public class DatabaseConnectionExceptionMapperTest {

    DatabaseConnectionExceptionMapper sut;

    @Test
    public void responseOnExceptionThrown(){
        //Arrange
        DatabaseConnectionException exception = new DatabaseConnectionException();
        sut = new DatabaseConnectionExceptionMapper();
        Response testResponse = Response.status(500).build();
        Response sutResponse;
        //Act
        sutResponse = sut.toResponse(exception);
        //Assert
        assertEquals(testResponse.getStatus(),sutResponse.getStatus());
    }
}
