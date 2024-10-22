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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;
import service.ServiceFactory;
import service.custom.UserService;
import util.OTPUtil;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInFormController implements Initializable {
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;
    public JFXButton btnLogin;
    public AnchorPane pageEmployeeLogIn;
    public JFXTextField txtEmployeeEmail;
    public JFXPasswordField txtEmployeePassword;
    public AnchorPane pageAdminLogIn;
    public JFXTextField txtAdminEmail;
    public JFXPasswordField txtAdminPassword;
    public AnchorPane pageSignUp;
    public JFXTextField txtSignUpEmail;
    public JFXPasswordField txtSignUpPassword;
    public JFXTextField txtSignUpFirstName;
    public JFXTextField txtSignUpLastName;
    public JFXPasswordField txtSignUpConfirmPassword;
    public AnchorPane pageForgotPassword;
    public JFXTextField txtForgotPwEmail;
    public AnchorPane pageResetPassword;
    public JFXTextField txtOTP;
    public JFXPasswordField txtResetPassword;

    private AnchorPane currentPage;

//    public void btnLoginOnAction(ActionEvent event) {
//        String email = txtEmail.getText();
//        String password = txtPassword.getText();
//        Stage stage=new Stage();
//        if (email.equals("Admin") && password.equals("Admin")){
//            try {
//                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/adminManagementForm.fxml"))));
//                stage.setTitle("Clothify Shop Management System | Admin DashBoard");
//                new Alert(Alert.AlertType.INFORMATION,"Log In Successfully").showAndWait();
//                btnLogin.getScene().getWindow().hide();
//                stage.show();
//            } catch (IOException e) {
//                new Alert(Alert.AlertType.ERROR,"LogIn Failed");
//            }
//        }else if (email.equals("Employee") && password.equals("Employee")) {
//            try {
//                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/employeeManagementForm.fxml"))));
//                stage.setTitle("Clothify Shop Management System | Employee DashBoard");
//                stage.setHeight(720);
//                stage.setWidth(1200);
//                stage.setResizable(false);
//                new Alert(Alert.AlertType.INFORMATION, "Log In Successfully").showAndWait();
//                btnLogin.getScene().getWindow().hide();
//                stage.show();
//            } catch (IOException e) {
//                new Alert(Alert.AlertType.ERROR, "Login Failed");
//            }
//        }
//    }

    public void btnEmployeeSignUpOnAction(MouseEvent mouseEvent) {
        currentPage.setVisible(false);
        currentPage = pageSignUp;
        currentPage.setVisible(true);
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

    final UserService userService = ServiceFactory.getInstance().getServiceType(ServiceType.USER);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentPage = pageEmployeeLogIn;
        currentPage.setVisible(true);

    }

    public void btnAdminLoginOnAction(ActionEvent event) {
        String email = txtAdminEmail.getText();
        String password = txtAdminPassword.getText();


        User user = userService.loginUser(email, password);

        if (user != null) {
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

    public void btnAdminForgetPwOnAction(MouseEvent mouseEvent) {

    }

    public void btnAdminSignUpOnAction(MouseEvent mouseEvent) {
        currentPage.setVisible(false);
        currentPage = pageSignUp;
        currentPage.setVisible(true);
    }

    public void btnRegisterOnAction(ActionEvent event) {
        String firstName = txtSignUpFirstName.getText();
        String lastName = txtSignUpLastName.getText();
        String emailAddress = txtSignUpEmail.getText();
        String password = txtSignUpPassword.getText();
        String confirmPassword = txtSignUpConfirmPassword.getText();

        if (!password.equals(confirmPassword)) {
            new Alert(Alert.AlertType.INFORMATION, "Your passwords do not match, please re-enter.").show();
        } else {
            if (userService.registerUser(firstName, lastName, emailAddress, password, confirmPassword)) {
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


        User user = userService.loginUser(email, password);

        if (user != null) {
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
            new Alert(Alert.AlertType.WARNING, "Invalid User Name Or Password.").show();
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

        User user = userService.findUserByEmail(email);
        if (user == null) {
            new Alert(Alert.AlertType.WARNING, "No user found with this email address.").show();
            return;
        }

        String otp = OTPUtil.generateOTP();
        userService.storeOTP(email, otp);
        System.out.println("Generated OTP: " + otp);

        boolean emailSent = userService.sendOTPEmail(email, otp);
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

        if (userService.validateOTP(emailAddress, otp)) {
            userService.resetPassword(emailAddress, newPassword);
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