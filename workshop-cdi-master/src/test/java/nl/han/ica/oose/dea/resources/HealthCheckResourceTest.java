package nl.han.ica.oose.dea.resources;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
public class HealthCheckResourceTest {

    @Test
    void checkReturnValueHealthy(){
        //Arange
        var healthCheckResource = new HealthCheckResource();
        String testValue = "Up & Running";
        //ACT

        //Assert
        assertEquals(testValue,healthCheckResource.healthy());
    }
}
