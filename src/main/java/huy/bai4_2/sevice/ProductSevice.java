package huy.bai4_2.sevice;

import huy.bai4_2.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductSevice implements IProductService{
    private List<Product>products=new ArrayList<>();
    @Override
    public List<Product> findAll() {
        return products ;
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {
        for(Product productT:products){
            if(productT.getId()==id){
                productT=product;
                break;
            }
        }    }

    @Override
    public void remove(int id) {
        for(int i=0;i<products.size();i++){
            if(products.get(i).getId()==id){
                products.remove(i);
                break;
            }
        }
    }
}
