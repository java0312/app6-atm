package uz.pdpd.app6atm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdpd.app6atm.entity.Atm;

import java.util.UUID;

public interface AtmRepository extends JpaRepository<Atm, UUID> {

    boolean existsByNumber(String number);

    boolean existsByNumberAndIdNot(String number, UUID id);

}
