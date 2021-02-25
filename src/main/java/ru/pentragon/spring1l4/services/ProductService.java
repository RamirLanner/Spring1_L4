package ru.pentragon.spring1l4.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.pentragon.spring1l4.model.Product;
import ru.pentragon.spring1l4.repo.MyRepository;


import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private MyRepository productRepository;

    @Value("12234-42342-6677766")
    private String identifier;

    public ProductService(MyRepository productRepository) {
        this.productRepository = productRepository;
    }

    public float calcAverageProductCost(){
        List<Product> productList= productRepository.getProductList();
        if(productList.size()==0) return 0;
        float avg = 0;
        for (Product product : productList) {
            avg+=product.getCost();
        }
        return avg/productList.size();
    }

    public int countProductListSize(){
        return productRepository.getProductList().size();
    }

    //create
    public void add(Product product){
        productRepository.add(product);
    }

    //read
    public Optional<Product> getByID(long id){
        return productRepository.getByID(id);
    }

    //update
    public void updateRecordByID(long id, String newTitle, float newCost){
        productRepository.updateRecordByID(id, newTitle, newCost);
    }

    //delete
    public void deleteRecordByID(long id){
        productRepository.deleteRecordByID(id);
    }

    public List<Product> getAllRecords(){
        return productRepository.getProductList();
    }

}
