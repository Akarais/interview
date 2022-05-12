package com.example.interview.transaction;

import com.example.interview.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping(path="/transaction")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping(path="/")
    @JsonView(View.Internal.class)
    public @ResponseBody Iterable<Transaction> getTransactionsExceedingAmountWithCustomers(
            @RequestParam(name = "min_amount") BigDecimal amount
    ) {
        return transactionRepository.getAllExceedingAmount(amount);
    }
}
