package controller.adminController;

import com.jfoenix.controls.JFXButton;
import controller.ReceiptController.ReceiptFormController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;
import service.ServiceFactory;
import service.custom.*;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    public TextField txtEmployeeNic;
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
    public ComboBox cmbCustTitle;
    public TextArea txtCustAddress;
    public TextField txtCustContact;
    public TableView tblCustomer;
    public Label btnLogOut;
    public TextArea txtSupCompanyAddress;
    public TextField txtSupPostalCode;
    public TextField txtSupCompany;
    public TextField txtSupCompanyEmail;
    public TextField txtSupName;
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

    public TextField txtItemName;
    public TextField txtItemUnitPrice;

    public ComboBox cmbItemCategory;

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
    public TextField txtOrderItemStockLev;
    public TextField txtOrderItemDiscount;
    public TableView<CartTM> tblOrderDetails;
    public Label lblOrderId;
    public PasswordField txtEmployeeConfirmPassword;
    public Label lblOrderDetailTime;
    public Label lblOrderDetailDate;
    public TableColumn colOrderId;
    public TableColumn colViewEmpId;
    public TableColumn colViewCustId;
    public TableColumn colViewDate;
    public TableColumn colViewTime;
    public TableColumn colViewNetTotal;
    public TableView tblViewOrder;

    private AnchorPane currentPage;
    private Timeline timeline;

    //=========================================================================REPORT GEN==========================================================================
    public StackedBarChart<String, Number> stackedBarChart;

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
            stage.setHeight(563);
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
        txtCustId.setText(customerService.generateCustomerId());
        cmbCustTitle.setValue(null);
        txtCustName.setText("");
        txtCustContact.setText("");
        txtCustAddress.setText("");
        txtCustCity.setText("");
        txtCustProvince.setText("");
        txtCustPostalCode.setText("");
        txtCustEmail.setText("");
    }

    public void btnAddCustomerOnAction(ActionEvent event) {
        if (txtCustId.getText() != null && !txtCustId.getText().isEmpty() &&
                cmbCustTitle.getValue() != null &&
                txtCustName.getText() != null && !txtCustName.getText().isEmpty() &&
                txtCustContact.getText() != null && !txtCustContact.getText().isEmpty() &&
                txtCustEmail.getText() != null && !txtCustEmail.getText().isEmpty() &&
                txtCustAddress.getText() != null && !txtCustAddress.getText().isEmpty() &&
                txtCustCity.getText() != null && !txtCustCity.getText().isEmpty() &&
                txtCustProvince.getText() != null && !txtCustProvince.getText().isEmpty() &&
                txtCustPostalCode.getText() != null && !txtCustPostalCode.getText().isEmpty()) {

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
            System.out.println(customer);
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
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Fill all fields...");
            alert.show();
        }
    }

    public void btnUpdateCustomerOnAction(ActionEvent event) {
        if (txtCustId.getText() != null && !txtCustId.getText().isEmpty() &&
                cmbCustTitle.getValue() != null &&
                txtCustName.getText() != null && !txtCustName.getText().isEmpty() &&
                txtCustContact.getText() != null && !txtCustContact.getText().isEmpty() &&
                txtCustEmail.getText() != null && !txtCustEmail.getText().isEmpty() &&
                txtCustAddress.getText() != null && !txtCustAddress.getText().isEmpty() &&
                txtCustCity.getText() != null && !txtCustCity.getText().isEmpty() &&
                txtCustProvince.getText() != null && !txtCustProvince.getText().isEmpty() &&
                txtCustPostalCode.getText() != null && !txtCustPostalCode.getText().isEmpty()) {
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
            if (customerService.updateCustomer(customer)) {
                loadCustomerTable();
                clearFieldsCustomer();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Customer Updated Successfully..");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Customer didn't Updated ...");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Fill all fields...");
            alert.show();
        }
    }

    public void btnSearchCustomerOnAction(ActionEvent event) {
        if (txtCustId.getText() != null && !txtCustId.getText().isEmpty()) {
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
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Enter Customer ID...");
            alert.show();
        }

    }

    public void btnDeleteCustomerOnAction(ActionEvent event) {
        if (txtCustId.getText() != null && !txtCustId.getText().isEmpty()) {
            if (customerService.deleteCustomer(txtCustId.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Customer Deleted SuccessFully");
                alert.show();
                loadCustomerTable();
                clearFieldsCustomer();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Customer Didn't Found");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Enter Customer ID...");
            alert.show();
        }
    }

    public void btnCustomerClearOnAction(MouseEvent mouseEvent) {
        clearFieldsCustomer();
    }

    //====================================================================EMPLOYEE MANAGEMENT==========================================================================
    final EmployeeService employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
    //private Map<String, String> temporaryPasswords = new HashMap<>();
    //private String currentEmployeeId;
    private  Employee currentEmployee;

    public void btnEmployeeManagementOnAction(ActionEvent event) {
        currentPage.setVisible(false);
        currentPage = pageEmployee;
        currentPage.setVisible(true);
    }

    private void loadEmployeeTable() {
        ObservableList<Employee> employees = employeeService.getAll();
        tblEmployee.setItems(employees);
    }

    private void setTextToValues(Employee newValue) {
        currentEmployee = newValue;
        txtEmployeeId.setText(newValue.getEmployeeId());
        txtEmployeeName.setText(newValue.getEmployeeName());
        txtEmployeeContact.setText(newValue.getContactNumber());
        txtEmployeeNic.setText(newValue.getEmployeeNic());
        txtEmployeeEmail.setText(newValue.getEmployeeEmailAddress());
        txtEmployeeAddress.setText(newValue.getEmployeeAddress());
        txtEmployeePassword.setText("");
        txtEmployeeConfirmPassword.setText("");
    }

    public void clearFieldsEmployee() {
        currentEmployee = null;
        txtEmployeeId.setText(employeeService.generateEmployeeId());
        txtEmployeeAddress.setText("");
        txtEmployeeName.setText("");
        txtEmployeeEmail.setText("");
        txtEmployeeNic.setText("");
        txtEmployeeContact.setText("");
    }

    public void btnAddEmployeeOnAction(ActionEvent event) {
        if (txtEmployeeId.getText() != null && !txtEmployeeId.getText().isEmpty() &&
                txtEmployeeName.getText() != null && !txtEmployeeName.getText().isEmpty() &&
                txtEmployeeNic.getText() != null && !txtEmployeeNic.getText().isEmpty() &&
                txtEmployeeContact.getText() != null && !txtEmployeeContact.getText().isEmpty() &&
                txtEmployeeAddress.getText() != null && !txtEmployeeAddress.getText().isEmpty() &&
                txtEmployeeEmail.getText() != null && !txtEmployeeEmail.getText().isEmpty() &&
                txtEmployeePassword.getText() != null && !txtEmployeePassword.getText().isEmpty()) {
            if (employeeService.addEmployee(txtEmployeeId.getText(),
                    txtEmployeeName.getText(),
                    txtEmployeeNic.getText(),
                    txtEmployeeContact.getText(),
                    txtEmployeeAddress.getText(),
                    txtEmployeeEmail.getText(),
                    txtEmployeePassword.getText())) {
                loadEmployeeTable();
                clearFieldsEmployee();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Employee Added Successfully...");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Employee Didn't added....");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Fill all fields...");
            alert.show();
        }
    }

    public void btnUpdateEmployeeOnAction(ActionEvent event) {
        if (!txtEmployeePassword.getText().equals(txtEmployeeConfirmPassword.getText())) {
            new Alert(Alert.AlertType.ERROR, "Passwords do not match!").show();
            return;
        }

        String password;
        if (txtEmployeePassword.getText().isEmpty()) {
            password = currentEmployee.getPassword();
        } else {
            password = txtEmployeePassword.getText();
        }
        if (employeeService.updateEmployee(
                txtEmployeeId.getText(),
                txtEmployeeName.getText(),
                txtEmployeeNic.getText(),
                txtEmployeeContact.getText(),
                txtEmployeeAddress.getText(),
                txtEmployeeEmail.getText(),
                password)) {

            //temporaryPasswords.remove(currentEmployeeId);
            loadEmployeeTable();
            clearFieldsEmployee();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Employee Updated Successfully..");
            alert.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Employee didn't Updated ...");
            alert.show();
        }
    }

    public void btnSearchEmployeeOnAction(ActionEvent event) {
        if (txtEmployeeId.getText() != null && !txtEmployeeId.getText().isEmpty()) {
            Employee employee = employeeService.searchEmployee(txtEmployeeId.getText());
            if (employee != null) {
                setTextToValues(employee);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Employee Not Found");
                alert.setHeaderText(null);
                alert.setContentText("No employee found with ID: " + txtEmployeeId.getText());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Enter Employee ID...");
            alert.show();
        }
    }

    public void btnDeleteEmployeeOnAction(ActionEvent event) {
        if (txtEmployeeId.getText() != null && !txtEmployeeId.getText().isEmpty()) {
            if (employeeService.deleteEmployee(txtEmployeeId.getText())) {
                loadEmployeeTable();
                clearFieldsEmployee();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Employee Deleted SuccessFully");
                alert.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Employee Didn't Found");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Enter Employee ID...");
            alert.show();
        }
    }

    public void btnEmployeeClearOnAction(MouseEvent mouseEvent) {
        clearFieldsEmployee();
    }

    //====================================================================SUPPLIER MANAGEMENT==========================================================================
    final SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);

    public void btnSupplierManagementOnAction(ActionEvent event) {
        currentPage.setVisible(false);
        currentPage = pageSupplier;
        currentPage.setVisible(true);
    }

    private void loadSupplierTable() {
        tblSupplier.setItems(FXCollections.observableArrayList(supplierService.getAll()));
    }

    private void setTextToValues(Supplier newValue) {
        txtSupId.setText(newValue.getSupplierId());
        txtSupName.setText(newValue.getSupplierName());
        txtSupCompany.setText(newValue.getSupplierCompany());
        txtSupContact.setText(newValue.getSupplierContactNumber());
        txtSupCompanyEmail.setText(newValue.getSupplierEmailAddress());
        txtSupCompanyAddress.setText(newValue.getSupplierAddress());
        txtSupPostalCode.setText(newValue.getSupplierPostalCode());
        txtSupItemDesc.setText(newValue.getSupplierItem());
    }

    public void clearFieldsSupplier() {
        txtSupId.setText(supplierService.generateSupplierId());
        txtSupName.setText("");
        txtSupCompany.setText("");
        txtSupContact.setText("");
        txtSupItemDesc.setText("");
        txtSupPostalCode.setText("");
        txtSupCompanyEmail.setText("");
        txtSupCompanyAddress.setText("");
    }

    public void btnAddSupplierOnAction(ActionEvent event) {
        if (txtSupId.getText() != null && !txtSupId.getText().isEmpty() &&
                txtSupName.getText() != null && !txtSupName.getText().isEmpty() &&
                txtSupContact.getText() != null && !txtSupContact.getText().isEmpty() &&
                txtSupItemDesc.getText() != null && !txtSupItemDesc.getText().isEmpty() &&
                txtSupCompany.getText() != null && !txtSupCompany.getText().isEmpty() &&
                txtSupCompanyAddress.getText() != null && !txtSupCompanyAddress.getText().isEmpty() &&
                txtSupCompanyEmail.getText() != null && !txtSupCompanyEmail.getText().isEmpty() &&
                txtSupPostalCode.getText() != null && !txtSupPostalCode.getText().isEmpty()) {
            Supplier supplier = new Supplier(
                    txtSupId.getText(),
                    txtSupName.getText(),
                    txtSupContact.getText(),
                    txtSupItemDesc.getText(),
                    txtSupCompany.getText(),
                    txtSupCompanyAddress.getText(),
                    txtSupCompanyEmail.getText(),
                    txtSupPostalCode.getText()
            );
            if (supplierService.addSupplier(supplier)) {
                loadSupplierTable();
                clearFieldsSupplier();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Supplier Added Successfully...");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Supplier Didn't added....");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Fill all fields...");
            alert.show();
        }
    }

    public void btnUpdateSupplierOnAction(ActionEvent event) {
        if (txtSupId.getText() != null && !txtSupId.getText().isEmpty() &&
                txtSupName.getText() != null && !txtSupName.getText().isEmpty() &&
                txtSupContact.getText() != null && !txtSupContact.getText().isEmpty() &&
                txtSupItemDesc.getText() != null && !txtSupItemDesc.getText().isEmpty() &&
                txtSupCompany.getText() != null && !txtSupCompany.getText().isEmpty() &&
                txtSupCompanyAddress.getText() != null && !txtSupCompanyAddress.getText().isEmpty() &&
                txtSupCompanyEmail.getText() != null && !txtSupCompanyEmail.getText().isEmpty() &&
                txtSupPostalCode.getText() != null && !txtSupPostalCode.getText().isEmpty()) {
            Supplier supplier = new Supplier(
                    txtSupId.getText(),
                    txtSupName.getText(),
                    txtSupContact.getText(),
                    txtSupItemDesc.getText(),
                    txtSupCompany.getText(),
                    txtSupCompanyAddress.getText(),
                    txtSupCompanyEmail.getText(),
                    txtSupPostalCode.getText()
            );
            if (supplierService.updateSupplier(supplier)) {
                loadSupplierTable();
                clearFieldsSupplier();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Supplier Updated Successfully..");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Supplier didn't Updated ...");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Fill all fields...");
            alert.show();
        }
    }

    public void btnSerachSupplierOnAction(ActionEvent event) {
        if (txtSupId.getText() != null && !txtSupId.getText().isEmpty()) {
            Supplier supplier = supplierService.searchSupplier(txtSupId.getText());
            if (supplier != null) {
                setTextToValues(supplier);
            } else {
                // Show an alert if no customer is found
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Supplier Not Found");
                alert.setHeaderText(null);
                alert.setContentText("No employee found with ID: " + txtSupId.getText());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Enter Supplier ID...");
            alert.show();
        }
    }

    public void btnDeleteSupplierOnAction(ActionEvent event) {
        if (txtSupId.getText() != null && !txtSupId.getText().isEmpty()) {
            if (supplierService.deleteSupplier(txtSupId.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Supplier Deleted SuccessFully");
                alert.show();
                loadSupplierTable();
                clearFieldsSupplier();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Supplier Didn't Found");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Enter Supplier ID...");
            alert.show();
        }
    }

    public void btnSupplierClearOnAction(MouseEvent mouseEvent) {
        clearFieldsSupplier();
    }

    //======================================================================ITEM MANAGEMENT==========================================================================
    final ItemService itemService = ServiceFactory.getInstance().getServiceType(ServiceType.ITEM);

    public void btnProductManagementOnAction(ActionEvent event) {
        loadSupplierId();
        currentPage.setVisible(false);
        currentPage = pageProduct;
        currentPage.setVisible(true);
    }

    private void loadItemTable() {
        tblItem.setItems(FXCollections.observableArrayList(itemService.getAll()));
    }

    private void loadCategoryMenu() {
        ObservableList<Object> category = FXCollections.observableArrayList();
        category.add("Ladies");
        category.add("Gents");
        category.add("Kids");
        cmbItemCategory.setItems(category);
    }

    private void loadSizeMenu() {
        ObservableList<Object> sizes = FXCollections.observableArrayList();
        sizes.add("XS");
        sizes.add("S");
        sizes.add("M");
        sizes.add("L");
        sizes.add("XL");
        sizes.add("XXL");
        cmbItemSize.setItems(sizes);
    }

    private void loadSupplierId() {
        List<Supplier> suppliers = supplierService.getAll();
        ObservableList<String> supplierIds = FXCollections.observableArrayList();
        for (Supplier supplier : suppliers) {
            supplierIds.add(supplier.getSupplierId());
        }
        cmbSupplierId.setItems(supplierIds);
    }

    private void setTextToValues(Item newValue) {
        txtItemId.setText(newValue.getItemId());
        txtItemName.setText(newValue.getName());
        txtItemQty.setText(String.valueOf(newValue.getQty()));
        txtItemUnitPrice.setText(String.valueOf(newValue.getPrice()));
        cmbItemCategory.setValue(newValue.getCategory());
        cmbItemSize.setValue(newValue.getSize());
        cmbSupplierId.setValue(newValue.getSupId());
    }

    public void clearFieldsItem() {
        txtItemId.setText(itemService.generateItemId());
        txtItemName.setText("");
        cmbItemCategory.setValue("");
        cmbItemSize.setValue("");
        cmbSupplierId.setValue("");
        txtItemQty.setText("");
        txtItemUnitPrice.setText("");
    }

    public void btnAddItemOnAction(ActionEvent event) {
        if (txtItemId.getText() != null && !txtItemId.getText().isEmpty() &&
                txtItemName.getText() != null && !txtItemName.getText().isEmpty() &&
                cmbItemCategory.getValue() != null &&
                cmbItemSize.getValue() != null &&
                cmbSupplierId.getValue() != null &&
                txtItemUnitPrice.getText() != null && !txtItemUnitPrice.getText().isEmpty() &&
                txtItemQty.getText() != null && !txtItemQty.getText().isEmpty()) {
            Item item = new Item(
                    txtItemId.getText(),
                    txtItemName.getText(),
                    cmbItemCategory.getValue().toString(),
                    cmbItemSize.getValue().toString(),
                    cmbSupplierId.getValue().toString(),
                    Double.parseDouble(txtItemUnitPrice.getText()),
                    Integer.parseInt(txtItemQty.getText())
            );
            ItemService itemService = ServiceFactory.getInstance().getServiceType(ServiceType.ITEM);
            if (itemService.addItem(item)) {
                loadItemTable();
                clearFieldsItem();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Item Added Successfully...");
                alert.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Failed to add the item.");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Fill all fields...");
            alert.show();
        }
    }

    public void btnUpdateItemOnAction(ActionEvent event) {
        if (txtItemId.getText() != null && !txtItemId.getText().isEmpty() &&
                txtItemName.getText() != null && !txtItemName.getText().isEmpty() &&
                cmbItemCategory.getValue() != null &&
                cmbItemSize.getValue() != null &&
                cmbSupplierId.getValue() != null &&
                txtItemUnitPrice.getText() != null && !txtItemUnitPrice.getText().isEmpty() &&
                txtItemQty.getText() != null && !txtItemQty.getText().isEmpty()) {
        Item item = new Item(
                txtItemId.getText(),
                txtItemName.getText(),
                cmbItemCategory.getValue().toString(),
                cmbItemSize.getValue().toString(),
                cmbSupplierId.getValue().toString(),
                Double.parseDouble(txtItemUnitPrice.getText()),
                Integer.parseInt(txtItemQty.getText())
        );
        if (itemService.updateItem(item)) {
            loadItemTable();
            clearFieldsItem();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Item Updated Successfully..");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Item didn't Updated ...");
            alert.show();
        }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Fill all fields...");
            alert.show();
        }
    }

    public void btnSearchItemOnAction(ActionEvent event) {
        if (txtItemId.getText() != null && !txtItemId.getText().isEmpty()) {
            Item item = itemService.searchItem(txtItemId.getText());
            if (item != null) {
                setTextToValues(item);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Item Not Found");
                alert.setHeaderText(null);
                alert.setContentText("No Item found with ID: " + txtItemId.getText());
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Enter Item ID...");
            alert.show();
        }
    }

    public void btnDeleteItemOnAction(ActionEvent event) {
        if (txtItemId.getText() != null && !txtItemId.getText().isEmpty()) {
        if (itemService.deleteItem(txtItemId.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Item Deleted SuccessFully");
            alert.show();
            loadItemTable();
            clearFieldsItem();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Item Didn't Found");
            alert.show();
        }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please Enter Item ID...");
            alert.show();
        }
    }

    public void btnItemClearOnAction(MouseEvent mouseEvent) {
        clearFieldsItem();
    }
    //====================================PLACE ORDER MANAGEMENT==============================================================
    final OrderService orderService = ServiceFactory.getInstance().getServiceType(ServiceType.ORDER);

    public void btnOrderManagementOnAction(ActionEvent event) {
        currentPage.setVisible(false);
        currentPage = pageOrderManage;
        currentPage.setVisible(true);
    }

    public void btnPlaceOrderOnAction(MouseEvent mouseEvent) {
        lblOrderId.setText(orderService.generateOrderId());
        loadCustomerId();
        loadItemId();
        currentPage.setVisible(false);
        currentPage = pagePlaceOrder;
        currentPage.setVisible(true);
    }

    public void btnViewOrderOnAction(MouseEvent mouseEvent) {
        loadViewOrderTable();
        currentPage.setVisible(false);
        currentPage = pageViewOrders;
        currentPage.setVisible(true);
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = f.format(date);
        lblDate.setText(dateNow);
        lblOrderDetailDate.setText(dateNow);


        timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            lblTime.setText(currentTime.format(formatter));
            lblOrderDetailTime.setText(currentTime.format(formatter));

        }), new KeyFrame(Duration.seconds(1)));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void loadCustomerId() {
        ObservableList<String> customerIds = customerService.getCustomerIds();
        cmbCustId.setItems(customerIds);
    }

    public void loadItemId() {
        ObservableList<String> itemIds = itemService.getItemIds();
        for (String itemId : itemIds) {
            System.out.println(itemId);
        }
        cmbItemCode.setItems(itemIds);
    }

    private void searchItemCode(String newValue) {
        Item item = itemService.searchItem(newValue);
        txtOrderItemName.setText(item.getName());
        txtOrderItemSize.setText(item.getSize());
        txtOrderItemCate.setText(item.getCategory());
        txtOrderItemStockLev.setText(String.valueOf(item.getQty()));
        txtOrderItemUnitPrice.setText(String.valueOf(item.getPrice()));
    }

    private void searchCustomer(String newValue) {
        Customer customer = customerService.searchCustomer(newValue);
        txtOrderCustName.setText(customer.getCustName());
        txtOrderCustAddress.setText(customer.getCustAddress());
        txtOrderCustContact.setText(customer.getCustContact());
    }

    public static Double discount = 0.0;

    private void calcNetTotal() {
        Double total = 0.0;
        for (CartTM cartTM : cartTMS) {
            total += cartTM.getTotal();
        }
        if (total < 0) {
            total = 0.0;
        }
        lblNetTotal.setText(total.toString() + " /=");
    }

    public void btnAddtoCartOnAction(ActionEvent event) {
        String itemId = cmbItemCode.getValue().toString();
        String name = txtOrderItemName.getText();
        String category = txtOrderItemCate.getText();
        String size = txtOrderItemSize.getText();
        double unitPrice = Double.parseDouble(txtOrderItemUnitPrice.getText());
        int qty = Integer.parseInt(txtOrderItemQty.getText());
        int currentStock = Integer.parseInt(txtOrderItemStockLev.getText());

        if (currentStock < qty) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Invalid QTY. Stock available: " + currentStock);
            alert.show();
        } else {
            double itemDiscount = Double.parseDouble(txtOrderItemDiscount.getText());
            double total = (unitPrice * qty) - itemDiscount;
            cartTMS.add(new CartTM(itemId, name, category, size, qty, unitPrice, total));
            calcNetTotal();
            updateStockRemove(itemId, qty);
            refreshStockDisplay(itemId);
            tblOrderDetails.setItems(cartTMS);
            discount += itemDiscount;
        }
    }

    private int updateStockRemove(String itemId, int qtyToAdd) {
        Item item = itemService.searchItem(itemId);
        int updatedStock = item.getQty();
        try {
            if (item != null) {
                int currentStock = item.getQty();
                updatedStock = currentStock - qtyToAdd;
                item.setQty(updatedStock);
                itemService.updateItem(item);
                loadItemTable();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error updating stock: " + e.getMessage());
            alert.show();
        }
        return updatedStock;
    }

    private int updateStock(String itemId, int qtyToRemove) {
        Item item = itemService.searchItem(itemId);
        int updatedStock = item.getQty();
        try {
            if (item != null) {
                int currentStock = item.getQty();
                updatedStock = currentStock + qtyToRemove;
                item.setQty(updatedStock);
                itemService.updateItem(item);
                loadItemTable();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error updating stock: " + e.getMessage());
            alert.show();
        }
        return updatedStock;
    }

    private void refreshStockDisplay(String itemId) {
        Item item = itemService.searchItem(itemId);
        if (item != null) {
            txtOrderItemStockLev.setText(String.valueOf(item.getQty()));
        }
    }

    ObservableList<CartTM> cartTMS = FXCollections.observableArrayList();

    public void clearFieldsOrder() {
        btncClearOnAction(new ActionEvent());
        txtOrderEmpId.setText("");
        txtOrderEmpEmali.setText("");
        cmbCustId.setValue(null);
        txtOrderCustName.setText("");
        txtOrderCustAddress.setText("");
        txtOrderCustContact.setText("");
        lblOrderId.setText(orderService.generateOrderId());
    }

    public void btncClearOnAction(ActionEvent event) {
        cmbItemCode.setValue(null);
        txtOrderItemName.setText("");
        txtOrderItemCate.setText("");
        txtOrderItemStockLev.setText("");
        txtOrderItemQty.setText("");
        txtOrderItemSize.setText("");
        txtOrderItemDiscount.setText("");
        txtOrderItemDiscount.setText("");
        txtOrderItemUnitPrice.setText("");

    }

    public void btnRemoveOnAction(ActionEvent event) {
        CartTM selectedItem = tblOrderDetails.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            String itemId = selectedItem.getItemId();
            int qtyToRemove = selectedItem.getQty();
            cartTMS.remove(selectedItem);
            updateStock(itemId, qtyToRemove);
            refreshStockDisplay(itemId);
            int qty = selectedItem.getQty();
            double unitPrice = selectedItem.getPrice();
            double total = selectedItem.getTotal();
            double removeDiscount = (qty * unitPrice) - total;
            discount -= removeDiscount;
            calcNetTotal();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Select a table row to delete");
            alert.show();
        }
    }

    public void btnPlaceOrderCartOnAction(ActionEvent event) {
        if (cartTMS.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "No items in the cart to place an order.");
            alert.show();
        } else {

            List<OrderDetails> orderDetailsList = new ArrayList<>();

            cartTMS.forEach(obj -> {
                orderDetailsList.add(new OrderDetails(lblOrderId.getText(), obj.getItemId(), obj.getQty(), obj.getPrice()));
            });
            Order order = new Order(lblOrderId.getText(),txtOrderEmpId.getText(),cmbCustId.getValue().toString(), LocalDate.now(), LocalTime.now(),lblNetTotal.getText(),orderDetailsList);
            orderService.placeOrder(order);

            Receipt receipt = new Receipt(
                    lblOrderId.getText(),
                    txtOrderCustName.getText(),
                    txtOrderCustContact.getText(),
                    txtOrderCustAddress.getText(),
                    new ArrayList<>(cartTMS),
                    discount,
                    Double.parseDouble(lblNetTotal.getText().split(" ")[0]),
                    LocalDateTime.now()
            );

            cartTMS.clear();
            lblNetTotal.setText("0/=");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Order placed successfully!");
            alert.show();
            showReceipt(receipt);
            clearFieldsOrder();
        }
    }

    private void showReceipt(Receipt receipt) {
        Stage receiptStage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/receiptForm.fxml"));
            Parent root = loader.load();

            ReceiptFormController controller = loader.getController();
            controller.initializeReceipt(receipt);

            receiptStage.setScene(new Scene(root));
            receiptStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentPage = pageViewOrders;
        currentPage.setVisible(true);
/////////////////////////////////////////////REPORT GEN/////////////////////////////////////////////////////////////////
        StackedBarChart<String, Number> stackedBarChart = createStackedBarChart();
        chartContainer.getChildren().add(stackedBarChart);
        loadPieChart();
/////////////////////////////////////////////CUSTOMER///////////////////////////////////////////////////////////////////
        txtCustId.setText(customerService.generateCustomerId());
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
/////////////////////////////////////////////EMPLOYEE///////////////////////////////////////////////////////////////////
        txtEmployeeId.setText(employeeService.generateEmployeeId());
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        colEmpNic.setCellValueFactory(new PropertyValueFactory<>("employeeNic"));
        colEmpAddress.setCellValueFactory(new PropertyValueFactory<>("employeeAddress"));
        colEmpEmail.setCellValueFactory(new PropertyValueFactory<>("employeeEmailAddress"));
        colEmpContact.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));

        tblEmployee.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValues((Employee) newValue);
            }
        }));
        loadEmployeeTable();
/////////////////////////////////////////////SUPPLIER///////////////////////////////////////////////////////////////////
        txtSupId.setText(supplierService.generateSupplierId());
        colSupId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colSupCompany.setCellValueFactory(new PropertyValueFactory<>("supplierCompany"));
        colSupAddress.setCellValueFactory(new PropertyValueFactory<>("supplierAddress"));
        colSupEmail.setCellValueFactory(new PropertyValueFactory<>("supplierEmailAddress"));
        colSupContact.setCellValueFactory(new PropertyValueFactory<>("supplierContactNumber"));
        colSupPostalCode.setCellValueFactory(new PropertyValueFactory<>("supplierPostalCode"));

        tblSupplier.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValues((Supplier) newValue);
            }
        }));
        loadSupplierTable();
///////////////////////////////////////////////ITEM/////////////////////////////////////////////////////////////////////
        txtItemId.setText(itemService.generateItemId());
        colItemtId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colItemSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colItemQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblItem.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValues((Item) newValue);
            }
        }));
        loadItemTable();
        loadCategoryMenu();
        loadSizeMenu();
        loadSupplierId();
        loadDateAndTime();
        loadViewOrderTable();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //lblOrderId.setText(orderService.generateOrderId());
        colOrderItemCode.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colOrderItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colOrderItemCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colOrderItemSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colOrderItemQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colOrderItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colOrderItemTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        loadCustomerId();
        loadItemId();
        cmbCustId.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newValue) -> {
            if (newValue != null) {
                searchCustomer((String) newValue);
            }
        });
        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newValue) -> {
            if (newValue != null) {
                searchItemCode((String) newValue);
            }
        });

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colViewEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colViewCustId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colViewDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colViewTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colViewNetTotal.setCellValueFactory(new PropertyValueFactory<>("netTotal"));

    }
    private void loadViewOrderTable() {
        tblViewOrder.setItems(FXCollections.observableArrayList(orderService.getAll()));
    }
}