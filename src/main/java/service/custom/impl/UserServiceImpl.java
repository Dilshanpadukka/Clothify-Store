package service.custom.impl;

import entity.UserEntity;
import model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.impl.UserDaoImpl;
import service.custom.UserService;
import util.DaoType;
import util.EmailUtil;
import util.OTPUtil;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private final Map<String, String> otpStorage = new HashMap<>();

    @Override
    public boolean registerUser(String firstName, String lastName, String emailAddress, String password,String confirmPassword) {
        if (isValidPassword(password) && isUniqueEmail(emailAddress)) {

            String hashedPassword = hashPassword(password);


            UserEntity userEntity = new UserEntity();
            userEntity.setFirstName(firstName);
            userEntity.setLastName(lastName);
            userEntity.setEmailAddress(emailAddress);
            userEntity.setPassword(hashedPassword);

            UserDaoImpl userDao = DaoFactory.getInstance().getDaoType(DaoType.USER);
            return userDao.save(userEntity);
        }
        return false;

    }

    @Override
    public User loginUser(String email, String password) {
        UserDaoImpl userDao = DaoFactory.getInstance().getDaoType(DaoType.USER);
        User user = userDao.getUserByEmail(email);
        System.out.println(user);

        if (user != null) {
            String storedPassword = user.getPassword();

            if (storedPassword == null) {
                System.out.println("Password for user is not set: " + email);
                return null;
            }

            boolean isPasswordValid = authenticate(password, storedPassword);
            if (isPasswordValid) {
                return user;
            } else {
                System.out.println("Invalid password for user: " + email);
            }
        } else {
            System.out.println("No user found with email: " + email);
        }

        return null;
    }



    @Override
    public User findUserByEmail(String email) {
        UserDaoImpl userDao = DaoFactory.getInstance().getDaoType(DaoType.USER);
        return userDao.getUserByEmail(email);
    }

    @Override
    public boolean sendOTPEmail(String email, String otp) {
        return EmailUtil.sendOTPEmail(email, otp);
    }

    @Override
    public void storeOTP(String email, String otp) {
        otpStorage.put(email, otp);
        System.out.println("Stored OTP: " + otp + " for email: " + email);
    }


    @Override
    public boolean validateOTP(String email, String otp) {
        String storedOtp = otpStorage.get(email);
        System.out.println("Validating OTP for email: " + email);
        System.out.println("Entered OTP: " + otp);
        System.out.println("Stored OTP: " + storedOtp);
        return storedOtp != null && storedOtp.equals(otp);
    }



    @Override
    public void resetPassword(String email, String newPassword) {
        UserDaoImpl userDao = DaoFactory.getInstance().getDaoType(DaoType.USER);
        User user = userDao.getUserByEmail(email);
        if (user != null) {
            String hashedPassword = OTPUtil.hashPassword(newPassword);
            user.setPassword(hashedPassword);
            UserEntity user1 = new ModelMapper().map(user, UserEntity.class);
            userDao.update(user1);
        }
    }


    private boolean isValidPassword(String password) {
        return password.length() >= 8;
    }

    private boolean isUniqueEmail(String email) {
        UserDaoImpl userDao = DaoFactory.getInstance().getDaoType(DaoType.USER);
        return userDao.getUserByEmail(email) == null;
    }

    public String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }


    public boolean checkPassword(String plainPassword, String hashedPassword) {
        System.out.println("Plain Password: " + plainPassword);
        System.out.println("Hashed Password: " + hashedPassword);
        if (plainPassword == null || hashedPassword == null) {
            throw new IllegalArgumentException("Passwords cannot be null");
        }
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }


    private boolean authenticate(String password, String storedPassword) {
        if (storedPassword == null) {
            System.out.println("Stored password is null");
            return false;
        }

        return checkPassword(password, storedPassword);
    }
}
