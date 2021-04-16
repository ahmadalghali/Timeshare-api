package uk.ac.greenwich.aa5119a.demotimebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.greenwich.aa5119a.demotimebank.model.Rating;
import uk.ac.greenwich.aa5119a.demotimebank.model.User;
import uk.ac.greenwich.aa5119a.demotimebank.repository.RatingRepository;
import uk.ac.greenwich.aa5119a.demotimebank.repository.UserRepository;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    UserRepository userRepository;

    public Boolean rateUser(int userId, float rating, String comments) {
        try {
            ratingRepository.save(new Rating((int) rating, userId, comments));

            updateUserRatings(userId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    public void updateAllUserRatings(){
//        List<User> users = userRepository.getAll();
//
//        for(User user : users){
//            updateUserRatings(user.getId());
//        }
//
//    }








    private double calculateAverageRating(List<Rating> ratings) {

        int totalStars = 0;



        for (Rating rating : ratings) {

            totalStars += rating.getRating();
        }

        return totalStars / ratings.size();
    }



    public void updateUserRatings(int userId) {


//        updating user rating
        List<Rating> userRatings = ratingRepository.findAllByUserId(userId);

        User user = userRepository.findById(userId).get();

        if(userRatings.size() == 0){
            return;
        }

        double userRatingAverage = calculateAverageRating(userRatings);

        user.setRatingScore(userRatingAverage);
        user.setRatingCount(userRatings.size());

        userRepository.save(user);
//        User user = userRepository.findById(userId).get();
//
//        Integer userRatingCount = ratingRepository.getUserRatingCount(user.getId());
//        Integer userRatingsSum = ratingRepository.getUserRatingSum(user.getId());
//
//        double userRatingAverage = (double) (userRatingsSum  / userRatingCount;
//
//        user.setRatingCount(userRatingCount);
//        user.setRatingScore(userRatingAverage);
//        userRepository.save(user);
    }


}
