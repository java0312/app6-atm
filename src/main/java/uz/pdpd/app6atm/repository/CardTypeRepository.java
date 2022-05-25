package uz.pdpd.app6atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdpd.app6atm.entity.Card;
import uz.pdpd.app6atm.entity.CardType;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardTypeRepository extends JpaRepository<CardType, UUID> {



}
