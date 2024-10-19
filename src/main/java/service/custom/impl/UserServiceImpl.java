package service.custom.impl;

import model.User;
import service.custom.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public boolean registerUser(String firstName, String lastName, String emailAddress, String password, String confirmPassword) {
        return false;
    }

    @Override
    public User loginUser(String email, String password) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public boolean sendOTPEmail(String email, String otp) {
        return false;
    }

    @Override
    public void storeOTP(String email, String otp) {

    }

    @Override
    public boolean validateOTP(String email, String otp) {
        return false;
    }

    @Override
    public void resetPassword(String email, String newPassword) {

    }
}
