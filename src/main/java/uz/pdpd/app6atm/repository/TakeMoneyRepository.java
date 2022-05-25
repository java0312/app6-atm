package uz.pdpd.app6atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdpd.app6atm.entity.TakeMoney;

import java.util.List;
import java.util.UUID;

public interface TakeMoneyRepository extends JpaRepository<TakeMoney, UUID> {

    List<TakeMoney> findAllByFromCardId(UUID fromCard_id);
}
