package ims;

import java.util.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Callback;

/**
 *
 * @author Taylor, Ryan G
 */
public class Inhouse extends Part {

    private final SimpleIntegerProperty partID;
    private final SimpleStringProperty partName;
    private final SimpleIntegerProperty partInstock;
    private final SimpleDoubleProperty partPrice;
    private final SimpleIntegerProperty partMin;
    private final SimpleIntegerProperty partMax;
    private final SimpleIntegerProperty partSource;
    
    public Inhouse(){
        this(0,"",0,0d,0,0,0);
    }
    
    public Inhouse(int partID, String partName, int partInstock, Double partPrice, int partMax, int partMin, int partSource){
        System.out.println("A new Inhouse Object was just created");
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

    public SimpleIntegerProperty partIDProperty(){
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

    public SimpleStringProperty partNameProperty(){
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

    public SimpleDoubleProperty priceProperty(){
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

    public SimpleIntegerProperty instockProperty(){
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

    public SimpleIntegerProperty minProperty(){
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

    public SimpleIntegerProperty maxProperty(){
        return partMax;
    }
/**********************************************
    Part MachineID
*/
    public int getMachineID(){
        return partSource.get();
    }
    
    public void setMachineID(int machID){
        partSource.set(machID);
    }

    public SimpleIntegerProperty machineIDProperty(){
        return partSource;
    }
    
}
