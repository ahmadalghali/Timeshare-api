package uk.ac.greenwich.aa5119a.demotimebank.repository.notification;

import org.springframework.data.repository.CrudRepository;
import uk.ac.greenwich.aa5119a.demotimebank.model.notification.NotificationClassConfirmation;

import java.util.List;

public interface NotificationClassConfirmationRepository extends CrudRepository<NotificationClassConfirmation, Integer> {

    List<NotificationClassConfirmation> findAllByReceiverIdOrderByIdDesc(int receiverId);
}
