package uz.pdpd.app6atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdpd.app6atm.entity.Currency;

import java.util.UUID;

public interface CurrencyRepository extends JpaRepository<Currency, UUID> {

}
