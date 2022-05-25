package uz.pdpd.app6atm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdpd.app6atm.entity.Atm;
import uz.pdpd.app6atm.entity.Card;
import uz.pdpd.app6atm.entity.SendMoney;
import uz.pdpd.app6atm.payload.ApiResponse;
import uz.pdpd.app6atm.payload.SendMoneyDto;
import uz.pdpd.app6atm.repository.AtmRepository;
import uz.pdpd.app6atm.repository.CardRepository;
import uz.pdpd.app6atm.repository.SendMoneyRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SendMoneyService {

    @Autowired
    SendMoneyRepository sendMOneyRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    AtmRepository atmRepository;

    public ApiResponse sendMoney(SendMoneyDto sendMoneyDto) {
        Optional<Card> optionalFromCard = cardRepository.findById(sendMoneyDto.getFromCardId());
        if (optionalFromCard.isEmpty())
            return new ApiResponse("From card not found!", false);

        Optional<Card> optionalToCard = cardRepository.findById(sendMoneyDto.getToCardId());
        if (optionalToCard.isEmpty())
            return new ApiResponse("To Card not found!", false);

        Optional<Atm> optionalAtm = atmRepository.findById(sendMoneyDto.getAtmId());
        if (optionalAtm.isEmpty())
            return new ApiResponse("Atm not found!", false);

        if (optionalFromCard.get().getBalance() < sendMoneyDto.getAmount())
            return new ApiResponse("Not enough money!", false);

        SendMoney sendMoney = new SendMoney();
        sendMoney.setAmount(sendMoneyDto.getAmount());
        sendMoney.setFromCard(optionalFromCard.get());
        sendMoney.setToCard(optionalToCard.get());
        sendMoney.setAtm(optionalAtm.get());
        sendMOneyRepository.save(sendMoney);

        Card toCard = optionalToCard.get();
        Card fromCard = optionalFromCard.get();

        toCard.setBalance(toCard.getBalance() + sendMoney.getAmount());
        fromCard.setBalance(fromCard.getBalance() - sendMoney.getAmount());

        cardRepository.save(toCard);
        cardRepository.save(fromCard);

        return new ApiResponse("Money sent!", true);
    }

    public List<SendMoney> getAllSendMoney() {
        return sendMOneyRepository.findAll();
    }

    public List<SendMoney> getAllSendMoneyByCardId(UUID cardId) {
        return sendMOneyRepository.findAllByFromCardId(cardId);
    }

    public ApiResponse editSendMoney(UUID id, SendMoneyDto sendMoneyDto) {

        Optional<SendMoney> optionalSendMoney = sendMOneyRepository.findById(id);
        if (optionalSendMoney.isEmpty())
            return new ApiResponse("", false);

        Optional<Card> optionalFromCard = cardRepository.findById(sendMoneyDto.getFromCardId());
        if (optionalFromCard.isEmpty())
            return new ApiResponse("From card not found!", false);

        Optional<Card> optionalToCard = cardRepository.findById(sendMoneyDto.getToCardId());
        if (optionalToCard.isEmpty())
            return new ApiResponse("To Card not found!", false);

        Optional<Atm> optionalAtm = atmRepository.findById(sendMoneyDto.getAtmId());
        if (optionalAtm.isEmpty())
            return new ApiResponse("Atm not found!", false);

        if (optionalFromCard.get().getBalance() < sendMoneyDto.getAmount())
            return new ApiResponse("Not enough money!", false);

        SendMoney sendMoney = optionalSendMoney.get();
        sendMoney.setAmount(sendMoneyDto.getAmount());
        sendMoney.setFromCard(optionalFromCard.get());
        sendMoney.setToCard(optionalToCard.get());
        sendMoney.setAtm(optionalAtm.get());
        sendMOneyRepository.save(sendMoney);

        Card toCard = optionalToCard.get();
        Card fromCard = optionalFromCard.get();

        toCard.setBalance(toCard.getBalance() + sendMoney.getAmount());
        fromCard.setBalance(fromCard.getBalance() - sendMoney.getAmount());

        cardRepository.save(toCard);
        cardRepository.save(fromCard);

        return new ApiResponse("SendMoney edited!", true);
    }


    public ApiResponse deleteSendMoney(UUID id) {
        Optional<SendMoney> optionalSendMoney = sendMOneyRepository.findById(id);
        if (optionalSendMoney.isEmpty())
            return new ApiResponse("SendMoney not found!", false);

        sendMOneyRepository.deleteById(id);
        return new ApiResponse("SendMoney deleted!", true);
    }
}






