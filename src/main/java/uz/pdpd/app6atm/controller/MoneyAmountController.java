package uz.pdpd.app6atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdpd.app6atm.entity.MoneyAmount;
import uz.pdpd.app6atm.payload.ApiResponse;
import uz.pdpd.app6atm.payload.MoneyAmountDto;
import uz.pdpd.app6atm.service.MoneyAmountService;
import java.util.List;
import java.util.UUID;

/**
 * * * * * * * * * * * * * * * * * * * * * * * * * *
 * _ Methods of this Class is for DIRECTOR only _ *
 * * * * * * * * * * * * * * * * * * * * * * * * * *
 **/
@RestController
@RequestMapping("/api/moneyAmount")
public class MoneyAmountController {

    @Autowired
    MoneyAmountService moneyAmountService;

    //__CREATE
    @PostMapping
    public HttpEntity<?> createMoneyAmount(@RequestBody MoneyAmountDto moneyAmountDto) {
        ApiResponse apiResponse = moneyAmountService.createMoneyAmount(moneyAmountDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    //__GET ALL__
    @GetMapping
    public HttpEntity<?> getAll(){
        List<MoneyAmount> list = moneyAmountService.getAll();
        return ResponseEntity.ok(list);
    }

    //__PUT__
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable UUID id, @RequestBody MoneyAmountDto moneyAmountDto){
        ApiResponse apiResponse = moneyAmountService.edit(id, moneyAmountDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    //__DELETE__
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable UUID id){
        ApiResponse apiResponse = moneyAmountService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }


}
