package uk.ac.greenwich.aa5119a.demotimebank.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;
import uk.ac.greenwich.aa5119a.demotimebank.model.User;
import uk.ac.greenwich.aa5119a.demotimebank.repository.UserRepository;
import uk.ac.greenwich.aa5119a.demotimebank.web.LoginResponse;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String register(User user) {

        User DBuser = userRepository.findByEmail(user.getEmail());

        if (DBuser != null) {
            return "user exists";
        } else {

            userRepository.save(user);

            return "registered";
        }
    }

    public LoginResponse login(User user) {

        User DBuser = userRepository.findByEmail(user.getEmail());

        LoginResponse loginResponse = new LoginResponse();

        if (DBuser != null) {
            if(DBuser.getPassword().equals(user.getPassword())){
                loginResponse.setUser(user);
                loginResponse.setMessage("logged in");
            }
            loginResponse.setUser(user);
            loginResponse.setMessage("incorrect password");
        } else{
            loginResponse.setUser(user);
            loginResponse.setMessage("user does not exist");
        }

        return loginResponse;
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
