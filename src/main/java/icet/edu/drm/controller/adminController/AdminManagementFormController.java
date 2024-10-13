package icet.edu.drm.controller.adminController;

import icet.edu.drm.model.Employee;
import icet.edu.drm.service.custom.impl.EmployeeServiceImpl;
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

    private AnchorPane currentPage;


    EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

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
        loadTable();
    }

    private void setTextToValues(Employee newValue) {
        lblEmployeeId.setText(newValue.getId());
        txtEmployeeName.setText(newValue.getName());
        txtContact.setText(newValue.getContact());
        txtEmployeeNic.setText(newValue.getNic());
        txtEmployeeEmail.setText(newValue.getEmail());
        txtEmployeeAddress.setText(newValue.getEmail());
    }

    private void loadTable() {
        tblEmployee.setItems(employeeServiceImpl.getAllUsers());
    }

    public void btnSupplierManagementOnAction(ActionEvent event) {
        currentPage.setVisible(false);
        currentPage = pageSupplier;
        currentPage.setVisible(true);
    }

    public void btnCustomerManagementOnAction(ActionEvent event) {
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
        lblEmployeeId.setText(employeeServiceImpl.generateEmployeeId());
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
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddEmployeeOnAction(ActionEvent event) {
        Random random = new Random();
        int p = random.nextInt(99999999) + 10000000;

        String encrypt = Integer.toString(p);
        String password = employeeServiceImpl.passwordEncrypt(encrypt);

        Employee employee = new Employee(
                lblEmployeeId.getText(),
                txtEmployeeName.getText(),
                txtContact.getText(),
                txtEmployeeNic.getText(),
                txtEmployeeAddress.getText(),
                txtEmployeeEmail.getText(),
                password
        );
        if (!txtEmployeeName.getText().equals("") && employeeServiceImpl.isValidEmail(txtEmployeeEmail.getText()) && !txtEmployeeAddress.getText().equals("")) {


            boolean isInsert = employeeServiceImpl.insertUser(employee);
            if (isInsert) {
                //Table1.setItems(userBoImpl.getAllUsers());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Employee Added");
                alert.setContentText("Employee Added Successfully..!");
                alert.showAndWait();
                lblEmployeeId.setText(employeeServiceImpl.generateEmployeeId());
                txtEmployeeAddress.setText("");
                txtEmployeeName.setText("");
                txtEmployeeEmail.setText("");
                txtEmployeeNic.setText("");
                txtContact.setText("");
                txtEmployeePassword.setText("");
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "Somthing Wrong..!!!").show();
        }
        loadTable();
    }
}
