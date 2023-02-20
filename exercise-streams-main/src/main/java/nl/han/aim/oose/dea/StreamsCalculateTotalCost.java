package nl.han.aim.oose.dea;

import nl.han.aim.oose.dea.helpers.Product;

import java.util.*;

public class StreamsCalculateTotalCost {
    int calculateTotalCostOfAllProducts(List<Product> products) {
        // TODO: implement
        ArrayList<Integer> productPrices = new ArrayList();
        products.stream().forEach((product)->{ productPrices.add(product.getPrice());});
        return productPrices.stream().reduce(0, (subtotal , product)->
            subtotal + product);
    }

    int calculateTotalCostOfAllGadgets(List<Product> products) {
        // TODO: implement

        return 0;
    }
}
