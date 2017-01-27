package bank.service;

import bank.model.Account;
import bank.model.Transaction;

import java.util.Date;

public interface AccountService {

    static String ERROR_MESSAGE = "Cannot execute transaction from/in non existant account !";

    void depositAmount(Account account, float amount) throws Exception;

    void WithDrawAmount(Account account, float amount) throws Exception;

    Transaction transferMoney(Account payer, Account payee, float amount, Date date) throws Exception;

}
