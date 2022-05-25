package uz.pdpd.app6atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdpd.app6atm.entity.TakeMoney;
import uz.pdpd.app6atm.payload.ApiResponse;
import uz.pdpd.app6atm.payload.TakeMoneyDto;
import uz.pdpd.app6atm.service.TakeMoneyService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/takeMoney")
public class TakeMoneyController {

    @Autowired
    TakeMoneyService takeMoneyService;


    @Transactional
    @PostMapping
    public HttpEntity<?> addTakeMoney(@RequestBody TakeMoneyDto takeMoneyDto){
        ApiResponse apiResponse = takeMoneyService.addTakeMoney(takeMoneyDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }


    @GetMapping("/byCardId/{cardId}")
    public HttpEntity<?> getTakeMoneyByCardId(@PathVariable UUID cardId){
        List<TakeMoney> takeMoney = takeMoneyService.getTakeMoneyByCardId(cardId);
        return ResponseEntity.ok(takeMoney);
    }

    @GetMapping
    public HttpEntity<?> getAllTakeMoney(){
        List<TakeMoney> takeMoney = takeMoneyService.getAllTakeMoney();
        return ResponseEntity.ok(takeMoney);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteTakeMoney(@PathVariable UUID id){
        ApiResponse apiResponse = takeMoneyService.deleteTakeMoney(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

}
