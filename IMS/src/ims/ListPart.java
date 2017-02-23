package ims;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Taylor, Ryan G
 */
public class ListPart{
    static ObservableList<Part> internalPartsList;
    
    public ListPart(){
        System.out.println("A new ListPart Obejct was created");
        internalPartsList = FXCollections.observableArrayList();
        internalPartsList.add(new Inhouse(1000, "Test Part 1", 29, 12.95,55,0,101923));
        internalPartsList.add(new Inhouse(1001, "Test Part 2", 12, 7.49d, 100, 5, 109292));
        internalPartsList.add(new Outsourced(1002, "Test Part 3", 3, 1.62d, 200, 2, 191983));
    }
    
    public void addPart(Part part){
        internalPartsList.add(part);
        System.out.println("partsList is now " + (internalPartsList.size()) + " items big");
    }

    public void delete(Part part){
        internalPartsList.remove(part);
        System.out.println("partsList is now " + (internalPartsList.size()) + "items big");
    }
    
    public Part search(Part part){
        return internalPartsList.get(internalPartsList.indexOf(part));
    }
    
    public void change(Part partOld, Part partNew){
        internalPartsList.get(internalPartsList.indexOf(partOld)).setPartID(partNew.getPartID());
        internalPartsList.get(internalPartsList.indexOf(partOld)).setPartName(partNew.getPartName());
        internalPartsList.get(internalPartsList.indexOf(partOld)).setInstock(partNew.getInstock());
        internalPartsList.get(internalPartsList.indexOf(partOld)).setPrice(partNew.getPrice());
    }
    public ObservableList<Part> retList(){
        return internalPartsList;
    }
    
}
