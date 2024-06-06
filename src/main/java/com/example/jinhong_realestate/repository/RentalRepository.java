package com.example.jinhong_realestate.repository;

import com.example.jinhong_realestate.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findBySggNm(String sggNm);
    List<Rental> findByRentFeeBetween(int minFee, int maxFee);
    List<Rental> findBySggNmAndRentFeeBetween(String sggNm, int minFee, int maxFee);
}
