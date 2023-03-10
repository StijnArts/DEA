package han.stijn.spotitube.exception;

import han.stijn.spotitube.exception.mapper.*;

import java.sql.*;

public class TokenNotSetException extends RuntimeException {
    public TokenNotSetException(){

    }
    public TokenNotSetException(String response, SQLException e){
        super(response,e);
    }
}
