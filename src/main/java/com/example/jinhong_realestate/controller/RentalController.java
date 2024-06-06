package com.example.jinhong_realestate.controller;

import com.example.jinhong_realestate.entity.Rental;
import com.example.jinhong_realestate.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalService.findAllRentals();
    }

    @GetMapping("/{id}")
    public Rental getRentalById(@PathVariable("id") Long renId) {
        return rentalService.findRentalById(renId);
    }

    @PostMapping
    public Rental createRental(@RequestBody Rental rental) {
        return rentalService.saveRental(rental);
    }

    @PutMapping("/{id}")
    public Rental updateRental(@PathVariable("id") Long renId, @RequestBody Rental rental) {
        rental.setRen_id(renId);
        return rentalService.saveRental(rental);
    }

    @DeleteMapping("/{id}")
    public void deleteRental(@PathVariable("id") Long renId) {
        rentalService.deleteRental(renId);
    }

    //자치구 코드로 임대를 검색
    @GetMapping("/sgg/{sggCd}")
    public List<Rental> getRentalsBySggCd(@PathVariable String sggCd) {
        return rentalService.getRentalsBySggCd(sggCd);
    }

    //지정된 가격 범위 내에서 임대를 검색
    @GetMapping("/price-range")
    public List<Rental> getRentalsByPriceRange(@RequestParam int minFee, @RequestParam int maxFee) {
        return rentalService.getRentalsByPriceRange(minFee, maxFee);
    }

    //임대 정보를 저장하고 해당 대리인에게 할당
    @PostMapping("/agent/{agentId}")
    public ResponseEntity<Rental> saveRentalWithAgent(@RequestBody Rental rental, @PathVariable Long agentId) {
        Rental savedRental = rentalService.saveRentalWithAgent(rental, agentId);
        if (savedRental != null) {
            return ResponseEntity.ok(savedRental);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
