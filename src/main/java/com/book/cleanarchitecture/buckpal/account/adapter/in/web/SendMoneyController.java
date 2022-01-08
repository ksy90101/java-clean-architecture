package com.book.cleanarchitecture.buckpal.account.adapter.in.web;

import com.book.cleanarchitecture.buckpal.account.application.port.in.SendMoneyCommand;
import com.book.cleanarchitecture.buckpal.account.application.port.in.SendMoneyUseCase;
import com.book.cleanarchitecture.buckpal.account.domain.Money;
import com.book.cleanarchitecture.buckpal.account.domain.vo.AccountId;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMoneyController {
    private final SendMoneyUseCase sendMoneyUseCase;

    public SendMoneyController(SendMoneyUseCase sendMoneyUseCase) {
        this.sendMoneyUseCase = sendMoneyUseCase;
    }

    @PostMapping("/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}")
    public void sendMoney(@PathVariable Long sourceAccountId, @PathVariable Long targetAccountId, @PathVariable Long amount) {
        SendMoneyCommand sendMoneyCommand = new SendMoneyCommand(new AccountId(sourceAccountId), new AccountId(targetAccountId), Money.of(amount));

        sendMoneyUseCase.sendMoney(sendMoneyCommand);
    }
}
