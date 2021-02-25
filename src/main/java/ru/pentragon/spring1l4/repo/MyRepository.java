package ru.pentragon.spring1l4.repo;


import ru.pentragon.spring1l4.model.Product;

import java.util.List;
import java.util.Optional;

public interface MyRepository {

    List<Product> getProductList();

    void add(Product product);

    void updateRecordByID(Long id, String newTitle, float newCost);

    Optional<Product> getByID(Long id);

    void deleteRecordByID(Long id);

}
