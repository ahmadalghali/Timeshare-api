package uk.ac.greenwich.aa5119a.demotimebank.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.jvm.hotspot.asm.Register;
import sun.rmi.runtime.Log;
import uk.ac.greenwich.aa5119a.demotimebank.model.User;
import uk.ac.greenwich.aa5119a.demotimebank.repository.UserRepository;
import uk.ac.greenwich.aa5119a.demotimebank.web.LoginResponse;
import uk.ac.greenwich.aa5119a.demotimebank.web.RegisterResponse;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public RegisterResponse register(User user) {

        RegisterResponse registerResponse = new RegisterResponse();

        User DBuser = userRepository.findByEmail(user.getEmail());

        if (DBuser != null) {
            registerResponse.setMessage("user exists");

        } else {

            userRepository.save(user);

            registerResponse.setMessage("registered");
        }
        registerResponse.setUser(user);

        return registerResponse;
    }



    public LoginResponse login(User user) {

        User DBuser = userRepository.findByEmail(user.getEmail());

        LoginResponse loginResponse = new LoginResponse();

        if (DBuser != null) {

            if(DBuser.getPassword().equals(user.getPassword())){
                loginResponse.setUser(user);
                loginResponse.setMessage("logged in");

                return loginResponse;
            }

            loginResponse.setUser(user);
            loginResponse.setMessage("incorrect password");

            return loginResponse;

        } else{
            loginResponse.setUser(user);
            loginResponse.setMessage("user does not exist");
            return loginResponse;
        }

    }


    public User logout(User user) {

        User DBuser = userRepository.findByEmail(user.getEmail());

        if (DBuser != null) {
//            DBuser.setLoggedIn(false);

            return userRepository.save(DBuser);
        }
        return null;
    }

    public User changePassword(User user) {
        User DBuser = userRepository.findByEmail(user.getEmail());

        DBuser.setPassword("changedPassword");
        userRepository.save(DBuser);
        return DBuser;
    }
}
