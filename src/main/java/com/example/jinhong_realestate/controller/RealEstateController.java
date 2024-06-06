package com.example.jinhong_realestate.controller;

import com.example.jinhong_realestate.dto.AgentDTO;
import com.example.jinhong_realestate.entity.Rental;
import com.example.jinhong_realestate.entity.Transaction;
import com.example.jinhong_realestate.service.RealEstateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class RealEstateController {
    /**
     * 통합 검색 및 비교 기능 Controller
     */
    private final RealEstateService realEstateService;

    @GetMapping("/recommendations/{userId}/transactions")
    public List<Transaction> recommendTransactions(@PathVariable Long userId) {
        return realEstateService.recommendTransactions(userId);
    }

    @GetMapping("/recommendations/{userId}/rentals")
    public List<Rental> recommendRentals(@PathVariable Long userId) {
        return realEstateService.recommendRentals(userId);
    }

    @GetMapping("/agents")
    public List<AgentDTO> searchAgents(@RequestParam String location, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return realEstateService.searchAgents(location, page, size);
    }

    @GetMapping("/transactions")
    public List<Transaction> searchTransactions(@RequestParam String location, @RequestParam int minPrice, @RequestParam int maxPrice) {
        return realEstateService.searchTransactions(location, minPrice, maxPrice);
    }

    @GetMapping("/rentals")
    public List<Rental> searchRentals(@RequestParam String location, @RequestParam int minRent, @RequestParam int maxRent) {
        return realEstateService.searchRentals(location, minRent, maxRent);
    }

    @GetMapping("/trends/region/{sggCd}")
    public Map<String, Object> analyzeMarketTrends(@PathVariable String sggCd) {
        return realEstateService.analyzeMarketTrends(sggCd);
    }
}
