package uz.pdpd.app6atm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdpd.app6atm.entity.Currency;
import uz.pdpd.app6atm.entity.MoneyAmount;
import uz.pdpd.app6atm.payload.ApiResponse;
import uz.pdpd.app6atm.payload.MoneyAmountDto;
import uz.pdpd.app6atm.repository.CurrencyRepository;
import uz.pdpd.app6atm.repository.MoneyAmountRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MoneyAmountService {

    @Autowired
    MoneyAmountRepository moneyAmountRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    //__CREATE
    public ApiResponse createMoneyAmount(MoneyAmountDto moneyAmountDto) {
        //_if currency doesn't exist then return message about this
        Optional<Currency> optionalCurrency = currencyRepository.findById(moneyAmountDto.getCurrencyId());
        if (optionalCurrency.isEmpty())
            return new ApiResponse("Currency not found!", false);

        //_if moneyAmount is exists then return message about this
        boolean exists = moneyAmountRepository.existsByAmountAndCurrencyId(moneyAmountDto.getAmount(), moneyAmountDto.getCurrencyId());
        if (exists)
            return new ApiResponse("moneyAmount is exists", false);

        //_create moneyAmount
        MoneyAmount moneyAmount = new MoneyAmount();
        moneyAmount.setAmount(moneyAmountDto.getAmount());
        moneyAmount.setCurrency(optionalCurrency.get());
        moneyAmountRepository.save(moneyAmount);
        return new ApiResponse("MoneyAmount saved!", true);
    }

    //__READ ALL
    public List<MoneyAmount> getAll() {
        return moneyAmountRepository.findAll();
    }

    //__EDIT__
    public ApiResponse edit(UUID id, MoneyAmountDto moneyAmountDto) {
        Optional<MoneyAmount> optionalMoneyAmount = moneyAmountRepository.findById(id);
        if (optionalMoneyAmount.isEmpty())
            return new ApiResponse("Money amount not found!", false);

        //_if currency doesn't exist then return message about this
        Optional<Currency> optionalCurrency = currencyRepository.findById(moneyAmountDto.getCurrencyId());
        if (optionalCurrency.isEmpty())
            return new ApiResponse("Currency not found!", false);

        //_if moneyAmount is exists then return message about this
        boolean exists = moneyAmountRepository.existsByAmountAndCurrencyIdAndIdNot(moneyAmountDto.getAmount(), moneyAmountDto.getCurrencyId(), id);
        if (exists)
            return new ApiResponse("moneyAmount is exists", false);

        //_create moneyAmount
        MoneyAmount moneyAmount = optionalMoneyAmount.get();
        moneyAmount.setAmount(moneyAmountDto.getAmount());
        moneyAmount.setCurrency(optionalCurrency.get());
        moneyAmountRepository.save(moneyAmount);
        return new ApiResponse("MoneyAmount edited!", true);
    }

    //__DELETE__
    public ApiResponse delete(UUID id) {
        boolean exists = moneyAmountRepository.existsById(id);
        if (!exists)
            return new ApiResponse("Money Amount not found!", false);
        moneyAmountRepository.deleteById(id);
        return new ApiResponse("Money amount deleted!", true);
    }
}
