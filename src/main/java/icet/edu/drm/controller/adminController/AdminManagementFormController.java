package icet.edu.drm.controller.adminController;

import icet.edu.drm.model.Customer;
import icet.edu.drm.model.Employee;
import icet.edu.drm.model.Supplier;
import icet.edu.drm.service.custom.CustomerService;
import icet.edu.drm.service.custom.EmployeeService;
import icet.edu.drm.service.custom.SupplierService;
import icet.edu.drm.service.custom.impl.CustomerServiceImpl;
import icet.edu.drm.service.custom.impl.EmployeeServiceImpl;
import icet.edu.drm.service.custom.impl.SupplierServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
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

    private AnchorPane currentPage;


    //====================================DASHBOARD MANAGEMENT==============================================================
    public void btnSupplierManagementOnAction(ActionEvent event) {
        lblSupId.setText(supplierService.generateSupplierId());
        currentPage.setVisible(false);
        currentPage = pageSupplier;
        currentPage.setVisible(true);
    }

    public void btnCustomerManagementOnAction(ActionEvent event) {
        lblCustId.setText(customerService.generateCustomerId());
        currentPage.setVisible(false);
        currentPage = pageCustomer;
        currentPage.setVisible(true);
    }

    public void btnProductManagementOnAction(ActionEvent event) {
        currentPage.setVisible(false);
        currentPage = pageProduct;
        currentPage.setVisible(true);
    }

    public void btnEmployeeManagementOnAction(ActionEvent event) {
        lblEmployeeId.setText(employeeService.generateEmployeeId());
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

//======================================================================================================================

    //====================================EMPLOYEE MANAGEMENT===============================================================
    final EmployeeService employeeService = new EmployeeServiceImpl();

    private void loadEmployeeTable() {
        tblEmployee.setItems(FXCollections.observableArrayList(employeeService.getEmployee()));
    }

    private void setTextToValues(Employee newValue) {
        lblEmployeeId.setText(newValue.getId());
        txtEmployeeName.setText(newValue.getName());
        txtContact.setText(newValue.getContact());
        txtEmployeeNic.setText(newValue.getNic());
        txtEmployeeEmail.setText(newValue.getEmail());
        txtEmployeeAddress.setText(newValue.getAddress());
    }

    public void btnAddEmployeeOnAction(ActionEvent event) {
        Random random = new Random();
        int p = random.nextInt(99999999) + 10000000;

        String encrypt = Integer.toString(p);
        String password = employeeService.passwordEncrypt(encrypt);

        Employee employee = new Employee(
                lblEmployeeId.getText(),
                txtEmployeeName.getText(),
                txtContact.getText(),
                txtEmployeeNic.getText(),
                txtEmployeeAddress.getText(),
                txtEmployeeEmail.getText(),
                password
        );
        if (!txtEmployeeName.getText().equals("") && employeeService.isValidEmail(txtEmployeeEmail.getText()) && !txtEmployeeAddress.getText().equals("")) {


            boolean isInsert = employeeService.addEmployee(employee);
            if (isInsert) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Employee Added");
                alert.setContentText("Employee Added Successfully..!");
                alert.showAndWait();
                clearFieldsEmployee();
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "Somthing Wrong..!!!").show();
        }
    }

    public void btnUpdateEmployeeOnAction(ActionEvent event) {
        Random random = new Random();
        int p = random.nextInt(99999999) + 10000000;

        String encrypt = Integer.toString(p);
        String password = employeeService.passwordEncrypt(encrypt);
        if (!txtEmployeeName.getText().equals("") && employeeService.isValidEmail(txtEmployeeEmail.getText()) && !txtEmployeeAddress.getText().equals("")) {
            Employee employee = new Employee(
                    lblEmployeeId.getText(),
                    txtEmployeeName.getText(),
                    txtContact.getText(),
                    txtEmployeeNic.getText(),
                    txtEmployeeAddress.getText(),
                    txtEmployeeEmail.getText(),
                    password
            );
            boolean isUpdated = employeeService.updateEmployee(employee);
            if (isUpdated) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Employee Update");
                alert.setContentText("Employee Updated Successfully");
                alert.showAndWait();
                clearFieldsEmployee();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Something Missing");
            alert.setContentText("Please Check your Form again..!!!");
            alert.showAndWait();
        }
    }

    public void btnDeleteEmployeeOnAction(ActionEvent event) {
        if (!txtEmployeeName.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleting");
            alert.setContentText("Are you sure want to delete this Employee");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                boolean isDeleted = employeeService.deleteEmployee(lblEmployeeId.getText());
                if (isDeleted) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Employee Deleted");
                    alert2.setContentText("Employee deleted successfully");
                    alert2.showAndWait();
                    clearFieldsEmployee();
                }
            }
        }
    }

    public void clearFieldsEmployee() {
        loadEmployeeTable();
        lblEmployeeId.setText(employeeService.generateEmployeeId());
        txtEmployeeAddress.setText("");
        txtEmployeeName.setText("");
        txtEmployeeEmail.setText("");
        txtEmployeeNic.setText("");
        txtContact.setText("");
        txtEmployeePassword.setText("");
    }

    public void btnSearchEmployeeOnAction(ActionEvent event) {
        try {
            Employee employee = employeeService.searchByName(txtEmployeeName.getText());
            if (employee != null) {
                setTextToValues(employee);
            } else {
                new Alert(Alert.AlertType.ERROR, "Employee not found").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //======================================================================================================================


    //====================================CUSTOMER MANAGEMENT===============================================================
    final CustomerService customerService = new CustomerServiceImpl();

    private void loadTitleMenu() {
        ObservableList<Object> title = FXCollections.observableArrayList();
        title.add("Mr.");
        title.add("Mrs.");
        title.add("Miss.");
        title.add("Master.");
        cmbCustTitle.setItems(title);
    }

    private void loadCustomerTable() {
        tblCustomer.setItems(FXCollections.observableArrayList(customerService.getCustomer()));
    }

    private void setTextToValues(Customer newValue) {
        lblCustId.setText(newValue.getId());
        cmbCustTitle.setValue(newValue.getTitle());
        txtCustName.setText(newValue.getName());
        txtCustContact.setText(newValue.getContact());
        txtCustAddress.setText(newValue.getAddress());
        txtCustCity.setText(newValue.getCity());
        txtCustProvince.setText(newValue.getProvince());
        txtCustPostalCode.setText(newValue.getPostalCode());
        txtCustEmail.setText(newValue.getEmail());
    }

    public void clearFieldsCustomer() {
        loadCustomerTable();
        lblCustId.setText(customerService.generateCustomerId());
        cmbCustTitle.setValue("");
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
                lblCustId.getText(),
                cmbCustTitle.getValue().toString(),
                txtCustName.getText(),
                txtCustContact.getText(),
                txtCustAddress.getText(),
                txtCustCity.getText(),
                txtCustProvince.getText(),
                txtCustPostalCode.getText(),
                txtCustEmail.getText()
        );
        if (!cmbCustTitle.getValue().equals("") && !txtCustName.getText().equals("") && !txtCustAddress.getText().equals("")) {
            boolean isInsert = customerService.addCustomer(customer);
            if (isInsert) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Added");
                alert.setContentText("Customer Added Successfully..!");
                alert.showAndWait();
                clearFieldsCustomer();
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "Somthing Wrong..!!!").show();
        }
    }

    public void btnDeleteCustomerOnAction(ActionEvent event) {
        if (!txtCustName.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleting");
            alert.setContentText("Are you sure want to delete this Customer");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                boolean isDeleted = customerService.deleteCustomer(lblCustId.getText());
                if (isDeleted) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Customer Deleted");
                    alert2.setContentText("Customer deleted successfully");
                    alert2.showAndWait();
                    clearFieldsCustomer();
                }
            }
        }
    }

    public void btnSearchCustomerOnAction(ActionEvent event) {
        try {
            Customer customer = customerService.searchByName(txtCustName.getText());
            if (customer != null) {
                setTextToValues(customer);
            } else {
                new Alert(Alert.AlertType.ERROR, "Customer not found").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateCustomerOnAction(ActionEvent event) {
        if (!cmbCustTitle.getValue().equals("") && !txtCustName.getText().equals("") && !txtCustAddress.getText().equals("")) {
            Customer customer = new Customer(
                    lblCustId.getText(),
                    cmbCustTitle.getValue().toString(),
                    txtCustName.getText(),
                    txtCustContact.getText(),
                    txtCustAddress.getText(),
                    txtCustCity.getText(),
                    txtCustProvince.getText(),
                    txtCustPostalCode.getText(),
                    txtCustEmail.getText()
            );
            boolean isUpdated = customerService.updateCustomer(customer);
            if (isUpdated) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Update");
                alert.setContentText("Customer Updated Successfully");
                alert.showAndWait();
                clearFieldsCustomer();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Something Missing");
            alert.setContentText("Please Check your Form again..!!!");
            alert.showAndWait();
        }
    }
    //====================================SUPPLIER MANAGEMENT===============================================================
    final SupplierService supplierService = new SupplierServiceImpl();

    private void loadSupplierTable() {
        tblSupplier.setItems(FXCollections.observableArrayList(supplierService.getSupplier()));
    }

    private void setTextToValues(Supplier newValue) {
        lblSupId.setText(newValue.getId());
        txtSupName.setText(newValue.getName());
        txtSupCompany.setText(newValue.getCompanyName());
        txtSupContact.setText(newValue.getContact());
        txtSupCity.setText(newValue.getCity());
        txtSupPostalCode.setText(newValue.getPostalCode());
        txtSupCompanyEmail.setText(newValue.getComEmail());
        txtSupCompanyAddress.setText(newValue.getAddress());
    }

    public void clearFieldsSupplier() {
        loadSupplierTable();
        lblSupId.setText(supplierService.generateSupplierId());
        txtSupName.setText("");
        txtSupCompany.setText("");
        txtSupContact.setText("");
        txtSupCity.setText("");
        txtSupPostalCode.setText("");
        txtSupCompanyEmail.setText("");
        txtSupCompanyAddress.setText("");
    }
    public void btnAddSupplierOnAction(ActionEvent event) {
        Supplier supplier = new Supplier(
                lblSupId.getText(),
                txtSupName.getText(),
                txtSupCompany.getText(),
                txtSupContact.getText(),
                txtSupCity.getText(),
                txtSupPostalCode.getText(),
                txtSupCompanyEmail.getText(),
                txtSupCompanyAddress.getText()
        );
        if (!txtSupName.getText().equals("") && !txtSupContact.getText().equals("")) {
            boolean isInsert = supplierService.addSupplier(supplier);
            if (isInsert) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Supplier Added");
                alert.setContentText("Supplier Added Successfully..!");
                alert.showAndWait();
                clearFieldsSupplier();
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "Somthing Wrong..!!!").show();
        }
    }

    public void btnUpdateSupplierOnAction(ActionEvent event) {
        if (!txtSupName.getText().equals("") && !txtSupContact.getText().equals("")) {
            Supplier supplier = new Supplier(
                    lblSupId.getText(),
                    txtSupName.getText(),
                    txtSupCompany.getText(),
                    txtSupContact.getText(),
                    txtSupCity.getText(),
                    txtSupPostalCode.getText(),
                    txtSupCompanyEmail.getText(),
                    txtSupCompanyAddress.getText()
            );
            boolean isUpdated = supplierService.updateSupplier(supplier);
            if (isUpdated) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Supplier Update");
                alert.setContentText("Supplier Updated Successfully");
                alert.showAndWait();
                clearFieldsSupplier();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Something Missing");
            alert.setContentText("Please Check your Form again..!!!");
            alert.showAndWait();
        }
    }

    public void btnSerachSupplierOnAction(ActionEvent event) {
        try {
            Supplier supplier = supplierService.searchByName(txtSupName.getText());
            if (supplier != null) {
                setTextToValues(supplier);
            } else {
                new Alert(Alert.AlertType.ERROR, "Supplier not found").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnDeleteSupplierOnAction(ActionEvent event) {
        if (!txtSupName.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleting");
            alert.setContentText("Are you sure want to delete this Supplier");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                boolean isDeleted = supplierService.deleteSupplier(lblSupId.getText());
                if (isDeleted) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Supplier Deleted");
                    alert2.setContentText("Sipplier deleted successfully");
                    alert2.showAndWait();
                    clearFieldsSupplier();
                }
            }
        }
    }

    //======================================================================================================================


    //======================================================================================================================
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentPage = pageCustomer;
        currentPage.setVisible(true);


        colEmpId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmpNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colEmpAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmpEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tblEmployee.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValues((Employee) newValue);
            }
        }));
        loadEmployeeTable();

        loadTitleMenu();

        colCustId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colCustName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colCustAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCustCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colCustProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colCustPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        colCustEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tblCustomer.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValues((Customer) newValue);
            }
        }));
        loadCustomerTable();

        colSupId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSupCompany.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        colSupAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSupEmail.setCellValueFactory(new PropertyValueFactory<>("comEmail"));
        colSupContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colSupPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        tblSupplier.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValues((Supplier) newValue);
            }
        }));
        loadSupplierTable();
    }

}