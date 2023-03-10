package han.stijn.spotitube.resource;


import han.stijn.spotitube.datasource.dao.*;
import han.stijn.spotitube.dto.*;
import jakarta.ws.rs.core.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;


public class LoginResourceTest {
    private LoginResource sut;
    private LoginDAO mockedLoginDAO;
    @BeforeEach
    public void setup(){
        sut = new LoginResource();
        this.mockedLoginDAO = Mockito.mock(LoginDAO.class);
        this.sut.setLoginDAO(mockedLoginDAO);
    }

    @Test
    public void executeBadLogin(){
        //Arrange
        Response testResponse = Response.status(403).build();
        LoginDTO testLoginDTO = new LoginDTO("melon", "MySuperSecretPassword12341");
        Mockito.when(mockedLoginDAO.checkLogin(testLoginDTO)).thenReturn(false);
        //Act

        //Assemble
        assertEquals(testResponse.getStatus(), sut.login(testLoginDTO).getStatus());
    }

    @Test
    public void executeGoodLogin(){
        //Arrange
        Response testResponse = Response.status(200).build();
        LoginDTO testLoginDTO = new LoginDTO("meron", "MySuperSecretPassword12341");
        UserDTO mockedUserDTO = new UserDTO("full name","1234-1234-1234");
        Mockito.when(mockedLoginDAO.getUser(testLoginDTO)).thenReturn(mockedUserDTO);
        Mockito.when(mockedLoginDAO.checkLogin(testLoginDTO)).thenReturn(true);
        //Act
        var sutStatus = sut.login(testLoginDTO).getStatus();
        var testStatus = testResponse.getStatus();
        //Assemble
        assertEquals(testStatus, sutStatus);
    }
}
