package uz.pdpd.app6atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdpd.app6atm.entity.Atm;
import uz.pdpd.app6atm.payload.ApiResponse;
import uz.pdpd.app6atm.payload.AtmDto;
import uz.pdpd.app6atm.service.AtmService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/atm")
public class AtmController {

    @Autowired
    AtmService atmService;

    @PostMapping
    public HttpEntity<?> createAtm(@RequestBody AtmDto atmDto){
        ApiResponse apiResponse = atmService.createAtm(atmDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAllAtms(){
        List<Atm> atms = atmService.getAllAtms();
        return ResponseEntity.ok(atms);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getAtm(@PathVariable UUID id){
        Atm atm = atmService.getAtm(id);
        if(atm == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(atm);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editAtm(@PathVariable UUID id, @RequestBody AtmDto atmDto){
        ApiResponse apiResponse = atmService.editAtm(id, atmDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteAtm(@PathVariable UUID id){
        ApiResponse apiResponse = atmService.deleteAtm(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

}
