package uk.ac.greenwich.aa5119a.demotimebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.greenwich.aa5119a.demotimebank.model.Rating;
import uk.ac.greenwich.aa5119a.demotimebank.repository.RatingRepository;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    public Boolean rateUser(int userId, float rating, String comments) {
        try {
            ratingRepository.save(new Rating((int) rating, userId, comments));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
