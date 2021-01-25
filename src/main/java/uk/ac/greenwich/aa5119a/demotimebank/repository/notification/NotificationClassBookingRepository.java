package uk.ac.greenwich.aa5119a.demotimebank.repository.notification;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.ac.greenwich.aa5119a.demotimebank.model.notification.NotificationClassBooking;

import java.util.List;

@Repository
public interface NotificationClassBookingRepository extends CrudRepository<NotificationClassBooking, Integer> {

    List<NotificationClassBooking> findAllByReceiverIdOrderByIdDesc(int receiverId);
}
