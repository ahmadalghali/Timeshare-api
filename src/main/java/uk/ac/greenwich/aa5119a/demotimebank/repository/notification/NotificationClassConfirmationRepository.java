package uk.ac.greenwich.aa5119a.demotimebank.repository.notification;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.ac.greenwich.aa5119a.demotimebank.model.notification.NotificationClassConfirmation;

import java.util.List;

//@Repository
public interface NotificationClassConfirmationRepository extends CrudRepository<NotificationClassConfirmation, Integer> {

//    List<NotificationClassConfirmation> findAllByReceiverIdOrderByIdDesc(int userId);
}
