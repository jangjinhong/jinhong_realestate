package com.example.jinhong_realestate.service;

import com.example.jinhong_realestate.entity.Transaction;
import com.example.jinhong_realestate.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    /**
     * 거래 정보를 저장합니다.
     *
     * @param transaction 저장할 거래 정보
     * @return 저장된 거래 정보
     */
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    /**
     * 주어진 ID에 해당하는 거래 정보를 찾습니다.
     *
     * @param transactionId 찾을 거래 정보의 ID
     * @return 주어진 ID에 해당하는 거래 정보
     */
    public Transaction findTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId).orElse(null);
    }

    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    /**
     * 주어진 시군구명에 해당하는 거래 정보를 반환합니다.
     *
     * @param sggNm 시군구명
     * @return 주어진 시군구명에 해당하는 거래 정보 리스트
     */
    public List<Transaction> getTransactionsBySggCd(String sggNm) {
        return transactionRepository.findBySggNm(sggNm);
    }

    /**
     * 주어진 가격 범위에 해당하는 거래 정보를 반환합니다.
     *
     * @param minAmt 최소 가격
     * @param maxAmt 최대 가격
     * @return 주어진 가격 범위에 해당하는 거래 정보 리스트
     */
    public List<Transaction> getTransactionsByPriceRange(int minAmt, int maxAmt) {
        return transactionRepository.findByObjAmtBetween(minAmt, maxAmt);
    }
}
