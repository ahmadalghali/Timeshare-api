package uk.ac.greenwich.aa5119a.demotimebank.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.greenwich.aa5119a.demotimebank.model.Rating;
import uk.ac.greenwich.aa5119a.demotimebank.model.User;
import uk.ac.greenwich.aa5119a.demotimebank.model.response.LoginResponse;
import uk.ac.greenwich.aa5119a.demotimebank.model.response.RegisterResponse;
import uk.ac.greenwich.aa5119a.demotimebank.repository.RatingRepository;
import uk.ac.greenwich.aa5119a.demotimebank.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RatingRepository ratingRepository;

    public RegisterResponse register(User user) {

        RegisterResponse registerResponse = new RegisterResponse();

        User DBuser = userRepository.findByEmail(user.getEmail());

        if (DBuser != null) {
            registerResponse.setMessage("user exists");
            registerResponse.setUser(DBuser);

        } else {

//            userRepository.save(user);
            registerResponse.setUser(userRepository.save(user));
            registerResponse.setMessage("registered");

        }

        return registerResponse;
    }


    public LoginResponse login(User user) {

        User DBuser = userRepository.findByEmail(user.getEmail());

        LoginResponse loginResponse = new LoginResponse();

        if (DBuser != null) {

            if (DBuser.getPassword().equals(user.getPassword())) {
                loginResponse.setUser(DBuser);
                loginResponse.setMessage("logged in");

                return loginResponse;
            }

            loginResponse.setUser(DBuser);
            loginResponse.setMessage("incorrect password");

            return loginResponse;

        } else {
            user.setId(-1);
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

    public void rateUser(int userId, int rating) {

//      add rating

        Rating newRating = new Rating(rating, userId);
        ratingRepository.save(newRating);


//        updating user rating
        List<Rating> userRatings = ratingRepository.findAllByUserId(userId);

        User user = userRepository.findById(userId).get();

        double updatedRating;

        updatedRating = calculateAverageRating(userRatings);

        user.setRatingScore(updatedRating);
        user.setRatingCount(userRatings.size());

        userRepository.save(user);

    }

    private double calculateAverageRating(List<Rating> ratings) {

        int totalStars = 0;

        for (Rating rating : ratings) {

            totalStars += rating.getRating();
        }

        return totalStars / ratings.size();
    }
}


