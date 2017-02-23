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
public abstract class Part{
    private Integer partCount = new Integer(0);
    private IntegerProperty partID = new SimpleIntegerProperty();
    private StringProperty partName = new SimpleStringProperty();
    private IntegerProperty partInstock = new SimpleIntegerProperty();
    private DoubleProperty partPrice = new SimpleDoubleProperty();
    private IntegerProperty partMin = new SimpleIntegerProperty();
    private IntegerProperty partMax = new SimpleIntegerProperty();
    private StringProperty partSource = new SimpleStringProperty();
    
    public abstract int getPartID();
    
    public abstract void setPartID(int prtID);
    
    public abstract IntegerProperty partIDProperty();
    
    public abstract String getPartName();
    
    public abstract void setPartName(String prtName);
    
    public abstract StringProperty partNameProperty();
    
    public abstract int getInstock();
    
    public abstract void setInstock(int prtInv);
    
    public abstract IntegerProperty instockProperty();
    
    public abstract Double getPrice();
    
    public abstract void setPrice(Double prtPrice);

    public abstract DoubleProperty priceProperty();
    
    public abstract int getMin();
    
    public abstract void setMin(int prtMin);
    
    public abstract IntegerProperty minProperty();
    
    public abstract int getMax();
    
    public abstract void setMax(int prtMax);
    
    public abstract IntegerProperty maxProperty();
    
    
}
