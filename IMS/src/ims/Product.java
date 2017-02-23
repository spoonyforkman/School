package ims;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product{
    private ArrayList<Part> part = new ArrayList<>();
    
    private SimpleIntegerProperty productID = new SimpleIntegerProperty();
    private SimpleStringProperty productName = new SimpleStringProperty();
    private SimpleIntegerProperty productInstock = new SimpleIntegerProperty();
    private SimpleDoubleProperty productPrice = new SimpleDoubleProperty();
    private SimpleIntegerProperty productMin = new SimpleIntegerProperty();
    private SimpleIntegerProperty productMax = new SimpleIntegerProperty();

    public Product(int productID, String productName, int productInstock, Double productPrice, int productMin, int productMax, List<Part> parts){
        System.out.println("A new Product Object was just created");
        this.productID = new SimpleIntegerProperty(productID);
        this.productName = new SimpleStringProperty(productName);
        this.productInstock = new SimpleIntegerProperty(productInstock);
        this.productPrice = new SimpleDoubleProperty(productPrice);
        this.productMax = new SimpleIntegerProperty(productMax);
        this.productMin = new SimpleIntegerProperty(productMin);
        this.part = new ArrayList<>(part);
    }
    
    public Product(){
        this(0,"",0,0d,0,0, new ArrayList<Part>());
    }
    
    public ArrayList<Part> getParts() {
        return part;
    }

    public void setParts(ArrayList<Part> products) {
        this.part = products;
    }
/**********************************************
    Product ID
*/
    public int getProductID() {
        return productID.get();
    }

    public void setProductID(int prdID) {
        productID.set(prdID);
    }

    public SimpleIntegerProperty productIDProperty(){
        return productID;
    }
    
/**********************************************
    Product Name
*/
    public String getProductName() {
        return productName.get();
    }

    public void setProductName(String name) {
        productName.set(name);
    }

    public SimpleStringProperty productNameProperty(){
        return productName;
    }
    
/**********************************************
    Product Instock
*/    
    public int getProductInstock() {
        return productInstock.get();
    }

    public void setProductInstock(int prdInstock) {
        productInstock.set(prdInstock);
    }

    public IntegerProperty productInstockProperty(){
        return productInstock;
    }
    
/**********************************************
    Product Price
*/    
    public Double getProductPrice() {
        return productPrice.get();
    }

    public void setProductPrice(Double prdPrice) {
        productPrice.set(prdPrice);
    }

    public DoubleProperty productPriceProperty(){
        return productPrice;
    }
    
/**********************************************
    Product Min
*/    
    public int getProductMin() {
        return productMin.get();
    }

    public void setProductMin(int productMin) {
        this.productMin.set(productMin);
    }

    public IntegerProperty productMinProperty(){
        return productMin;
    }
    
/**********************************************
    Product Max
*/
    public int getProductMax() {
        return productMax.get();
    }

    public void setProductMax(int productMax) {
        this.productMax.set(productMax);
    }
    
    public IntegerProperty productMaxProperty(){
        return productMax;
    }

/**********************************************
    Product Part
*/  
    public void addPart(Part part){
        this.part.add(part);
    }

    public boolean removePart(int productIndex){
        if(productIndex >= part.size()){
            return false;
        }else{
            part.remove(productIndex);
            return true;
        }
    }
    
    public Part lookupPart(int productIndex){
        if(productIndex >= part.size()){
            return null;
        }else{
            return part.get(productIndex);
        }
    }
    
    public void updatePart(int productIndex, Part product){
        if(productIndex >= part.size()){
        }else{
            part.set(productIndex, product);
        }
    }
    
}
