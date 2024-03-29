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
import java.util.Random;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    RatingService ratingService;

    public RegisterResponse register(User user) {

        RegisterResponse registerResponse = new RegisterResponse();

        User DBuser = userRepository.findByEmail(user.getEmail());

        if (DBuser != null) {
            registerResponse.setMessage("user exists");
            registerResponse.setUser(DBuser);

        } else {

            Random random = new Random();
            int randomNum = random.nextInt(99 - 1 + 1) + 1;

            String[] cities = {"London", "Manchester", "Leeds", "Liverpool", "Bristol", "Birmingham", "Cardiff", "Belfast"};
            int randomCity = random.nextInt(7 - 1 + 1) + 1;

            user.setCity(cities[randomCity]);

            user.setProfileImageUrl("https://randomuser.me/api/portraits/men/" + randomNum + ".jpg");
//            userRepository.save(user);
            user.setTimeCreditsCount(3.0);
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
                DBuser.setLoginCount(DBuser.getLoginCount()+ 1);
                userRepository.save(DBuser);
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


    public boolean checkBalance(int studentId, double lessonPrice) {

        boolean hasEnoughBalance = false;

        User student = userRepository.findById(studentId).get();
        if (student.getTimeCreditsCount() >= lessonPrice) {
            hasEnoughBalance = true;
        }

        return hasEnoughBalance;

    }
//    private void changePhotos() {
//        List<User> users = userRepository.getAll();
//
//        int i = 1;
//        for(User user : users){
//            user.setProfileImageUrl("https://randomuser.me/api/portraits/men/" + i + ".jpg");
//            userRepository.save(user);
//            i++;
//        }
//    }


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

//    public void rateUser(int userId, int rating, String comments) {
//
////      add rating
//
//        Rating newRating = new Rating(rating, userId, comments);
//        ratingRepository.save(newRating);
//
//
////        updating user rating
//        List<Rating> userRatings = ratingRepository.findAllByUserId(userId);
//
//        User user = userRepository.findById(userId).get();
//
//        double updatedRating;
//
//        updatedRating = calculateAverageRating(userRatings);
//
//        user.setRatingScore(updatedRating);
//        user.setRatingCount(userRatings.size());
//
//        userRepository.save(user);
//
//    }
//
    private double calculateAverageRating(List<Rating> ratings) {

        int totalStars = 0;

        for (Rating rating : ratings) {

            totalStars += rating.getRating();
        }

        return totalStars / ratings.size();
    }

    public User refreshUserDetails(int userId) {
        ratingService.updateUserRatings(userId);
        return userRepository.findById(userId).get();
    }
}


