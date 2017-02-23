package ims;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Part_Modify_FXMLController {
    private IMS mainApp;
    
    @FXML
    private Group grpPartSource;
    @FXML
    private RadioButton rdoOutsourced;
    @FXML
    public TextField txtModPartCost;
    @FXML
    public TextField txtModPartID;
    @FXML
    public TextField txtModPartInventory;
    @FXML
    public TextField txtModPartName;
    @FXML
    public TextField txtModPartMin;
    @FXML
    public TextField txtModPartMax;
    @FXML
    public TextField txtModPartSource;
    @FXML
    private RadioButton rdoInHouse;
    @FXML
    private Label lblMax;
    @FXML
    private AnchorPane paneAddPart;
    @FXML
    private Label lblID;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private Label lblName;
    @FXML
    private ToggleGroup PartRadio;
    @FXML
    private Label lblPartSource;
    @FXML
    private Label lblInventory;
    @FXML
    private Label lblPrice;

    @FXML
    private Label lblMin;

    public void setMainApp(IMS mainApp) {
        this.mainApp = mainApp;
        showPartsDetails(this.mainApp.getModPartIndex());
    }
    
    @FXML
    void initialize() {

    }

    private void showPartsDetails(int index){
        System.out.println("Part_Modify_FXMLController Constructor>> Part index: " + index);
        
        if(index > -1){
            int tempPartID = mainApp.partRetList().get(index).getPartID();
            String tempPartName = mainApp.partRetList().get(index).getPartName();
            int tempInstock = mainApp.partRetList().get(index).getInstock();
            Double tempPartPrice = mainApp.partRetList().get(index).getPrice();
            int tempPartMax = mainApp.partRetList().get(index).getMax();
            int tempPartMin = mainApp.partRetList().get(index).getMin();



            txtModPartID.setText(Integer.toString(tempPartID));
            txtModPartName.setText(tempPartName);
            txtModPartInventory.setText(Integer.toString(tempInstock));
            txtModPartCost.setText(Double.toString(tempPartPrice));
            txtModPartMax.setText(Integer.toString(tempPartMax));
            txtModPartMin.setText(Integer.toString(tempPartMin));
             if(mainApp.partRetList().get(index) instanceof Inhouse){
                int tempPartMachineID;
                tempPartMachineID = ((Inhouse) mainApp.partRetList().get(index)).getMachineID();
                txtModPartSource.setText(Integer.toString(tempPartMachineID));
                rdoInHouse.setSelected(true);
             } else if(mainApp.partRetList().get(index) instanceof Outsourced){
                int tempPartCompanyID;
                tempPartCompanyID = ((Outsourced) mainApp.partRetList().get(index)).getCompanyID();
                txtModPartSource.setText(Integer.toString(tempPartCompanyID));
                rdoOutsourced.setSelected(true);
             } else {
                txtModPartSource.setText("NO MACHINE / COMPANY ID");
            }
        } else {
            txtModPartID.setText("--EMPTY--");
            txtModPartName.setText("--EMPTY--");
            txtModPartInventory.setText("--EMPTY--");
            txtModPartCost.setText("--EMPTY--");
            txtModPartMax.setText("--EMPTY--");
            txtModPartMin.setText("--EMPTY--");
            txtModPartSource.setText("--EMPTY--");
        }
    }
    
    @FXML
    void outsourced(ActionEvent event) {
        lblPartSource.setText("Company ID");
    }

    @FXML
    void inHouse(ActionEvent event) {
        lblPartSource.setText("Machine ID");
    }

    @FXML
    void VerifyInput() {
        if (txtModPartName.getText() == null || txtModPartName.getText().trim().isEmpty()){
            throw new RuntimeException("Part Name field is empty");
        }
        else if (txtModPartInventory.getText() == null || txtModPartInventory.getText().trim().isEmpty()){
            throw new RuntimeException("Part Inventory field is empty");
        }
        else if (txtModPartCost.getText() == null || txtModPartCost.getText().trim().isEmpty()){
            throw new RuntimeException("Part Price field is empty");
        }
        else if (txtModPartMax.getText() == null || txtModPartMax.getText().trim().isEmpty()){
            throw new RuntimeException("Part Max Inventory field is empty");
        }
        else if (txtModPartMin.getText() == null || txtModPartMin.getText().trim().isEmpty()){
            throw new RuntimeException("Part Min Inventory field is empty");
        }
        else if (txtModPartSource.getText() == null || txtModPartSource.getText().trim().isEmpty()){
            throw new RuntimeException("Machine Number\\Company Name field is empty");
        }
        else if(Integer.parseInt(txtModPartMax.getText()) < Integer.parseInt(txtModPartMin.getText())){
            throw new RuntimeException("Minimum inventory level must NOT be higher than the Maximum inventory level");
        }
        else if(Integer.parseInt(txtModPartInventory.getText()) > Integer.parseInt(txtModPartMax.getText())){
            throw new RuntimeException("Part Inventory must not exceed the maximum allowable inventory level");
        }
        else if(Integer.parseInt(txtModPartInventory.getText()) < Integer.parseInt(txtModPartMin.getText())){
            throw new RuntimeException("Part Inventory must not be less than the minimum allowable inventory level");
        }
               
        
        if (txtModPartMax.getText() != null){
            int value = Integer.parseInt(txtModPartMax.getText());
        }        
        if (txtModPartCost.getText() != null){
            double value = Double.parseDouble(txtModPartCost.getText());
            
        }
    }
    
    @FXML
    void SavePart(ActionEvent event){
       VerifyInput();
        if(rdoInHouse.isSelected()){
            int pID = Integer.parseInt(txtModPartID.getText());
            String pName = txtModPartName.getText();
            int pInv = Integer.parseInt(txtModPartInventory.getText());
            Double pCost = Double.parseDouble(txtModPartCost.getText());
            int pMax = Integer.parseInt(txtModPartMax.getText());
            int pMin = Integer.parseInt(txtModPartMin.getText());
            int pSource = Integer.parseInt(txtModPartSource.getText());

            mainApp.partRetList().get(mainApp.getModPartIndex()).setPartID(pID);
            mainApp.partRetList().get(mainApp.getModPartIndex()).setPartName(pName);
            mainApp.partRetList().get(mainApp.getModPartIndex()).setInstock(pInv);
            mainApp.partRetList().get(mainApp.getModPartIndex()).setPrice(pCost);
            mainApp.partRetList().get(mainApp.getModPartIndex()).setMax(pMax);
            mainApp.partRetList().get(mainApp.getModPartIndex()).setMin(pMin);
            ((Inhouse) mainApp.partRetList().get(mainApp.getModPartIndex())).setMachineID(pSource);
            
            pID = Integer.parseInt(txtModPartID.getText());
            pName = txtModPartName.getText();
            pInv = Integer.parseInt(txtModPartInventory.getText());
            pCost = Double.parseDouble(txtModPartCost.getText());
            
            System.out.println("Part ID: " + pID +
                    "\nPart Name: " + pName +
                    "\nPart Inventory: " + pInv +
                    "\nPart Cost: " + pCost);

        } else if (rdoOutsourced.isSelected()){
            int pID = Integer.parseInt(txtModPartID.getText());
            String pName = txtModPartName.getText();
            int pInv = Integer.parseInt(txtModPartInventory.getText());
            Double pCost = Double.parseDouble(txtModPartCost.getText());
            int pMax = Integer.parseInt(txtModPartMax.getText());
            int pMin = Integer.parseInt(txtModPartMin.getText());
            int pSource = Integer.parseInt(txtModPartSource.getText());

            mainApp.partRetList().get(mainApp.getModPartIndex()).setPartID(pID);
            mainApp.partRetList().get(mainApp.getModPartIndex()).setPartName(pName);
            mainApp.partRetList().get(mainApp.getModPartIndex()).setInstock(pInv);
            mainApp.partRetList().get(mainApp.getModPartIndex()).setPrice(pCost);
            mainApp.partRetList().get(mainApp.getModPartIndex()).setMax(pMax);
            mainApp.partRetList().get(mainApp.getModPartIndex()).setMin(pMin);
            ((Outsourced) mainApp.partRetList().get(mainApp.getModPartIndex())).setCompanyID(pSource);
        }    
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close(); 
    }

    
    @FXML
    void VerifyInput(ActionEvent event) {

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
  
}
