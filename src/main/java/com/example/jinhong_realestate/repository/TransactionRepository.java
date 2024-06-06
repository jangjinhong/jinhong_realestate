package com.example.jinhong_realestate.repository;

import com.example.jinhong_realestate.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySggNm(String sggNm);
    List<Transaction> findByObjAmtBetween(int minAmt, int maxAmt);

    /**
     * 특정 위치에 속한 거래 및 해당 거래에 연결된 대리인을 적재(채움)하고,
     * 거래의 가격 범위가 주어진 최소 및 최대 가격 사이에 있는지를 확인합니다.
     * */
    @Query("SELECT t FROM Transaction t JOIN FETCH t.agent a WHERE t.sggNm = :location AND t.objAmt BETWEEN :minPrice AND :maxPrice")
    List<Transaction> findTransactionsWithAgentByLocationAndPriceRange(@Param("location") String location, @Param("minPrice") int minPrice, @Param("maxPrice") int maxPrice);

        List<Transaction> findBySggNmAndObjAmtBetween(String sggNm, int minAmt, int maxAmt);
}
