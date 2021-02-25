package ru.pentragon.spring1l4.repo;

//import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import ru.pentragon.spring1l4.model.Product;


//import javax.annotation.PostConstruct;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
//@Primary
public class ProductRepo implements MyRepository{
    private List<Product> productList;

    @PostConstruct
    public void init(){
        System.out.println("Post construct is completed!");
        productList = new ArrayList<>();
        productList.add(new Product(1L, "Apple", 12.3f));
        productList.add(new Product(2L,"Orange", 16.4f));
        productList.add(new Product(3L, "Sweet Roll", 5.21f));
        productList.add(new Product(4L, "Moon Sugar", 66.3f));
        productList.add(new Product(5L, "Skooma", 77.88f));
    }

    @Override
    public List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    @Override//create
    public void add(Product product){
        productList.add(product);
    }

    @Override//read
    public Optional<Product> getByID(Long id){
//        for (Product product : productList) {
//            if (product.getId()== id) return product;
//        }
//        return null;
        System.out.println(productList.stream().filter(product -> product.equals(id)).findFirst());
        return productList.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    @Override//update
    public void updateRecordByID(Long id, String newTitle, float newCost){
        for (Product product : productList) {
            if (product.getId()== id){
                product.setTitle(newTitle);
                product.setCost(newCost);
                return;
            }
        }
    }

    @Override//delete
    public void deleteRecordByID(Long id){
        for (Product product : productList) {
            if (product.getId()== id){
                productList.remove(product);
                return;
            }
        }
    }
}
