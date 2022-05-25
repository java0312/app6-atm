package uz.pdpd.app6atm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdpd.app6atm.entity.Address;
import uz.pdpd.app6atm.entity.Atm;
import uz.pdpd.app6atm.entity.CardType;
import uz.pdpd.app6atm.payload.ApiResponse;
import uz.pdpd.app6atm.payload.AtmDto;
import uz.pdpd.app6atm.repository.AtmRepository;
import uz.pdpd.app6atm.repository.CardTypeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AtmService {

    @Autowired
    AtmRepository atmRepository;

    @Autowired
    CardTypeRepository cardTypeRepository;

    //create
    public ApiResponse createAtm(AtmDto atmDto) {

        boolean exists = atmRepository.existsByNumber(atmDto.getNumber());
        if (exists)
            return new ApiResponse("Atm already exists!", false);

        Optional<CardType> optionalCardType = cardTypeRepository.findById(atmDto.getCardTypeId());
        if (optionalCardType.isEmpty())
            return new ApiResponse("Card type not found!", false);

        Atm atm = new Atm();
        atm.setNumber(atmDto.getNumber());
        atm.setCardType(optionalCardType.get());
        atm.setCommissionInRemovable(atmDto.getCommissionInRemovable());
        atm.setCommissionInSend(atmDto.getCommissionInSend());
        atm.setMinDollarForWaring(atmDto.getMinDollarForWaring());
        atm.setMinSumForWaring(atmDto.getMinSumForWaring());
        atm.setRemovableMaxDollar(atmDto.getRemovableMaxDollar());
        atm.setRemovableMaxSum(atmDto.getRemovableMaxSum());

        Address address = new Address();
        address.setCity(atmDto.getCity());
        address.setStreet(atmDto.getStreet());

        atm.setAddress(address);
        atmRepository.save(atm);
        return new ApiResponse("Atm saved!", true);
    }

    //get all
    public List<Atm> getAllAtms() {
        return atmRepository.findAll();
    }

    //get one
    public Atm getAtm(UUID id) {
        Optional<Atm> optionalAtm = atmRepository.findById(id);
        return optionalAtm.orElse(null);
    }

    //edit
    public ApiResponse editAtm(UUID id, AtmDto atmDto) {

        Optional<Atm> optionalAtm = atmRepository.findById(id);
        if (optionalAtm.isEmpty())
            return new ApiResponse("Atm not found!", false);

        boolean exists = atmRepository.existsByNumberAndIdNot(atmDto.getNumber(), id);
        if (exists)
            return new ApiResponse("Atm already exists!", false);

        Optional<CardType> optionalCardType = cardTypeRepository.findById(atmDto.getCardTypeId());
        if (optionalCardType.isEmpty())
            return new ApiResponse("Card type not found!", false);

        Atm atm = optionalAtm.get();
        atm.setNumber(atmDto.getNumber());
        atm.setCardType(optionalCardType.get());
        atm.setCommissionInRemovable(atmDto.getCommissionInRemovable());
        atm.setCommissionInSend(atmDto.getCommissionInSend());
        atm.setMinDollarForWaring(atmDto.getMinDollarForWaring());
        atm.setMinSumForWaring(atmDto.getMinSumForWaring());
        atm.setRemovableMaxDollar(atmDto.getRemovableMaxDollar());
        atm.setRemovableMaxSum(atmDto.getRemovableMaxSum());

        Address address = new Address();
        address.setCity(atmDto.getCity());
        address.setStreet(atmDto.getStreet());

        atm.setAddress(address);
        atmRepository.save(atm);
        return new ApiResponse("Atm edited!", true);

    }

    //delete
    public ApiResponse deleteAtm(UUID id) {
        boolean exists = atmRepository.existsById(id);
        if (!exists)
            return new ApiResponse("Atm not found!", false);
        atmRepository.deleteById(id);
        return new ApiResponse("Atm deleted!", true);
    }
}
