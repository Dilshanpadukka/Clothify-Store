package controller.adminController;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Customer;
import service.ServiceFactory;
import service.custom.CustomerService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AdminManagementFormController implements Initializable {

    public AnchorPane pageCustomer;
    public AnchorPane pageSupplier;
    public AnchorPane pageProduct;
    public AnchorPane pageEmployee;
    public AnchorPane pageOrderManage;
    public AnchorPane pagePlaceOrder;
    public AnchorPane pageViewOrders;
    public TextField txtEmployeeName;
    public TextField txtEmployeePassword;
    public TextField txtEmployeeEmail;
    public TextArea txtEmployeeAddress;
    public DatePicker txtEmployeeDOB;
    public TextField txtEmployeeSalary;
    public TextField txtContact;
    public TextField txtEmployeeNic;
    public Label lblEmployeeId;
    public TableColumn colEmpId;
    public TableColumn colEmpName;
    public TableColumn colEmpNic;
    public TableColumn colEmpAddress;
    public TableColumn colEmpEmail;
    public TableView tblEmployee;
    public TableColumn colCustId;
    public TableColumn colCustTitle;
    public TableColumn colCustName;
    public TableColumn colCustAddress;
    public TableColumn colCustCity;
    public TableColumn colCustProvince;
    public TableColumn colCustPostalCode;
    public TableColumn colCustEmail;
    public TableColumn colCustContact;
    public TextField txtCustCity;
    public TextField txtCustName;
    public TextField txtCustEmail;
    public TextField txtCustProvince;
    public TextField txtCustPostalCode;
    public Label lblCustId;
    public ComboBox cmbCustTitle;
    public TextArea txtCustAddress;
    public TextField txtCustContact;
    public TableView tblCustomer;
    public Label btnLogOut;
    public TextArea txtSupCompanyAddress;
    public Label lblSupId;
    public TextField txtSupPostalCode;
    public TextField txtSupCompany;
    public TextField txtSupCompanyEmail;
    public TextField txtSupName;
    public TextField txtSupCity;
    public TableColumn colSupPostalCode;
    public TableColumn colSupContact;
    public TableColumn colSupEmail;
    public TableColumn colSupAddress;
    public TableColumn colSupCompany;
    public TableColumn colSupName;
    public TableColumn colSupId;
    public TableView tblSupplier;
    public TextField txtSupContact;
    public TableView tblItem;
    public TableColumn colItemtId;
    public TableColumn colItemCategory;
    public TableColumn colItemName;
    public TableColumn colItemSize;
    public TableColumn colItemQty;
    public TableColumn colItemUnitPrice;
    public TableColumn colItemDescription;
    public TextField txtItemName;
    public TextField txtItemUnitPrice;
    public Label lblItemId;
    public ComboBox cmbItemCategory;
    public TextArea txtItemDescription;
    public ComboBox cmbItemSize;
    public TextField txtItemQty;
    public ComboBox cmbSupplierId;
    public Label lblDate;
    public Label lblTime;
    public ComboBox cmbItemCode;
    public TextField txtOrderItemQty;
    public TextField txtOrderItemSize;
    public TextField txtOrderItemCate;
    public TextField txtOrderItemUnitPrice;
    public TextField txtOrderItemName;
    public TableColumn colOrderItemCode;
    public TableColumn colOrderItemName;
    public TableColumn colOrderItemCategory;
    public TableColumn colOrderItemSize;
    public TableColumn colOrderItemQty;
    public TableColumn colOrderItemUnitPrice;
    public TableColumn colOrderItemTotal;
    public TextField txtOrderCustAddress;
    public ComboBox cmbCustId;
    public TextField txtOrderCustName;
    public TextField txtOrderCustContact;
    public TextField txtCustId;
    public TextField txtSupItemDesc;
    public TextField txtSupId;
    public TableColumn colEmpContact;
    public TextField txtEmployeeContact;
    public TextField txtEmployeeId;
    public Label txtOrderId;
    public Label lblNetTotal;
    public TextField txtOrderEmpId;
    public TextField txtOrderEmpEmali;
    public AnchorPane pageReportGen;
    public JFXButton btnPrint01OnAction;
    public PieChart pieChart;
    public VBox chartContainer;
    public JFXButton btnPrint02OnAction;
    public JFXButton btnPrint03OnAction;
    public TextField txtItemId;

    private AnchorPane currentPage;

    //=========================================================================REPORT GEN==========================================================================
    public StackedBarChart<String,Number> stackedBarChart;
    public void btnReportGenOnAction(ActionEvent event) {
        currentPage.setVisible(false);
        currentPage = pageReportGen;
        currentPage.setVisible(true);
    }
    private StackedBarChart<String, Number> createStackedBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        stackedBarChart = new StackedBarChart<>(xAxis, yAxis);
        stackedBarChart.setTitle("Sales Overview");

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Trousers");
        series1.getData().add(new XYChart.Data<>("January", 30));
        series1.getData().add(new XYChart.Data<>("February", 70));
        series1.getData().add(new XYChart.Data<>("March", 50));
        series1.getData().add(new XYChart.Data<>("April", 100));

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Shirts");
        series2.getData().add(new XYChart.Data<>("January", 50));
        series2.getData().add(new XYChart.Data<>("February", 60));
        series2.getData().add(new XYChart.Data<>("March", 80));
        series2.getData().add(new XYChart.Data<>("April", 90));

        stackedBarChart.getData().addAll(series1, series2);

        return stackedBarChart;
    }
    private void loadPieChart() {
        PieChart.Data slice1 = new PieChart.Data("Clothing", 30);
        PieChart.Data slice2 = new PieChart.Data("Footwear", 25);
        PieChart.Data slice3 = new PieChart.Data("Accessories", 20);
        PieChart.Data slice4 = new PieChart.Data("Homewear", 15);
        PieChart.Data slice5 = new PieChart.Data("Others", 10);

        pieChart.getData().addAll(slice1, slice2, slice3, slice4, slice5);

        for (PieChart.Data slice : pieChart.getData()) {
            slice.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pie Chart Info");
                alert.setContentText("Type : " + slice.getName() + "\nValue: " + slice.getPieValue());
                alert.showAndWait();

            });
        }
    }

    //=======================================================================LOG OUT BUTTON==========================================================================
    public void btnLogOutOnAction(MouseEvent mouseEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/logInForm.fxml"))));
            btnLogOut.getScene().getWindow().hide();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //====================================================================CUSTOMER MANAGEMENT==========================================================================
    final CustomerService customerService = ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);

    public void btnCustomerManagementOnAction(ActionEvent event) {
        currentPage.setVisible(false);
        currentPage = pageCustomer;
        currentPage.setVisible(true);
    }
    private void loadTitleMenu() {
        ObservableList<Object> title = FXCollections.observableArrayList();
        title.add("Mr.");
        title.add("Mrs.");
        title.add("Miss.");
        title.add("Master.");
        cmbCustTitle.setItems(title);
    }
    private void loadCustomerTable() {
        ObservableList<Customer> customers = customerService.getAll();
        tblCustomer.setItems(customers);
    }
    private void setTextToValues(Customer newValue) {
        txtCustId.setText(newValue.getCustId());
        cmbCustTitle.setValue(newValue.getCustTitle());
        txtCustName.setText(newValue.getCustName());
        txtCustContact.setText(newValue.getCustContact());
        txtCustEmail.setText(newValue.getCustEmail());
        txtCustAddress.setText(newValue.getCustAddress());
        txtCustCity.setText(newValue.getCity());
        txtCustProvince.setText(newValue.getProvince());
        txtCustPostalCode.setText(newValue.getPostalCode());
    }
    public void clearFieldsCustomer() {
        generateCustomerID();
        cmbCustTitle.setValue(null);
        txtCustName.setText("");
        txtCustContact.setText("");
        txtCustAddress.setText("");
        txtCustCity.setText("");
        txtCustProvince.setText("");
        txtCustPostalCode.setText("");
        txtCustEmail.setText("");
    }
    public void generateCustomerID() {
        try {
            String SQL = "SELECT MAX(CustID) FROM customerentity";
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Clothify-Store-new", "root", "12345");
            PreparedStatement pstm = connection.prepareStatement(SQL);
            ResultSet resultSet = pstm.executeQuery();
            int newId = 1;
            if (resultSet.next()) {
                String lastId = resultSet.getString(1);
                if (lastId != null) {
                    newId = Integer.parseInt(lastId.substring(1)) + 1;
                }
            }
            txtCustId.setText(String.format("C%04d", newId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void btnAddCustomerOnAction(ActionEvent event) {
        Customer customer = new Customer(
                txtCustId.getText(),
                cmbCustTitle.getValue().toString(),
                txtCustName.getText(),
                txtCustContact.getText(),
                txtCustEmail.getText(),
                txtCustAddress.getText(),
                txtCustCity.getText(),
                txtCustProvince.getText(),
                txtCustPostalCode.getText()
        );
        if (customerService.addCustomer(customer)) {
            loadCustomerTable();
            clearFieldsCustomer();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Customer Added Successfully...");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Customer Didn't added....");
            alert.show();
        }
    }
    public void btnUpdateCustomerOnAction(ActionEvent event) {
        Customer customer = new Customer(
                txtCustId.getText(),
                cmbCustTitle.getValue().toString(),
                txtCustName.getText(),
                txtCustContact.getText(),
                txtCustEmail.getText(),
                txtCustAddress.getText(),
                txtCustCity.getText(),
                txtCustProvince.getText(),
                txtCustPostalCode.getText()
        );
        if(customerService.updateCustomer(customer)){
            loadCustomerTable();
            clearFieldsCustomer();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Customer Updated Successfully..");
            alert.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Customer didn't Updated ...");
            alert.show();
        }
    }
    public void btnSearchCustomerOnAction(ActionEvent event) {
        try {
            Customer customer = customerService.searchCustomer(txtCustId.getText());
            if (customer != null) {
                setTextToValues(customer);
            } else {
                new Alert(Alert.AlertType.ERROR, "Customer not found").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void btnDeleteCustomerOnAction(ActionEvent event) {
        if(customerService.deleteCustomer(txtCustId.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Customer Deleted SuccessFully");
            alert.show();
            loadCustomerTable();
            clearFieldsCustomer();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Customer Didn't Found");
            alert.show();
        }
    }
    //====================================DASHBOARD MANAGEMENT==============================================================
    public void btnSupplierManagementOnAction(ActionEvent event) {
        currentPage.setVisible(false);
        currentPage = pageSupplier;
        currentPage.setVisible(true);
    }
    public void btnProductManagementOnAction(ActionEvent event) {
        currentPage.setVisible(false);
        currentPage = pageProduct;
        currentPage.setVisible(true);
    }

    public void btnEmployeeManagementOnAction(ActionEvent event) {
        currentPage.setVisible(false);
        currentPage = pageEmployee;
        currentPage.setVisible(true);
    }

    public void btnOrderManagementOnAction(ActionEvent event) {
        currentPage.setVisible(false);
        currentPage = pageOrderManage;
        currentPage.setVisible(true);
    }

    public void btnPlaceOrderOnAction(MouseEvent mouseEvent) {
        currentPage.setVisible(false);
        currentPage = pagePlaceOrder;
        currentPage.setVisible(true);
    }

    public void btnViewOrderOnAction(MouseEvent mouseEvent) {
        currentPage.setVisible(false);
        currentPage = pageViewOrders;
        currentPage.setVisible(true);
    }





    public void btnAddtoCartOnAction(ActionEvent event) {

    }

    public void btncClearOnAction(ActionEvent event) {

    }

    public void btnCustomerClearOnAction(MouseEvent mouseEvent) {
        clearFieldsCustomer();
    }
    
    public void btnEmployeeClearOnAction(MouseEvent mouseEvent) {
    }

    public void btnRemoveOnAction(ActionEvent event) {
    }

    public void btnItemClearOnAction(MouseEvent mouseEvent) {
    }



    public void btnSupplierClearOnAction(MouseEvent mouseEvent) {
    }







    public void btnAddSupplierOnAction(ActionEvent event) {
    }

    public void btnUpdateSupplierOnAction(ActionEvent event) {
    }

    public void btnSerachSupplierOnAction(ActionEvent event) {
    }

    public void btnDeleteSupplierOnAction(ActionEvent event) {
    }

    public void btnAddItemOnAction(ActionEvent event) {
    }

    public void btnUpdateItemOnAction(ActionEvent event) {
    }

    public void btnSearchItemOnAction(ActionEvent event) {
    }

    public void btnDeleteItemOnAction(ActionEvent event) {
    }

    public void btnAddEmployeeOnAction(ActionEvent event) {
    }

    public void btnUpdateEmployeeOnAction(ActionEvent event) {
    }

    public void btnSearchEmployeeOnAction(ActionEvent event) {
    }

    public void btnDeleteEmployeeOnAction(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentPage = pageCustomer;
        currentPage.setVisible(true);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        StackedBarChart<String, Number> stackedBarChart = createStackedBarChart();
        chartContainer.getChildren().add(stackedBarChart);
        loadPieChart();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        generateCustomerID();
        loadTitleMenu();
        colCustId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colCustTitle.setCellValueFactory(new PropertyValueFactory<>("custTitle"));
        colCustName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        colCustContact.setCellValueFactory(new PropertyValueFactory<>("custContact"));
        colCustEmail.setCellValueFactory(new PropertyValueFactory<>("custEmail"));
        colCustAddress.setCellValueFactory(new PropertyValueFactory<>("custAddress"));
        colCustCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colCustProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colCustPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        tblCustomer.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValues((Customer) newValue);
            }
        }));
        loadCustomerTable();
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

}