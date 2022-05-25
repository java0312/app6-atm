package uz.pdpd.app6atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdpd.app6atm.entity.SendMoney;
import uz.pdpd.app6atm.payload.ApiResponse;
import uz.pdpd.app6atm.payload.SendMoneyDto;
import uz.pdpd.app6atm.service.SendMoneyService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sendMoney")
public class SendMoneyController {

    @Autowired
    SendMoneyService sendMoneyService;

    @PostMapping
    public HttpEntity<?> sendMoney(@RequestBody SendMoneyDto sendMoneyDto){
        ApiResponse apiResponse = sendMoneyService.sendMoney(sendMoneyDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAllSendMoney(){
        List<SendMoney> list = sendMoneyService.getAllSendMoney();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/byCardId/{cardId}")
    public HttpEntity<?> getAllSendMoneyByCardId(@PathVariable UUID cardId){
        List<SendMoney> list = sendMoneyService.getAllSendMoneyByCardId(cardId);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/{id}")
    public HttpEntity<?> editSendMoney(@PathVariable UUID id, @RequestBody SendMoneyDto sendMoneyDto){
        ApiResponse apiResponse = sendMoneyService.editSendMoney(id, sendMoneyDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteSendMoney(@PathVariable UUID id){
        ApiResponse apiResponse = sendMoneyService.deleteSendMoney(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

}
















/*
* kinetik energiyalarining yig'indisi
* mexanik sistema kinetik energiyasini ifodalaydi ...
* qattiq jism kinetik energiyasini ....
* Ilgarilanma harakatda qattiq jism kinetik energiyasi ...
* sistema ...
* ko'paytmasining yarmiga teng ekan.
* Qo'zg'almas o'q atrofida aylanuvchi qattiq jismning kinetk energiyasi aylanish o'qiga nisbatan ...
* */
























