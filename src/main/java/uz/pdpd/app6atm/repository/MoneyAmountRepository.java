package uz.pdpd.app6atm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdpd.app6atm.entity.MoneyAmount;

import java.util.UUID;

public interface MoneyAmountRepository extends JpaRepository<MoneyAmount, UUID> {

    boolean existsByAmountAndCurrencyId(Integer amount, UUID currency_id);

    boolean existsByAmountAndCurrencyIdAndIdNot(Integer amount, UUID currency_id, UUID id);

}
