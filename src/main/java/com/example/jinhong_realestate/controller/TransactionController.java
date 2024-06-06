package com.example.jinhong_realestate.controller;

import com.example.jinhong_realestate.entity.Transaction;
import com.example.jinhong_realestate.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.findAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable("id") Long transId) {
        return transactionService.findTransactionById(transId);
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable("id") Long transId, @RequestBody Transaction transaction) {
        transaction.setTrans_id(transId);
        return transactionService.saveTransaction(transaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable("id") Long transId) {
        transactionService.deleteTransaction(transId);
    }

    @GetMapping("/sgg/{sggCd}")
    public List<Transaction> getTransactionsBySggCd(@PathVariable String sggCd) {
        return transactionService.getTransactionsBySggCd(sggCd);
    }

    @GetMapping("/price-range")
    public List<Transaction> getTransactionsByPriceRange(@RequestParam int minAmt, @RequestParam int maxAmt) {
        return transactionService.getTransactionsByPriceRange(minAmt, maxAmt);
    }
}
