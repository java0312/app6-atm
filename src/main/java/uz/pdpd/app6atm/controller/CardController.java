package uz.pdpd.app6atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdpd.app6atm.entity.Card;
import uz.pdpd.app6atm.payload.ApiResponse;
import uz.pdpd.app6atm.payload.CardDto;
import uz.pdpd.app6atm.service.CardService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    CardService cardService;

    //__CREATE  CARD__ (by DIRECTOR only)
    @PostMapping
    public HttpEntity<?> createCard(@RequestBody CardDto cardDto){
        ApiResponse apiResponse = cardService.createCard(cardDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    //__GET ALL CARDS__ (for DIRECTOR only)
    @GetMapping
    public HttpEntity<?> getAllCards(){
        List<Card> cards = cardService.getAllCards();
        return ResponseEntity.ok(cards);
    }

    //__GET ONE CARD__ (for DIRECTOR ...)
    @GetMapping("/{id}")
    public HttpEntity<?> getCard(@PathVariable UUID id){
        Card card = cardService.getCard(id);
        if (card == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(card);
    }

    //__EDIT CARD__ (by DIRECTOR only)
    @PutMapping("/{id}")
    public HttpEntity<?> editCard(@PathVariable UUID id, @RequestBody CardDto cardDto){
        ApiResponse apiResponse = cardService.editCard(id, cardDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    //__DELETE CARD__ (by DIRECTOR only)
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCard(@PathVariable UUID id){
        ApiResponse apiResponse = cardService.deleteCard(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }

}
