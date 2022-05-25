package uz.pdpd.app6atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdpd.app6atm.entity.Card;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {

    boolean existsByNumber(String number);

    boolean existsByNumberAndIdNot(String number, UUID id);

    Optional<Card> findCardByNumber(String number);

}
