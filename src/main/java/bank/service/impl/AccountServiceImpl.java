package bank.service.impl;


import bank.model.Account;
import bank.model.Transaction;
import bank.service.AccountService;

import java.util.Date;

public class AccountServiceImpl implements AccountService{

    public void depositAmount(Account account, float amount) throws Exception {
        checkAccountExistence(account);
        account.deposit(amount);
    }

    public void WithDrawAmount(Account account, float amount) throws Exception {
        checkAccountExistence(account);
        account.withdraw(amount);
    }

    public Transaction transferMoney(Account payer, Account payee, float amount, Date date) throws Exception {
        checkAccountExistence(payer);
        checkAccountExistence(payee);

        Transaction transaction = new Transaction(payer, payee, amount, date);
        transaction.execute();
        return transaction;
    }

    private void checkAccountExistence(Account account) throws Exception {
        if(account == null) {
            throw new Exception(ERROR_MESSAGE);
        }
    }
}
