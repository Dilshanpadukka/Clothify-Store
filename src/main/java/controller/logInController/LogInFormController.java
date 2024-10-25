package controller.logInController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Employee;
import service.ServiceFactory;
import service.custom.EmployeeService;
import util.OTPUtil;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInFormController implements Initializable {
    public AnchorPane pageEmployeeLogIn;
    public JFXTextField txtEmployeeEmail;
    public JFXPasswordField txtEmployeePassword;
    public AnchorPane pageAdminLogIn;
    public JFXTextField txtAdminEmail;
    public JFXPasswordField txtAdminPassword;
    public AnchorPane pageSignUp;
    public AnchorPane pageForgotPassword;
    public JFXTextField txtForgotPwEmail;
    public AnchorPane pageResetPassword;
    public JFXTextField txtOTP;
    public JFXPasswordField txtResetPassword;
    public JFXTextField txtRegisterEmpEmail;
    public JFXPasswordField txtRegisterEmpPassword;
    public JFXTextField txtRegisterEmpName;
    public JFXPasswordField txtRegisterEmpConfirmPassword;
    public Label lblRegisterEmpID;
    public JFXTextField txtRegisterEmpNIC;
    public JFXTextField txtRegisterEmpContact;
    public JFXTextField txtRegisterEmpAddress;

    private AnchorPane currentPage;

    public void btnEmployeeSignUpOnAction(MouseEvent mouseEvent) {
        currentPage.setVisible(false);
        currentPage = pageSignUp;
        currentPage.setVisible(true);
        lblRegisterEmpID.setText(employeeService.generateEmployeeId());
    }
    public void btnAdminSigninOnAction(MouseEvent mouseEvent) {
        currentPage.setVisible(false);
        currentPage = pageAdminLogIn;
        currentPage.setVisible(true);
    }

    public void btnEmployeeSigninOnAction(MouseEvent mouseEvent) {
        currentPage.setVisible(false);
        currentPage = pageEmployeeLogIn;
        currentPage.setVisible(true);
    }

    public void btnBackToSigninOnAction(MouseEvent mouseEvent) {
        currentPage.setVisible(false);
        currentPage = pageEmployeeLogIn;
        currentPage.setVisible(true);
    }

    final EmployeeService employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentPage = pageEmployeeLogIn;
        currentPage.setVisible(true);
    }

    public void btnAdminLoginOnAction(ActionEvent event) {
        String systemAdmin = "admin";
        String systemPassword = "admin12345";
        String email = txtAdminEmail.getText();
        String password = txtAdminPassword.getText();


        if (systemAdmin.equals(email) && systemPassword.equals(password)) {
            Stage newStage = new Stage();
            try {
                newStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/adminManagementForm.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            newStage.show();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } else {
            new Alert(Alert.AlertType.WARNING, "Invalid User Name Or Password.").show();
        }
    }


    public void btnRegisterOnAction(ActionEvent event) {
        String empId = lblRegisterEmpID.getText();
        String name = txtRegisterEmpName.getText();
        String nic = txtRegisterEmpNIC.getText();
        String contact = txtRegisterEmpContact.getText();
        String address = txtRegisterEmpAddress.getText();
        String emailAddress = txtRegisterEmpEmail.getText();
        String password = txtRegisterEmpPassword.getText();
        String confirmPassword = txtRegisterEmpConfirmPassword.getText();

        if (!password.equals(confirmPassword)) {
            new Alert(Alert.AlertType.INFORMATION, "Your passwords do not match, please re-enter.").show();
        } else {
            if (employeeService.addEmployee(empId,name, nic,contact,address, emailAddress, password)) {
                new Alert(Alert.AlertType.INFORMATION, "You are registered successfully!").show();
                currentPage.setVisible(false);
                currentPage = pageEmployeeLogIn;
                currentPage.setVisible(true);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Registration failed. Please try again later.").show();
            }
        }
    }


    public void btnEmployeeLoginOnAction(ActionEvent event) {
        String email = txtEmployeeEmail.getText();
        String password = txtEmployeePassword.getText();


        Employee employee = employeeService.loginEmployee(email, password);

        if (employee != null) {
            Stage newStage = new Stage();
            try {
                newStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/employeeManagementForm.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            newStage.show();
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } else {
            new Alert(Alert.AlertType.WARNING, "Invalid Employee Name Or Password.").show();
        }
    }

    public void btnEmployeeForgetPwOnAction(MouseEvent mouseEvent) {
        currentPage.setVisible(false);
        currentPage = pageForgotPassword;
        currentPage.setVisible(true);

    }

    public void btnSendOTPOnAction(ActionEvent event) {
        String email = txtForgotPwEmail.getText();
        if (email.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter your email address.").show();
            return;
        }
        Employee employee = employeeService.findEmployeeByEmail(email);
        if (employee == null) {
            new Alert(Alert.AlertType.WARNING, "No Employee found with this email address.").show();
            return;
        }

        String otp = OTPUtil.generateOTP();
        employeeService.storeOTP(email, otp);
        System.out.println("Generated OTP: " + otp);

        boolean emailSent = employeeService.sendOTPEmail(email, otp);
        if (emailSent) {
            new Alert(Alert.AlertType.INFORMATION, "OTP has been sent to your email.").show();
            // Get the controller and set the email address
            setEmailAddress(email);
            currentPage.setVisible(false);
            currentPage = pageResetPassword;
            currentPage.setVisible(true);
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to send OTP. Please try again.").show();
        }

    }
private String emailAddress;
    public void setEmailAddress(String email) {
        this.emailAddress = email;
        System.out.println("Email Address Set: " + this.emailAddress);
    }
    public void resetPassword() {
        String otp = txtOTP.getText().trim();
        String newPassword = txtResetPassword.getText().trim();
        System.out.println("Entered OTP: " + otp);
        System.out.println("Email Address: " + emailAddress);

        if (employeeService.validateOTP(emailAddress, otp)) {
            employeeService.resetPassword(emailAddress, newPassword);
            new Alert(Alert.AlertType.INFORMATION, "Password has been reset.").show();
            currentPage.setVisible(false);
            currentPage = pageEmployeeLogIn;
            currentPage.setVisible(true);
        } else {
            new Alert(Alert.AlertType.WARNING, "Invalid OTP...").show();
        }
    }
    public void btnResetPasswordOnAction(ActionEvent event) {
        resetPassword();
    }

    public void btnForgotBackSignInOnAction(MouseEvent mouseEvent) {
        currentPage.setVisible(false);
        currentPage = pageEmployeeLogIn;
        currentPage.setVisible(true);
    }

    public void btnResetBackSignInOnAction(MouseEvent mouseEvent) {
        currentPage.setVisible(false);
        currentPage = pageEmployeeLogIn;
        currentPage.setVisible(true);
    }
}