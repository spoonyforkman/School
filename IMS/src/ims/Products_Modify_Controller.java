package ims;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Products_Modify_Controller {
    private IMS mainApp;
    private int intmodProdIndex;
    private int intmodProdPartIndex = -1;
    private Part tempPart, tempProdPart;
    private String tempPartName, tempProdPartName = new String();
    private ArrayList <Part> tempProductPartList = new ArrayList<>();
    
    
    @FXML
    public TextField txtProductID;
    @FXML
    public TextField txtProductInventory;
    @FXML
    public TextField txtProductMin;
    @FXML
    public TextField txtPartsSearch;
    @FXML
    private Button btnSaveProduct;
    @FXML
    public TextField txtProductName;
    @FXML
    private TableView<Part> tblParts;
    @FXML
    private TableColumn<Part, Number> colPartID;
    @FXML
    private TableColumn<Part, String> colPartName;
    @FXML
    private TableColumn<Part, Number> colPartInventory;
    @FXML
    private TableColumn<Part, Number> colPartPrice;
    @FXML
    private TableView<Part> tblProductParts;
    @FXML
    private TableColumn<Part, Number> colProdPartID;
    @FXML
    private TableColumn<Part, String> colProdPartName;
    @FXML
    private TableColumn<Part, Number> colProdPartInventory;
    @FXML
    private TableColumn<Part, Number> colProdPartPrice;
    @FXML
    private Button btnDeletePartFromProduct;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnPartSearch;
    @FXML
    public TextField txtProductMax;
    @FXML
    public TextField txtProductPrice;
    
    @FXML
    void initialize(){
    /**************************
                PARTS TABLE
    */
        // 0. Initialize the columns
        if(colPartID != null) {
            colPartID.setCellValueFactory(cellData -> cellData.getValue().partIDProperty());
            colPartName.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
            colPartInventory.setCellValueFactory(cellData -> cellData.getValue().instockProperty());
            colPartPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        }
    /*****************************
                PRODUCTSPARTS TABLE
    */
         //0. Initialize the columns
        if(colProdPartID != null) {
            colProdPartID.setCellValueFactory(cellData -> cellData.getValue().partIDProperty());
            colProdPartName.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
            colProdPartInventory.setCellValueFactory(cellData -> cellData.getValue().instockProperty());
            colProdPartPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        }

    }


    public void setMainApp(IMS mainApp) {
        this.mainApp = mainApp;

        /**************************
            PARTS TABLE
        */
        // 1. Wrap the ObservableList in a FilteredList (initially display all data)
        FilteredList<Part> filteredData = new FilteredList<>(mainApp.partRetList(), p -> true);

            // 2. Set the filter Predicate whenever the filter changes.
            txtPartsSearch.setOnKeyReleased(e -> {
                txtPartsSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Part>) part -> {
                        System.out.println("txtPartsSearch Listener Activated");
                        if(newValue == null || newValue .isEmpty()){
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        if(part.getPartName().toLowerCase().contains(lowerCaseFilter)){
                            return true;
                        } else if (Integer.toString(part.getPartID()).contains(newValue)){
                            return true;
                        }
                        return false;
                    });  
                });
                SortedList<Part> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tblParts.comparatorProperty());
                tblParts.setItems(sortedData);
            });

            TableView.TableViewSelectionModel<Part> tvModel = tblParts.getSelectionModel();    
            tvModel.selectedIndexProperty().addListener(new ChangeListener<Number>()
            {
                public void changed(ObservableValue<? extends Number> changed,
                        Number oldVal, Number newVal){
                    int prodIndex = (int)newVal;
                    intmodProdIndex = prodIndex;
                    mainApp.setModPartsIndex(prodIndex);
                    tempPartName = mainApp.partRetList().get(prodIndex).getPartName();
                    System.out.println("The Index for the part selected is " + mainApp.getModPartIndex());
                }    
            });
            
            tblParts.setItems(mainApp.partRetList());

        /**************************
            PRODUCTPARTS TABLE
        */
            
            TableView.TableViewSelectionModel<Part> tvProductPartModel = tblProductParts.getSelectionModel();    
            tvProductPartModel.selectedIndexProperty().addListener(new ChangeListener<Number>()
            {
                public void changed(ObservableValue<? extends Number> changed,
                        Number oldVal, Number newVal){
                    int index = (int)newVal;
                        intmodProdPartIndex = index;
                        mainApp.setProductPartsIndex(index);
                        tempProdPartName = mainApp.partRetList().get(index).getPartName();
                        System.out.println("The Index for the part selected is " + mainApp.getProductPartsIndex());

                }    
            });
            
            tblProductParts.setItems(this.mainApp.getProductPartList());
            showProductsDetails(this.mainApp.getProductIndex());
    }

    @FXML
    void addPartToProduct(){
        tempPart = searchForPart(this.tempPartName);
        if(tempPart instanceof Inhouse){
            mainApp.addToProdPartList(new Inhouse(
                    tempPart.getPartID(), tempPart.getPartName(), tempPart.getInstock(),
                    tempPart.getPrice(), tempPart.getMax(), tempPart.getMin(),
                    ((Inhouse) tempPart).getMachineID()
            ));
        } else if(tempPart instanceof Outsourced){
            mainApp.addToProdPartList(new Outsourced(
                    tempPart.getPartID(), tempPart.getPartName(), tempPart.getInstock(),
                    tempPart.getPrice(), tempPart.getMax(), tempPart.getMin(),
                    ((Outsourced) tempPart).getCompanyID()
            ));
        }
        
    }
    
    private Part searchForPart(String partName){
        for(int i = 0; i <= mainApp.partRetList().size(); i++){
            if(mainApp.partRetList().get(i).getPartName() == partName){
                System.out.println("Part Found at index: " + i);
                return mainApp.partRetList().get(i);
            }
        }
        return null;        
    }

    @FXML
    void DeletePartFromProduct(ActionEvent event) {
        System.out.println("Attempting to remove part: " + searchForPart(tempProdPartName).getPartName());
        tempProdPart = searchForPart(tempProdPartName);
        this.mainApp.removeFromProdPartList(tempProdPart);
    }

    
    @FXML
    void PartSearch(){
        
    }
    
    @FXML
    void SaveProduct(ActionEvent event) {
        if(VerifyInput()==true){
            int prID = generateID();
            String prName = txtProductName.getText();
            int prInv = Integer.parseInt(txtProductInventory.getText());
            Double prCost = Double.parseDouble(txtProductPrice.getText());
            int prMax = Integer.parseInt(txtProductMax.getText());
            int prMin = Integer.parseInt(txtProductMin.getText());        
            ArrayList<Part> addedParts = new ArrayList<>();
                for(int i = 0; i<tblProductParts.getItems().size(); i++){
                    addedParts.add(tblProductParts.getItems().get(i));
                }

            mainApp.prodRetList().get(this.mainApp.getProductIndex()).setProductID(prID);
            mainApp.prodRetList().get(this.mainApp.getProductIndex()).setProductName(prName);
            mainApp.prodRetList().get(this.mainApp.getProductIndex()).setProductInstock(prInv);
            mainApp.prodRetList().get(this.mainApp.getProductIndex()).setProductPrice(prCost);
            mainApp.prodRetList().get(this.mainApp.getProductIndex()).setProductMax(prMax);
            mainApp.prodRetList().get(this.mainApp.getProductIndex()).setProductMin(prMin);
            mainApp.prodRetList().get(this.mainApp.getProductIndex()).setPart(addedParts);

            Stage stage = (Stage) btnSaveProduct.getScene().getWindow();
            stage.close();
            setMainApp(mainApp);
        }
    }

    @FXML
    void ExitProduct(ActionEvent event) {
       
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    	alert.setTitle("Cancel Add Product?");
    	alert.setHeaderText("Are you sure you want to leave this screen?");
    	alert.setContentText("You will lose any changes since your last save.  Are you sure?");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		        Stage stage = (Stage) btnCancel.getScene().getWindow();
       stage.close();
    	} else {
    	    // ... user chose CANCEL or closed the dialog
    	} 

    }

    private boolean VerifyInput() {
        if (txtProductName.getText() == null || txtProductName.getText().trim().isEmpty()){
            mainApp.errors("Product Name field is empty", "Product Name");
            return false;
        }
        else if (txtProductInventory.getText() == null || txtProductInventory.getText().trim().isEmpty()){
            mainApp.errors("Product Inventory field is empty", "Product Inventory");
            return false;
        }
        else if (txtProductPrice.getText() == null || txtProductPrice.getText().trim().isEmpty()){
            mainApp.errors("Product Price field is empty", "Product Cost");
            return false;
        }
        else if (txtProductMax.getText() == null || txtProductMax.getText().trim().isEmpty()){
            mainApp.errors("Product Max field is empty", "Product Max");
            return false;
        }
        else if (txtProductMin.getText() == null || txtProductMin.getText().trim().isEmpty()){
            mainApp.errors("Product Min field is empty", "Product Min");
            return false;
        }
        else if(Integer.parseInt(txtProductMax.getText()) < Integer.parseInt(txtProductMin.getText())){
            mainApp.errors("Minimum inventory level must NOT be higher than the Maximum inventory level", "Product Max");
            return false;
        }
        else if(Integer.parseInt(txtProductInventory.getText()) > Integer.parseInt(txtProductMax.getText())){
            mainApp.errors("Product Inventory must not be less than the minimum allowable", "Product Inventory");
            return false;
        }
        else if(Integer.parseInt(txtProductInventory.getText()) < Integer.parseInt(txtProductMin.getText())){
            mainApp.errors("Product Inventory must not be less than the minimum allowable inventory level", "Product Inventory");
            return false;
        }
        
        if(tblProductParts.getItems().isEmpty()){
           mainApp.errors("Must select at least one part per product", "Parts Table"); 
           return false;
        }
        
        if(txtProductMax.getText() != null){
            int value = Integer.parseInt(txtProductMax.getText());
        }
        if(txtProductPrice.getText() != null){
            double value = Double.parseDouble(txtProductPrice.getText());
        }
    return true;
    }

    private int generateID() {
        int iD = 100;
        return iD++;
    }

    private void showProductsDetails(int index) {
        System.out.println("Products_Modify_Controller>>(showProductsDetails)Modifying Prod index: " + index);
        
        if(index > -1){
            
            this.tempProductPartList = mainApp.prodRetList().get(index).getPart();
            System.out.println("tempProductPartList Size: " + tempProductPartList.size() +
                    "\nmainApp PartList size: " + mainApp.prodRetList().get(index).getPart());
            int tempProdID = mainApp.prodRetList().get(index).getProductID();
            String tempProdName = mainApp.prodRetList().get(index).getProductName();
            int tempProdInstock = mainApp.prodRetList().get(index).getProductInstock();
            Double tempProdPrice = mainApp.prodRetList().get(index).getProductPrice();
            int tempProdMax = mainApp.prodRetList().get(index).getProductMax();
            int tempProdMin = mainApp.prodRetList().get(index).getProductMin();
            
            txtProductID.setText(Integer.toString(tempProdID));
            txtProductName.setText(tempProdName);
            txtProductInventory.setText(Integer.toString(tempProdInstock));
            txtProductPrice.setText(Double.toString(tempProdPrice));
            txtProductMax.setText(Integer.toString(tempProdMax));
            txtProductMin.setText(Integer.toString(tempProdMin));
            for(Part part: tempProductPartList){
                mainApp.prodpartRetList().add(part);
            }
        
        } else {
            ArrayList<Part> tempProdParts = new ArrayList<>();
            txtProductID.setText("--EMPTY--");
            txtProductName.setText("--EMPTY--");
            txtProductInventory.setText("--EMPTY--");
            txtProductPrice.setText("--EMPTY--");
            txtProductMax.setText("--EMPTY--");
            txtProductMin.setText("--EMPTY--");
            tempProdParts.clear();

        }

    }

}
