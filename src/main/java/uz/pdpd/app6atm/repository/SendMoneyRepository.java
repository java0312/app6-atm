package uz.pdpd.app6atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdpd.app6atm.entity.SendMoney;

import java.util.List;
import java.util.UUID;

@Repository
public interface SendMoneyRepository extends JpaRepository<SendMoney, UUID> {

    List<SendMoney> findAllByFromCardId(UUID fromCard_id);
}
