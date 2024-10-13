package icet.edu.drm.controller.logInController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInFormController {
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;
    public JFXButton btnLogin;

    public void btnLoginOnAction(ActionEvent event) {
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        Stage stage=new Stage();
        if (email.equals("Admin") && password.equals("Admin")){
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/adminManagementForm.fxml"))));
                stage.setTitle("Clothify Shop Management System | Admin DashBoard");
                new Alert(Alert.AlertType.INFORMATION,"Log In Successfully").showAndWait();
                btnLogin.getScene().getWindow().hide();
                stage.show();
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR,"LogIn Failed");
            }
        }else if (email.equals("Employee") && password.equals("Employee")) {
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/employeeManagementForm.fxml"))));
                stage.setTitle("Clothify Shop Management System | Employee DashBoard");
                new Alert(Alert.AlertType.INFORMATION, "Log In Successfully").showAndWait();
                btnLogin.getScene().getWindow().hide();
                stage.show();
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR, "Login Failed");
            }
        }
    }
}
