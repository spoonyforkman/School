package ims;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Taylor, Ryan G
 */
public class Outsourced extends Part {

    private final SimpleIntegerProperty partID;
    private final SimpleStringProperty partName;
    private final SimpleIntegerProperty partInstock;
    private final SimpleDoubleProperty partPrice;
    private final SimpleIntegerProperty partMin;
    private final SimpleIntegerProperty partMax;
    private final SimpleIntegerProperty partSource;
    
    public Outsourced(){
        this(0,"",0,0d,0,0,0);
    }
    
    public Outsourced(int partID, String partName, int partInstock, Double partPrice, int partMax, int partMin, int partSource){
        this.partID = new SimpleIntegerProperty(partID);
        this.partName = new SimpleStringProperty(partName);
        this.partInstock = new SimpleIntegerProperty(partInstock);
        this.partPrice = new SimpleDoubleProperty(partPrice);
        this.partMax = new SimpleIntegerProperty(partMax);
        this.partMin = new SimpleIntegerProperty(partMin);
        this.partSource = new SimpleIntegerProperty(partSource);

    }
    

/**********************************************
    Part ID
*/
    @Override
    public int getPartID() {
       return partID.get();
    }
    
    @Override
    public void setPartID(int prtID) {
        partID.set(prtID);
    }

    public IntegerProperty partIDProperty(){
        return partID;
    }
    
/**********************************************
    Part Name
*/
    @Override
    public String getPartName() {
        return partName.get();
    }

    @Override
    public void setPartName(String prtName) {
        partName.set(prtName);
    }

    public StringProperty partNameProperty(){
        return partName;
    }
/**********************************************
    Part Price
*/
    
    @Override
    public Double getPrice() {
        return partPrice.get();
    }

    @Override
    public void setPrice(Double prtPrice) {
        partPrice.set(prtPrice);
    }

    public DoubleProperty priceProperty(){
        return partPrice;
    }
/**********************************************
    Part Inventory
*/
    @Override
    public int getInstock() {
        return partInstock.get();
    }

    @Override
    public void setInstock(int prtInv) {
        partInstock.set(prtInv);
    }

    public IntegerProperty instockProperty(){
        return partInstock;
    }
/**********************************************
    Part Min
*/
    @Override
    public int getMin() {
        return partMin.get();
    }

    @Override
    public void setMin(int prtMin) {
        partMin.set(prtMin);
    }

    public IntegerProperty minProperty(){
        return partMin;
    }
/**********************************************
    Part Max
*/
    @Override
    public int getMax() {
        return partMax.get();
    }

    @Override
    public void setMax(int prtMax) {
        partMax.set(prtMax);
    }

    public IntegerProperty maxProperty(){
        return partMax;
    }
/**********************************************
    Part MachineID
*/
    public int getCompanyID(){
        return partSource.get();
    }
    
    public void setCompanyID(int machID){
        partSource.set(machID);
    }

    public IntegerProperty companyIDProperty(){
        return partSource;
    }
}
