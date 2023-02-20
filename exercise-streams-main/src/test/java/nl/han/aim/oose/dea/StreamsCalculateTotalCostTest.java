package nl.han.aim.oose.dea;

import nl.han.aim.oose.dea.helpers.Product;
import nl.han.aim.oose.dea.helpers.ProductCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;

class StreamsCalculateTotalCostTest {
    StreamsCalculateTotalCost sut;

    @BeforeEach
    void setUp() {
        sut = new StreamsCalculateTotalCost();
    }

    @Test
    void calculateTotalCostOfAllProducts_WithListOfProducts_ReturnsSumOfProductCost(){
        // Arrange
        var input = initialiseTestProductSet();

        // Act
        int totalValue = sut.calculateTotalCostOfAllProducts(input);

        // Assert
        Assertions.assertEquals(7286, totalValue);
    }

    @Test
    void calculateTotalCostOfAllGadgets_WithListOfProducts_ReturnsSumOfGadgetsCost() {
        // Arrange
        var input = initialiseTestProductSet();

        // Act
        int totalValue = sut.calculateTotalCostOfAllGadgets(input);

        // Assert
        Assertions.assertEquals(3886, totalValue);
    }

    private List<Product> initialiseTestProductSet() {
        final int PRICE_TV = 1200;
        final int PRICE_LAPTOP = 2300;
        final int PRICE_TABLET = 149;
        final int PRICE_PHONE = 237;
        final int PRICE_TABLE = 3400;

        var tv = new Product("TV", PRICE_TV);
        var laptop = new Product("Laptop", PRICE_LAPTOP);
        var tablet = new Product("tablet", PRICE_TABLET);
        var phone = new Product("phone", PRICE_PHONE);
        var table = new Product("table", PRICE_TABLE, ProductCategory.FURNITURE);

        return asList(tv, laptop, tablet, phone, table);
    }


}