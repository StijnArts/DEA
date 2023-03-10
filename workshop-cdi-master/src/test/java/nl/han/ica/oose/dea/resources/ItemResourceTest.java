package nl.han.ica.oose.dea.resources;
import jakarta.ws.rs.core.*;
import nl.han.ica.oose.dea.services.*;
import nl.han.ica.oose.dea.services.dto.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
public class ItemResourceTest {

    private ItemResource sut;
    private ItemService mockedItemService;

    @BeforeEach
    void setup(){
        sut = new ItemResource();
        this.mockedItemService = Mockito.mock(ItemService.class);

        this.sut.setItemService(mockedItemService);
    }

    @Test
    void executeTestGetTextItems(){
        //Arrange
        String testValue = "bread, butter";

        //Act
        //Assert
        assertEquals(testValue,sut.getTextItems());
    }

    @Test
    void getJsonItemsCallsGetAll(){
        //Arrange

        //Act

        //Assert
    }

    @Test
    void executeTestAddItem(){
        //Arrange
        String[] categorien = {"fruit"};
        ItemDTO itemDTO = new ItemDTO(10,"appel",categorien,"appel");
        //Act
        var sutStatus = sut.addItem(itemDTO).getStatus();
        //Assert
        assertEquals(201,sutStatus);
    }

    @Test
    void executeTestGetItem(){
        //Arrange
        //Act
        var sutStatus = sut.getItem(1).getStatus();
        //Assert
        assertEquals(200,sutStatus);
    }

    @Test
    void executeTestDeleteItem(){
        //Arrange
        //Act
        var sutStatus = sut.deleteItem(1).getStatus();
        //Assert
        assertEquals(200,sutStatus);
    }
}
