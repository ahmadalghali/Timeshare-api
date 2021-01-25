package uk.ac.greenwich.aa5119a.demotimebank.repository.notification;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.ac.greenwich.aa5119a.demotimebank.model.notification.Notification;

import java.util.List;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer> {

    List<Notification> findAllByReceiverIdOrderByIdDesc(int receiverId);
}
