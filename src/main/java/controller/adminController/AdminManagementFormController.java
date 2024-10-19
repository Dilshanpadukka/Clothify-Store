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
import model.Employee;
import model.Item;
import model.Supplier;
import service.ServiceFactory;
import service.custom.CustomerService;
import service.custom.EmployeeService;
import service.custom.ItemService;
import service.custom.SupplierService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
    }

    //====================================================================EMPLOYEE MANAGEMENT==========================================================================
    final EmployeeService employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);

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
        txtEmployeeId.setText(newValue.getEmployeeId());
        txtEmployeeName.setText(newValue.getEmployeeName());
        txtEmployeeContact.setText(newValue.getContactNumber());
        txtEmployeeNic.setText(newValue.getEmployeeNic());
        txtEmployeeEmail.setText(newValue.getEmployeeEmailAddress());
        txtEmployeeAddress.setText(newValue.getEmployeeAddress());
    }

    public void clearFieldsEmployee() {
        txtEmployeeId.setText(employeeService.generateEmployeeId());
        txtEmployeeAddress.setText("");
        txtEmployeeName.setText("");
        txtEmployeeEmail.setText("");
        txtEmployeeNic.setText("");
        txtEmployeeContact.setText("");
    }

    public void btnAddEmployeeOnAction(ActionEvent event) {
        Employee employee = new Employee(
                txtEmployeeId.getText(),
                txtEmployeeName.getText(),
                txtEmployeeNic.getText(),
                txtEmployeeAddress.getText(),
                txtEmployeeEmail.getText(),
                txtEmployeeContact.getText()
        );
        if (employeeService.addEmployee(employee)) {
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
    }

    public void btnUpdateEmployeeOnAction(ActionEvent event) {
        Employee employee = new Employee(
                txtEmployeeId.getText(),
                txtEmployeeName.getText(),
                txtEmployeeNic.getText(),
                txtEmployeeAddress.getText(),
                txtEmployeeEmail.getText(),
                txtEmployeeContact.getText()
        );
        if (employeeService.updateEmployee(employee)) {
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
    }

    public void btnDeleteEmployeeOnAction(ActionEvent event) {
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
    }

    public void btnUpdateSupplierOnAction(ActionEvent event) {
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
    }

    public void btnSerachSupplierOnAction(ActionEvent event) {
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
    }

    public void btnDeleteSupplierOnAction(ActionEvent event) {
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
    private void loadSupplierId(){
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
        if (itemService.addItem(item)){
            loadItemTable();
            clearFieldsItem();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Item Added Successfully...");
            alert.show();

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Failed to add the item.");
            alert.show();
        }
    }

    public void btnUpdateItemOnAction(ActionEvent event) {
        Item item = new Item(
                txtItemId.getText(),
                txtItemName.getText(),
                cmbItemCategory.getValue().toString(),
                cmbItemSize.getValue().toString(),
                cmbSupplierId.getValue().toString(),
                Double.parseDouble(txtItemUnitPrice.getText()),
                Integer.parseInt(txtItemQty.getText())
        );
        if(itemService.updateItem(item)){
            loadItemTable();
            clearFieldsItem();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Item Updated Successfully..");
            alert.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Item didn't Updated ...");
            alert.show();
        }
    }

    public void btnSearchItemOnAction(ActionEvent event) {
        Item item = itemService.searchItem(txtItemId.getText());
        if (item!=null) {
            setTextToValues(item);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Item Not Found");
            alert.setHeaderText(null);
            alert.setContentText("No Item found with ID: " + txtItemId.getText());
            alert.showAndWait();
        }
    }

    public void btnDeleteItemOnAction(ActionEvent event) {
        if(itemService.deleteItem(txtItemId.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Item Deleted SuccessFully");
            alert.show();
            loadItemTable();
            clearFieldsItem();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Item Didn't Found");
            alert.show();
        }
    }
    public void btnItemClearOnAction(MouseEvent mouseEvent) {
        clearFieldsItem();
    }

    //====================================DASHBOARD MANAGEMENT==============================================================



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


    public void btnRemoveOnAction(ActionEvent event) {
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentPage = pageCustomer;
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

    }

}