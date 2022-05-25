package uz.pdpd.app6atm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdpd.app6atm.entity.Atm;
import uz.pdpd.app6atm.entity.Card;
import uz.pdpd.app6atm.entity.MoneyAmount;
import uz.pdpd.app6atm.entity.TakeMoney;
import uz.pdpd.app6atm.my.KnowRole;
import uz.pdpd.app6atm.payload.ApiResponse;
import uz.pdpd.app6atm.payload.TakeMoneyDto;
import uz.pdpd.app6atm.repository.AtmRepository;
import uz.pdpd.app6atm.repository.CardRepository;
import uz.pdpd.app6atm.repository.MoneyAmountRepository;
import uz.pdpd.app6atm.repository.TakeMoneyRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TakeMoneyService {

    @Autowired
    TakeMoneyRepository takeMoneyRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    AtmRepository atmRepository;

    @Autowired
    MoneyAmountRepository moneyAmountRepository;


    public ApiResponse addTakeMoney(TakeMoneyDto takeMoneyDto) {

        Optional<Atm> optionalAtm = atmRepository.findById(takeMoneyDto.getAtmId());
        if (optionalAtm.isEmpty())
            return new ApiResponse("Atm not found!", false);

        Optional<Card> optionalCard = cardRepository.findCardByNumber(takeMoneyDto.getCardNumber());
        if (optionalCard.isEmpty())
            return new ApiResponse("Card not found!", false);


        Integer money = takeMoneyDto.getMoney();

        List<MoneyAmount> all = moneyAmountRepository.findAll();

        for (MoneyAmount moneyAmount : all) {
            if (moneyAmount.getAmount() > money){
                TakeMoney takeMoney = new TakeMoney();
                takeMoney.setMoneyAmount(moneyAmount);
                takeMoney.setFromCard(optionalCard.get());
                takeMoney.setAtm(optionalAtm.get());
                takeMoney.setCount(money / moneyAmount.getAmount());
                takeMoneyRepository.save(takeMoney);

                money = money % moneyAmount.getAmount();
                Card one = cardRepository.getOne(optionalCard.get().getId());
                one.setBalance(one.getBalance() - takeMoney.getCount() * takeMoney.getMoneyAmount().getAmount());
                cardRepository.save(one);
            }
        }

        return new ApiResponse("Take your money!", true);
    }

    public List<TakeMoney> getTakeMoneyByCardId(UUID cardId) {
        return takeMoneyRepository.findAllByFromCardId(cardId);
    }

    public List<TakeMoney> getAllTakeMoney() {
        return takeMoneyRepository.findAll();
    }

    public ApiResponse deleteTakeMoney(UUID id) {
        try {
            takeMoneyRepository.deleteById(id);
            return new ApiResponse("Take Money deleted!", true);
        }catch (Exception e){
            return new ApiResponse("Take money not found!", false);
        }
    }
}
