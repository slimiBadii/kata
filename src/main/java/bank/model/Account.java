package bank.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode
public class Account {

    private float amount;
    private List<Transaction> transactions;

    public Account() {
        amount = 0;
        transactions = new ArrayList<Transaction>();
    }


    public Account(float amount) {
        this.amount = amount;
        transactions = new ArrayList<Transaction>();
    }

    public void deposit(float amount) {
        this.amount += amount;
    }

    public void withdraw(float amount) {
        this.amount -= amount;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactionsWhenPayer() {
        return transactions.stream().filter(transaction -> transaction.getPayer().equals(this)).collect(Collectors.toList());
    }

    public List<Transaction> getTransactionsWhenPayee() {
        return transactions.stream().filter(transaction -> transaction.getPayee().equals(this)).collect(Collectors.toList());
    }

}
