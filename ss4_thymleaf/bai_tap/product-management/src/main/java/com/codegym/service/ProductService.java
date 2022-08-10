package com.codegym.service;

import com.codegym.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductService implements IProductService{
    private static List<Product> products;

    static {
        products.add(new Product(1,"Samsung",5.0,"ok","VN"));
        products.add( new Product(2,"Apple",9.0,"ok","USA"));
        products.add( new Product(3,"Xiaomi",6.0,"ok","CN"));
        products.add( new Product(4,"Oppo",5.0,"improve","CN"));
        products.add( new Product(5,"Sony",7.0,"ok","JP"));
        products.add( new Product(6,"Huawei",6.0,"ok","CN"));
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }


    @Override
    public Product findById(int id) {
        return products.stream().filter(e -> e.getId() == id).findFirst().get();
    }

    @Override
    public void update(int id, Product product) {
            for(int i=0; i < products.size(); i++){
                if(products.get(i).getId() == id){
                    products.set(i, product);
                }
            }
    }

    @Override
    public void remove(int id) {
        products.removeIf(e->e.getId() ==id);
    }

    @Override
    public List<Product> search(String name) {
        return products.stream().filter(e->e.getName().contains(name)).collect(Collectors.toList());
    }
}
