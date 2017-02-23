package ims;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Predicate;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main_FXMLController {
//    static IMS list = new IMS();
    private IMS mainApp;
    private int intmodPartIndex = -1;
    private int intmodProdIndex = -1;
    

    @FXML
    private Pane paneParts;
    @FXML
    private Button btnProductsAdd;
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
    private TableView<Product> tblProd;
    @FXML
    private TableColumn<Product, Number> colProductID;
    @FXML
    private TableColumn<Product, String> colProductName;
    @FXML
    private TableColumn<Product, Number> colProductInventory;
    @FXML
    private TableColumn<Product, Number> colProductPrice;
    @FXML
    private Button btnProductDelete;
    @FXML
    private Button btnPartsDelete;
    @FXML
    private Button btnPartsSearch;
    @FXML
    private TextField txtProductsSearch;
    @FXML
    private TextField txtPartsSearch;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnProductsModify;
    @FXML
    private Label label;
    @FXML
    private Button btnPartsAdd;
    @FXML
    private Pane paneParts1;
    @FXML
    private Button btnProductsSearch;
    @FXML
    private Button btnPartsModify;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Group grpPartSource;
    @FXML
    private RadioButton rdoOutsourced;
    @FXML
    private TextField txtPartCost;
    @FXML
    private RadioButton rdoInHouse;
    @FXML
    private Label lblMax;
    @FXML
    private AnchorPane paneAddPart;
    @FXML
    private TextField txtPartInventory;
    @FXML
    private TextField txtPartID;
    @FXML
    private Label lblID;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private Label lblName;
    @FXML
    private TextField txtPartName;
    @FXML
    private ToggleGroup PartRadio;
    @FXML
    private TextField txtPartMin;
    @FXML
    private Label lblPartSource;
    @FXML
    private TextField txtPartMax;
    @FXML
    private Label lblInventory;
    @FXML
    private Label lblPrice;
    @FXML
    private TextField txtpartSource;
    @FXML
    private Label lblMin;
    
    static void Main_FXMLController(){
           
    }
    
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
                PRODUCTS TABLE
    */
        // 0. Initialize the columns
        if(colProductID != null) {
            colProductID.setCellValueFactory(cellData -> cellData.getValue().productIDProperty());
            colProductName.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
            colProductInventory.setCellValueFactory(cellData -> cellData.getValue().productInstockProperty());
            colProductPrice.setCellValueFactory(cellData -> cellData.getValue().productPriceProperty());
        }
    }

    public void setMainApp(IMS mainApp) {
        this.mainApp = mainApp;
        
        // 1. Wrap the ObservableList in a FilteredList (initially display all data)
        FilteredList<Part> filteredData = new FilteredList<>(mainApp.partRetList(), p -> true);
        
        if(txtPartsSearch != null){
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
                    int index = (int)newVal;
                    intmodPartIndex = index;
                    System.out.println("intmodPartIndex: " + intmodPartIndex);
                    mainApp.setModPartsIndex(intmodPartIndex);
                    System.out.println("The Index for the part selected is " + mainApp.getModPartIndex());
                }    
            });
            tblParts.setItems(this.mainApp.partRetList());
        }

        if(colProductID != null){
            // 1. Wrap the ObservableList in a FilteredList (initially display all data)
            FilteredList<Product> filteredProdData = new FilteredList<>(mainApp.prodRetList(), p -> true);

            if(txtProductsSearch != null){
                // 2. Set the filter Predicate whenever the filter changes.
                txtProductsSearch.setOnKeyReleased(e -> {
                    txtProductsSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
                        filteredProdData.setPredicate((Predicate<? super Product>) prod -> {
                            System.out.println("txtPartsSearch Listener Activated");
                            if(newValue == null || newValue .isEmpty()){
                                return true;
                            }
                            String lowerCaseFilter = newValue.toLowerCase();
                            if(prod.getProductName().toLowerCase().contains(lowerCaseFilter)){
                                return true;
                            } else if (Integer.toString(prod.getProductID()).contains(newValue)){
                                return true;
                            }
                            return false;
                        });  
                    });
                    SortedList<Product> sortedProdData = new SortedList<>(filteredProdData);
                    sortedProdData.comparatorProperty().bind(tblProd.comparatorProperty());
                    tblProd.setItems(sortedProdData);
                });

                TableView.TableViewSelectionModel<Product> tvModel2 =
                        tblProd.getSelectionModel();    
                tvModel2.selectedIndexProperty().addListener(new ChangeListener<Number>()
                {
                    public void changed(ObservableValue<? extends Number> changed,
                            Number oldVal, Number newVal){
                        int index = (int)newVal;
                        intmodProdIndex = index;
                        mainApp.setProductIndex(intmodProdIndex);
                        System.out.println("Main_FXMLController>> The Index for the product selected is " + mainApp.getProductIndex());
                    }    
                });
            }
            tblProd.setItems(this.mainApp.prodRetList());
        }
    }
    
    @FXML
    void outsourced(ActionEvent event) {
        lblPartSource.setText("Company ID");
        txtpartSource.setPromptText("Enter Company ID...");
    }

    @FXML
    void inHouse(ActionEvent event) {
        lblPartSource.setText("Machine ID");
        txtpartSource.setPromptText("Enter Machine ID...");
    }

    @FXML
    void SavePart(ActionEvent event){
       VerifyInput();
        if(rdoInHouse.isSelected()){
            int pID = generateID();
            String pName = txtPartName.getText();
            int pInv = Integer.parseInt(txtPartInventory.getText());
            Double pCost = Double.parseDouble(txtPartCost.getText());
            int pMax = Integer.parseInt(txtPartMax.getText());
            int pMin = Integer.parseInt(txtPartMin.getText());
            int pSource = Integer.parseInt(txtpartSource.getText());

            Inhouse newPart = new Inhouse();
            newPart.setPartID(pID);
            newPart.setPartName(pName);
            newPart.setInstock(pInv);
            newPart.setPrice(pCost);
            newPart.setMax(pMax);
            newPart.setMin(pMin);
            newPart.setMachineID(pSource);
            
//            int test = this.mainApp.partRetList().size();
            this.mainApp.partRetList().add(newPart);

        } else if (rdoOutsourced.isSelected()){
            int pID = generateID();
            String pName = txtPartName.getText();
            int pInv = Integer.parseInt(txtPartInventory.getText());
            Double pCost = Double.parseDouble(txtPartCost.getText());
            int pMax = Integer.parseInt(txtPartMax.getText());
            int pMin = Integer.parseInt(txtPartMin.getText());
            int pSource = Integer.parseInt(txtpartSource.getText());

            Outsourced newPart = new Outsourced();
            newPart.setPartID(pID);
            newPart.setPartName(pName);
            newPart.setInstock(pInv);
            newPart.setPrice(pCost);
            newPart.setMax(pMax);
            newPart.setMin(pMin);
            newPart.setCompanyID(pSource);
            
            int test = this.mainApp.partRetList().size();
            this.mainApp.partRetList().add(newPart);
        }

        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close(); 

    }

 @FXML
    void VerifyInput() {
        if (txtPartName.getText() == null || txtPartName.getText().trim().isEmpty()){
            mainApp.errors("Part Name field is empty", "Part Name");
        }
        else if (txtPartInventory.getText() == null || txtPartInventory.getText().trim().isEmpty()){
            mainApp.errors("Part Inventory field is empty", "Part Inventory");
        }
        else if (txtPartCost.getText() == null || txtPartCost.getText().trim().isEmpty()){
            mainApp.errors("Part Price field is empty", "Part Cost");
        }
        else if (txtPartMax.getText() == null || txtPartMax.getText().trim().isEmpty()){
            mainApp.errors("Part Max field is empty", "Part Max");
        }
        else if (txtPartMin.getText() == null || txtPartMin.getText().trim().isEmpty()){
            mainApp.errors("Part Min field is empty", "Part Min");
        }
        else if (txtpartSource.getText() == null || txtpartSource.getText().trim().isEmpty()){
            mainApp.errors("Machine Number\\Company Name field is empty", "Part Source");
        }
        else if(Integer.parseInt(txtPartMax.getText()) < Integer.parseInt(txtPartMin.getText())){
            mainApp.errors("Minimum inventory level must NOT be higher than the Maximum inventory level", "Part Max");
//            throw new RuntimeException("Minimum inventory level must NOT be higher than the Maximum inventory level");
        }
        else if(Integer.parseInt(txtPartInventory.getText()) > Integer.parseInt(txtPartMax.getText())){
            mainApp.errors("Part Inventory must not be less than the minimum allowable", "Part Inventory");
        }
        else if(Integer.parseInt(txtPartInventory.getText()) < Integer.parseInt(txtPartMin.getText())){
            mainApp.errors("Part Inventory must not be less than the minimum allowable inventory level", "Part Inventory");
        }
               
        
        if (txtPartMax.getText() != null){
            int value = Integer.parseInt(txtPartMax.getText());
        }        
        if (txtPartCost.getText() != null){
            double value = Double.parseDouble(txtPartCost.getText());
            
        }
    }

    @FXML
    void CloseWindow(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Add Part?");
        alert.setHeaderText("Are you sure you want to leave this screen?");
        alert.setContentText("You will lose any changes since your last save.  Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }
    
    @FXML
    void PartsSearch(ActionEvent event) {
    }

    @FXML
    void DeletePart(ActionEvent event) {
        mainApp.partRetList().remove(intmodPartIndex);
    }

    @FXML
    void fireModifyPart(ActionEvent event) throws IOException {
        if(intmodPartIndex == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Part Selected!");
            alert.setHeaderText("No Part Selected");
            alert.setContentText("Please select a part to modify");
            alert.showAndWait();
        } else {
            showPartEditDialog();
        }
    }
    
    public Stage showPartEditDialog() throws IOException{
        Stage stage = new Stage();
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Part_Modify_FXML.fxml"));

        root = loader.load();
        Part_Modify_FXMLController controller = loader.getController();
        controller.setMainApp(mainApp);
        stage.setScene(new Scene(root));
        stage.showAndWait();

        return stage;
    }

   
    @FXML
    void fireAddPart(ActionEvent event) throws IOException {
        mainApp.setModPartsIndex(-1);
        mainApp.showPartsDialog();
        
    }

    @FXML
    void ProductsSearch(ActionEvent event) {
    }

    @FXML
    void DeleteProduct(ActionEvent event) {
        mainApp.prodRetList().remove(mainApp.getProductIndex());
    }

    @FXML
    void ModifyProduct(ActionEvent event) throws IOException {
        if(intmodProdIndex == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Product Selected!");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a product to modify");
            alert.showAndWait();
        } else {
            mainApp.setProductIndex(intmodProdIndex);
            mainApp.showProductsDialog();
        }
    }

    @FXML
    void fireAddProduct(ActionEvent event) throws IOException {
        mainApp.setProductIndex(-1);
        mainApp.showProductsDialog();
        
    }
    
    

    @FXML
    void Exit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    	alert.setTitle("Exit?");
    	alert.setHeaderText("Exiting Inventory Management System");
    	alert.setContentText("Save and Exit?");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		Platform.exit();
    	} else {
    	} 
    }
    @FXML
    private int generateID(){
        int iD = 1000;
        return iD++;
    }
    
}
