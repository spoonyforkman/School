package ims;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author Taylor, Ryan G
 */
public class IMS extends Application {
    private ObservableList<Part> partsList = FXCollections.observableArrayList();
    private ObservableList<Product> productList = FXCollections.observableArrayList();
    private ObservableList<Part> productPartList = FXCollections.observableArrayList();
    private int modPartIndex, modifiedProductIndex, ProductPartsIndex;
    
    public IMS(){
        System.out.println("IMS_Constructor>> modPartIndex: " + modPartIndex);
        partsList.add(new Inhouse(1000, "Test Part 1", 29, 12.95,55,0,101923));
        partsList.add(new Inhouse(1001, "Test Part 2", 12, 7.49d, 100, 5, 109292));
        partsList.add(new Outsourced(1002, "Test Part 3", 3, 1.62d, 200, 2, 191983));
        
        System.out.println("IMS_Constructor>> modProdIndex: " + modifiedProductIndex);
        ArrayList<Part> tempList = new ArrayList<>();
        ArrayList<Part> tempList2 = new ArrayList<>();
            tempList.add(new Inhouse(1001, "Test Part 2", 12, 7.49d, 100, 5, 109292));
        productList.add(new Product(100, "Test Product 1",33, 23.85, 100, 3, tempList));
            tempList2.add(new Outsourced(1002, "Test Part 3", 3, 1.62d, 200, 2, 191983));
            tempList2.add(new Inhouse(1001, "Test Part 2", 12, 7.49d, 100, 5, 109292));
        productList.add(new Product(101, "Test Product 2", 18, 49.56, 100, 0, tempList2));
            System.out.println("IMS_Constructor Mod>> :" + productList.get(1).getPart().size());
    }
    
    public ObservableList<Part> partRetList(){
        return partsList;
    }
    
    public ObservableList<Product> prodRetList(){
        return productList;
    }

    public ObservableList<Part> prodpartRetList(){
        return productPartList;
    }
    
    @Override
    public void start(Stage stage) throws Exception {

        showMainDialog(stage);
    }

    public void showMainDialog(Stage stage) throws IOException{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(IMS.class.getResource("Main_FXML.fxml"));

        Parent root = (Parent) loader.load();

        // Give the controller access to the main app.
        Main_FXMLController controller = loader.getController();
        System.out.println("Controller: " + controller);
        controller.setMainApp(this);
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
    }
    
    public void showPartsDialog() throws IOException{
            
        if(modPartIndex == -1){
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(IMS.class.getResource("Part_FXML.fxml"));

            Parent root = (Parent) loader.load();

            // Give the controller access to the main app.
            Main_FXMLController controller = loader.getController();
            System.out.println("Controller: " + controller);
            controller.setMainApp(this);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("My modal window");
            stage.showAndWait();
        } else if(modPartIndex > -1){
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(IMS.class.getResource("Part_Modify_FXML.fxml"));

            Parent root = (Parent) loader.load();

            // Give the controller access to the main app.
            Part_Modify_FXMLController controller = loader.getController();
            System.out.println("Controller: " + controller);
            controller.setMainApp(this);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("My modal window");
            stage.showAndWait();
            modPartIndex=-1;
        }
    }
    

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public int getModPartIndex(){
        return modPartIndex;
    }
    
    public void setModPartsIndex(int index){
        modPartIndex=index;
    }
    
    void setProductIndex(int index) {
        modifiedProductIndex=index;
    }

    int getProductIndex(){
        return modifiedProductIndex;
    }
    
    void setProductPartsIndex(int index) {
        ProductPartsIndex=index;
    }
    
    int getProductPartsIndex() {
        return ProductPartsIndex;
    }    
    
    void showProductsDialog() throws IOException {
        if(modifiedProductIndex == -1){
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(IMS.class.getResource("Products_Add_FXML.fxml"));

            Parent root = (Parent) loader.load();

            // Give the controller access to the main app.
            Products_Add_Controller controller = loader.getController();
            System.out.println("Controller: " + controller);
            controller.setMainApp(this);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Add Product");
            stage.showAndWait();
        } else if(modifiedProductIndex > -1){
           Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(IMS.class.getResource("Products_Modify_FXML.fxml"));

            Parent root = (Parent) loader.load();

            // Give the controller access to the main app.
            Products_Modify_Controller controller = loader.getController();
            System.out.println("Controller: " + controller);
            controller.setMainApp(this);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Modify Product");
            stage.showAndWait();
        }
    }

    public void addToProdPartList(Part part){
        this.productPartList.add(part);
    }
    
    public void removeFromProdPartList(Part part){
        this.productPartList.remove(ProductPartsIndex);
    }
    
    
    public void errors(String error, String errorSource){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText(error);
        alert.setContentText(errorSource);
        alert.showAndWait();
    }
    
    public ObservableList<Part> getPartsList() {
        return partsList;
    }

    public void setPartsList(ObservableList<Part> partsList) {
        this.partsList = partsList;
    }

    public ObservableList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ObservableList<Product> productList) {
        this.productList = productList;
    }

    public ObservableList<Part> getProductPartList() {
        return productPartList;
    }

    public void setProductPartList(ObservableList<Part> productPartList) {
        this.productPartList = productPartList;
    }
    
}
