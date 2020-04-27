package rc.bootsecurity.db;

import org.springframework.data.jpa.repository.JpaRepository;

import rc.bootsecurity.model.PreviousOrder;

public interface PreviousOrderRepo extends JpaRepository<PreviousOrder,Integer> {

}
