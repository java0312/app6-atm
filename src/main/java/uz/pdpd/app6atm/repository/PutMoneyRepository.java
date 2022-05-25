package uz.pdpd.app6atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdpd.app6atm.entity.PutMoney;

import java.util.UUID;

public interface PutMoneyRepository extends JpaRepository<PutMoney, UUID> {

}
