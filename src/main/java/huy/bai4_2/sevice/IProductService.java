package huy.bai4_2.sevice;

import huy.bai4_2.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    void save(Product product);
    Product findById(int id);
    void update(int id,Product product);
    void remove(int id);
}
