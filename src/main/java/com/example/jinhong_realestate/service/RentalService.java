package com.example.jinhong_realestate.service;

import com.example.jinhong_realestate.entity.Agent;
import com.example.jinhong_realestate.entity.Rental;
import com.example.jinhong_realestate.repository.AgentRepository;
import com.example.jinhong_realestate.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final AgentRepository agentRepository;

    public Rental saveRental(Rental rental) {
        return rentalRepository.save(rental);
    }

    /**
     * 주어진 ID에 해당하는 임대 정보를 찾습니다.
     *
     * @param rentalId 찾을 임대 정보의 ID
     * @return 주어진 ID에 해당하는 임대 정보
     */
    public Rental findRentalById(Long rentalId) {
        return rentalRepository.findById(rentalId).orElse(null);
    }

    public List<Rental> findAllRentals() {
        return rentalRepository.findAll();
    }

    public void deleteRental(Long rentalId) {
        rentalRepository.deleteById(rentalId);
    }

    /**
     * 주어진 시군구명에 해당하는 임대 정보를 반환합니다.
     *
     * @param sggNm 시군구명
     * @return 주어진 시군구명에 해당하는 임대 정보 리스트
     */
    public List<Rental> getRentalsBySggCd(String sggNm) {
        return rentalRepository.findBySggNm(sggNm);
    }

    /**
     * 주어진 임대료 범위에 해당하는 임대 정보를 반환합니다.
     *
     * @param minFee 최소 임대료
     * @param maxFee 최대 임대료
     * @return 주어진 임대료 범위에 해당하는 임대 정보 리스트
     */
    public List<Rental> getRentalsByPriceRange(int minFee, int maxFee) {
        return rentalRepository.findByRentFeeBetween(minFee, maxFee);
    }

    /**
     * 임대 정보를 저장하고 해당 대리인에게 할당합니다.
     *
     * @param rental 저장할 임대 정보
     * @param agentId 할당할 대리인의 ID
     * @return 저장된 임대 정보
     */
    public Rental saveRentalWithAgent(Rental rental, Long agentId) {
        Agent agent = agentRepository.findById(agentId).orElse(null);
        if (agent != null) {
            rental.setAgent(agent);
            return rentalRepository.save(rental);
        }
        return null;
    }
}
