package uz.pdpd.app6atm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdpd.app6atm.entity.Atm;
import uz.pdpd.app6atm.entity.MoneyAmount;
import uz.pdpd.app6atm.entity.PutMoney;
import uz.pdpd.app6atm.entity.User;
import uz.pdpd.app6atm.my.KnowRole;
import uz.pdpd.app6atm.payload.ApiResponse;
import uz.pdpd.app6atm.payload.PutMoneyDto;
import uz.pdpd.app6atm.repository.AtmRepository;
import uz.pdpd.app6atm.repository.MoneyAmountRepository;
import uz.pdpd.app6atm.repository.PutMoneyRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PutMoneyService {

    @Autowired
    PutMoneyRepository putMoneyRepository;

    @Autowired
    KnowRole knowRole;

    @Autowired
    AtmRepository atmRepository;

    @Autowired
    MoneyAmountRepository moneyAmountRepository;

    public ApiResponse putMoney(PutMoneyDto putMoneyDto) {
        if (knowRole.isNobody())
            return new ApiResponse("you cannot put money", false);

        User authUser = knowRole.getAuthUser();

        Optional<Atm> optionalAtm = atmRepository.findById(putMoneyDto.getAtmId());
        if (optionalAtm.isEmpty())
            return new ApiResponse("Atm not found!", false);

        Optional<MoneyAmount> optionalMoneyAmount = moneyAmountRepository.findById(putMoneyDto.getMoneyAmountId());
        if (optionalMoneyAmount.isEmpty())
            return new ApiResponse("Money amount not found!", false);

        PutMoney putMoney = new PutMoney();
        putMoney.setUser(authUser);
        putMoney.setMoneyAmount(optionalMoneyAmount.get());
        putMoney.setAtm(optionalAtm.get());
        putMoney.setCount(putMoneyDto.getCount());
        putMoneyRepository.save(putMoney);


        Atm atm = optionalAtm.get();
        atm.setBalance(atm.getBalance() + putMoney.getCount() * putMoney.getMoneyAmount().getAmount());

        return new ApiResponse("Money put!", true);
    }

    public List<PutMoney> getAllPutMoney() {
        return putMoneyRepository.findAll();
    }

    public PutMoney getPutMoney(UUID id) {
        Optional<PutMoney> optionalPutMoney = putMoneyRepository.findById(id);
        return optionalPutMoney.orElse(null);
    }

    public ApiResponse editPutMoney(UUID id, PutMoneyDto putMoneyDto) {

        Optional<PutMoney> optionalPutMoney = putMoneyRepository.findById(id);
        if (optionalPutMoney.isEmpty())
            return new ApiResponse("Put money not found!", false);

        if (knowRole.isNobody())
            return new ApiResponse("you cannot put money", false);
        User authUser = knowRole.getAuthUser();

        Optional<Atm> optionalAtm = atmRepository.findById(putMoneyDto.getAtmId());
        if (optionalAtm.isEmpty())
            return new ApiResponse("Atm not found!", false);

        Optional<MoneyAmount> optionalMoneyAmount = moneyAmountRepository.findById(putMoneyDto.getMoneyAmountId());
        if (optionalMoneyAmount.isEmpty())
            return new ApiResponse("Money amount not found!", false);

        PutMoney putMoney = optionalPutMoney.get();
        putMoney.setUser(authUser);
        putMoney.setMoneyAmount(optionalMoneyAmount.get());
        putMoney.setAtm(optionalAtm.get());
        putMoney.setCount(putMoneyDto.getCount());
        putMoneyRepository.save(putMoney);


        Atm atm = optionalAtm.get();
        atm.setBalance(atm.getBalance() + putMoney.getCount() * putMoney.getMoneyAmount().getAmount());

        return new ApiResponse("putMoney edited!", true);
    }

    public ApiResponse deletePutMoney(UUID id) {
        try {
            putMoneyRepository.deleteById(id);
            return new ApiResponse("Put money deleted!", true);
        }catch (Exception e){
            return new ApiResponse("Put money not found!", false);
        }
    }
}
