package uz.pdpd.app6atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdpd.app6atm.entity.PutMoney;
import uz.pdpd.app6atm.payload.ApiResponse;
import uz.pdpd.app6atm.payload.PutMoneyDto;
import uz.pdpd.app6atm.service.PutMoneyService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/putMoney")
public class PutMoneyController {

    @Autowired
    PutMoneyService putMoneyService;

    @PostMapping
    public HttpEntity<?> putMoney(@RequestBody PutMoneyDto putMoneyDto) {
        ApiResponse apiResponse = putMoneyService.putMoney(putMoneyDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping()
    public HttpEntity<?> getAllPutMoney(){
        List<PutMoney> list = putMoneyService.getAllPutMoney();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getPutMoney(@PathVariable UUID id){
        PutMoney putMoney = putMoneyService.getPutMoney(id);
        return ResponseEntity.ok(putMoney);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editPutMoney(@PathVariable UUID id, @RequestBody PutMoneyDto putMoneyDto){
        ApiResponse apiResponse = putMoneyService.editPutMoney(id, putMoneyDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deletePutMoney(@PathVariable UUID id){
        ApiResponse apiResponse = putMoneyService.deletePutMoney(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

}
