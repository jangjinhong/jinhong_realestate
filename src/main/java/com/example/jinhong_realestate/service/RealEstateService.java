package com.example.jinhong_realestate.service;

import com.example.jinhong_realestate.dto.AgentDTO;
import com.example.jinhong_realestate.entity.Agent;
import com.example.jinhong_realestate.entity.Rental;
import com.example.jinhong_realestate.entity.Transaction;
import com.example.jinhong_realestate.entity.User;
import com.example.jinhong_realestate.repository.AgentRepository;
import com.example.jinhong_realestate.repository.RentalRepository;
import com.example.jinhong_realestate.repository.TransactionRepository;
import com.example.jinhong_realestate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RealEstateService {

    private final AgentRepository agentRepository;
    private final TransactionRepository transactionRepository;
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;

    /** 주어진 사용자의 선호 위치와 가격 범위에 따라 거래를 추천
     *
     * @param userId
     * @return 선호 위치와 가격 범위에 따른 거래 List
     */
    public List<Transaction> recommendTransactions(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return transactionRepository.findBySggNmAndObjAmtBetween(
                    user.getPreferredLocation(), user.getMinPrice(), user.getMaxPrice()
            );
        }
        return new ArrayList<>();
    }

    /** 주어진 사용자의 선호 위치와 임대료 범위에 따라 임대를 추천
     * @param userId
     * @return 사용자의 선호 위치와 임대료 범위에 따라 임대를 추천 list
     */
    public List<Rental> recommendRentals(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return rentalRepository.findBySggNmAndRentFeeBetween(
                    user.getPreferredLocation(), user.getMinRent(), user.getMaxRent()
            );
        }
        return new ArrayList<>();
    }

    /**
     * 지정된 위치에 대한 대리점을 페이징
     * @param location
     * @param page
     * @param size
     */
    public List<AgentDTO> searchAgents(String location, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Agent> agentsPage = agentRepository.findByAddressContaining(location, pageable);
        return agentsPage.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private AgentDTO convertToDTO(Agent agent) {
        AgentDTO dto = new AgentDTO();
        dto.setId(agent.getAgent_id());
        dto.setAddress(agent.getAddress());
        dto.setCmpNm(agent.getCmpNm());
        dto.setRdealerNm(agent.getRdealerNm());
        return dto;
    }

    /**
     * 지정된 위치 및 가격 범위에 대한 거래를 검색
     * @param location
     * @param minPrice
     * @param maxPrice
     */
    public List<Transaction> searchTransactions(String location, int minPrice, int maxPrice) {
        return transactionRepository.findTransactionsWithAgentByLocationAndPriceRange(location, minPrice, maxPrice);
    }

    /**
     * 지정된 위치 및 임대료 범위에 대한 임대를 검색
     * @param location
     * @param minRent
     * @param maxRent
     */
    public List<Rental> searchRentals(String location, int minRent, int maxRent) {
        return rentalRepository.findBySggNmAndRentFeeBetween(location, minRent, maxRent);
    }

    /**
     * 특정 지역의 시장 동향을 분석
     * @param sggNm
     * @return
     */
    public Map<String, Object> analyzeMarketTrends(String sggNm) {
        List<Transaction> transactions = transactionRepository.findBySggNm(sggNm);
        Map<String, Object> trends = new HashMap<>();
        trends.put("averagePrice", transactions.stream().mapToInt(Transaction::getObjAmt).average().orElse(0));
        trends.put("transactionVolume", transactions.size());
        return trends;
    }

    /** 특정 agent가 갖고 있는 매물
     * @param agentId
     * @return
     */
    public List<Transaction> getPropertiesByAgent(Long agentId) {
        Agent agent = agentRepository.findById(agentId).orElse(null);
        if (agent != null) {
            return agent.getTransactions(); // 에이전트가 소유한 거래 목록을 반환
        }
        return new ArrayList<>();
    }
}