package nl.han.ica.oose.dea.services;

import nl.han.ica.oose.dea.services.dto.*;

import java.util.*;

public interface ItemService {
    List<ItemDTO> getAll();

    void addItem(ItemDTO itemDTO);

    ItemDTO getItem(int id);

    void deleteItem(int id);
}
