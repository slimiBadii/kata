package bank.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Transaction {

    private Account payer;
    private Account payee;
    private float amount;
    private Date date;

    public void execute() {
        payer.withdraw(amount);
        payer.addTransaction(this);

        payee.deposit(amount);
        payee.addTransaction(this);
    }
}
