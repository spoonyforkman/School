package ims;

import java.util.ArrayList;

public class Inventory extends Product{
    static private ArrayList<Product> products = new ArrayList<>();

    
    void Inventory(Product product){
        
    }
    
    public void addProduct(Product product){
        products.add(product);
    }
    
    public boolean removeProduct(int productIndex){
        if(productIndex >= products.size()){
            return false;
        }else{
            products.remove(productIndex);
            return true;
        }
    }
    
    public Product lookupProduct(int productIndex){
        if(productIndex >= products.size()){
            return null;
        }else{
            return products.get(productIndex);
        }
    }
    
    public void updateProduct(int productIndex, Product newProduct){
        if(productIndex >= products.size()){
            
        } else {
            products.set(productIndex, newProduct);
        }
        
    }
    
}
