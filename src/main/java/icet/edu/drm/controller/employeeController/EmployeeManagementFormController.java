package icet.edu.drm.controller.employeeController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeManagementFormController implements Initializable {
    public AnchorPane pageCustomer;
    public AnchorPane pageSupplier;
    public AnchorPane pageProduct;
    public AnchorPane pageOrderManage;
    public AnchorPane pagePlaceOrder;
    public AnchorPane pageViewOrders;

    private AnchorPane currentPage;
    public void btnSupplierManagementOnAction(ActionEvent event) {
        currentPage.setVisible(false);
        currentPage=pageSupplier;
        currentPage.setVisible(true);
    }

    public void btnCustomerManagementOnAction(ActionEvent event) {
        currentPage.setVisible(false);
        currentPage=pageCustomer;
        currentPage.setVisible(true);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentPage=pageCustomer;
        currentPage.setVisible(true);

    }


    public void btnProductManagementOnAction(ActionEvent event) {
        currentPage.setVisible(false);
        currentPage=pageProduct;
        currentPage.setVisible(true);
    }


    public void btnOrderManagementOnAction(ActionEvent event) {
        currentPage.setVisible(false);
        currentPage=pageOrderManage;
        currentPage.setVisible(true);
    }

    public void btnPlaceOrderOnAction(MouseEvent mouseEvent) {
        currentPage.setVisible(false);
        currentPage=pagePlaceOrder;
        currentPage.setVisible(true);

    }

    public void btnViewOrderOnAction(MouseEvent mouseEvent) {
        currentPage.setVisible(false);
        currentPage=pageViewOrders;
        currentPage.setVisible(true);
    }
    public void btnViewOrderOnAction2(MouseEvent mouseEvent) {
        currentPage.setVisible(false);
        currentPage=pageViewOrders;
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
}
