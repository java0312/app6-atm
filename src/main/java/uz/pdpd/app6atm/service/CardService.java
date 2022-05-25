package uz.pdpd.app6atm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdpd.app6atm.entity.Card;
import uz.pdpd.app6atm.entity.CardType;
import uz.pdpd.app6atm.payload.ApiResponse;
import uz.pdpd.app6atm.payload.CardDto;
import uz.pdpd.app6atm.repository.CardRepository;
import uz.pdpd.app6atm.repository.CardTypeRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CardTypeRepository cardTypeRepository;

    //__CREATE  CARD__ (by DIRECTOR only)
    public ApiResponse createCard(CardDto cardDto) {

        //_if card exists by number then return message about this
        boolean exists = cardRepository.existsByNumber(cardDto.getNumber());
        if (exists)
            return new ApiResponse("Card already exists!", false);

        //_if type of card doesn't find then return message about this
        Optional<CardType> optionalCardType = cardTypeRepository.findById(cardDto.getCardTypeId());
        if (optionalCardType.isEmpty())
            return new ApiResponse("Card type not found!", false);

        //_create object of new card
        Card card = new Card();
        card.setFirstName(cardDto.getFirstName());
        card.setLastName(cardDto.getLastName());
        card.setNumber(cardDto.getNumber());
        card.setCardType(optionalCardType.get());
        card.setCvvCode(cardDto.getCvvCode());
        card.setBank(cardDto.getBank());
        card.setPassword(cardDto.getPassword());
        card.setBalance(cardDto.getBalance());
        Date expireDate = new Date();
        expireDate.setYear(expireDate.getYear() + 5);
        card.setExpireDate(expireDate);

        //_save object of new card and return about this
        cardRepository.save(card);
        return new ApiResponse("Card successfully created!", true);
    }

    //__GET ALL CARDS__ (for DIRECTOR only)
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    //__GET ONE CARD__ (for DIRECTOR ...)
    public Card getCard(UUID id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        return optionalCard.orElse(null);
    }

    //__EDIT CARD__ (for DIRECTOR only)
    public ApiResponse editCard(UUID id, CardDto cardDto) {

        //_Does card exist by id?
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (optionalCard.isEmpty())
            return new ApiResponse("Card not found!", false);

        //_if card exists by number then return message about this
        boolean exists = cardRepository.existsByNumberAndIdNot(cardDto.getNumber(), id);
        if (exists)
            return new ApiResponse("Card already exists!", false);

        //_if type of card doesn't find then return message about this
        Optional<CardType> optionalCardType = cardTypeRepository.findById(cardDto.getCardTypeId());
        if (optionalCardType.isEmpty())
            return new ApiResponse("Card type not found!", false);

        //_edit card
        Card card = optionalCard.get();
        card.setFirstName(cardDto.getFirstName());
        card.setLastName(cardDto.getLastName());
        card.setNumber(cardDto.getNumber());
        card.setCardType(optionalCardType.get());
        card.setCvvCode(cardDto.getCvvCode());
        card.setBank(cardDto.getBank());
        card.setPassword(cardDto.getPassword());
        card.setBalance(cardDto.getBalance());

        Date expireDate = new Date();
        expireDate.setYear(expireDate.getYear() + cardDto.getExpiredYear());
        card.setExpireDate(expireDate);

        //_save object of new card and return about this
        cardRepository.save(card);
        return new ApiResponse("Card successfully edited!", true);
    }

    //__DELETE CARD__ (by DIRECTOR only)
    public ApiResponse deleteCard(UUID id) {
        boolean exists = cardRepository.existsById(id);
        if (!exists)
            return new ApiResponse("Card not found!", false);
        cardRepository.deleteById(id);
        return new ApiResponse("Card deleted!", true);
    }
}
