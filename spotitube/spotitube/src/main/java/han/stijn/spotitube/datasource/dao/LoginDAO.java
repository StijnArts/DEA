package han.stijn.spotitube.datasource.dao;

import han.stijn.spotitube.datasource.*;
import han.stijn.spotitube.datasource.preparedstatement.*;
import han.stijn.spotitube.datasource.preparedstatement.parameter.*;
import han.stijn.spotitube.dto.*;
import han.stijn.spotitube.exception.*;
import jakarta.inject.*;
import org.apache.commons.codec.digest.*;

import java.sql.*;
import java.util.*;

public class LoginDAO extends DatabaseLogger {

    //Password for all test accounts is password123!
    private PreparedStatementHelper preparedStatementHelper;
    private ResultSetReader resultSetReader;
    private TokenGenerator tokenGenerator;

    public boolean checkLogin(LoginDTO loginDTO){
        try {
            String query = "SELECT [PASSWORD] AS [password] FROM [USER] WHERE [USERNAME] = ?";
            var parameters = new ArrayList<ISQLParameter>();
            parameters.add(SQLParameterFactory.createParameter(1,loginDTO.getUser()));
            ResultSet resultSet = preparedStatementHelper.executeQueryWithResultAndTransaction(query,parameters);
            while(resultSetReader.determineNextInResult(resultSet)) {
                if (DigestUtils.sha256Hex(loginDTO.getPassword()).equals(resultSetReader.readString(resultSet,"password"))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new UserNotFoundException("User not found in database.",e);
        }
        return false;
    }

    public UserDTO getUser(LoginDTO loginDTO) {
        putToken(loginDTO.getUser());
        var query = "SELECT FIRSTNAMELASTNAME AS [fullName], [TOKEN] AS [token] FROM [USER] WHERE [USERNAME] = ?";
        var parameters = new ArrayList<ISQLParameter>();
        parameters.add(SQLParameterFactory.createParameter(1,loginDTO.getUser()));
        var resultSet = preparedStatementHelper.executeQueryWithResultAndTransaction(query,parameters);
        try {
            while(resultSetReader.determineNextInResult(resultSet)) {
                    return new UserDTO(resultSetReader.readString(resultSet,"fullName"), resultSetReader.readString(resultSet,"token"));
            }
        } catch (SQLException e) {
            throw new UserNotFoundException("User not found in database.",e);
        }
        return null;
    }

    public void putToken(String username){
        String query = "UPDATE [USER] SET [TOKEN]=? WHERE [USERNAME] = ?";
        var parameters = new ArrayList<ISQLParameter>();
        parameters.add(SQLParameterFactory.createParameter(1,tokenGenerator.generateToken()));
        parameters.add(SQLParameterFactory.createParameter(2,username));
        preparedStatementHelper.executeQueryWithoutResultWithTransaction(query, parameters);
    }

    public boolean checkToken(String token){
        try {
            String query = "SELECT TOKEN FROM [USER] WHERE TOKEN = ?";
            var parameters = new ArrayList<ISQLParameter>();
            parameters.add(SQLParameterFactory.createParameter(1,token));
            ResultSet resultSet = preparedStatementHelper.executeQueryWithResultAndTransaction(query,parameters);
            return resultSetReader.determineNextInResult(resultSet);
        } catch (SQLException e) {
            throw new TokenNotSetException("Token was not set.",e);
        }
    }

    @Inject
    public void setTokenGenerator(TokenGenerator tokenGenerator) {
        this.tokenGenerator = tokenGenerator;
    }

    @Inject
    public void setResultSetReader(ResultSetReader resultSetReader) {
        this.resultSetReader = resultSetReader;
    }

    @Inject
    public void setPreparedStatementHelper(PreparedStatementHelper preparedStatementHelper) {
        this.preparedStatementHelper = preparedStatementHelper;
    }
}
