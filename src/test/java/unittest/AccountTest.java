package unittest;

import bank.model.Account;
import bank.model.Transaction;
import bank.service.AccountService;
import bank.service.impl.AccountServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;


public class AccountTest {

    private AccountService accountService;

    private Account payer;
    private Account payee1;
    private Account payee2;

    @Before
    public void setUp() {
        accountService = new AccountServiceImpl();
        payer = new Account(500);
        payee1 = new Account(200.2f);
        payee2 = new Account(20.1f);
    }

    @Test(expected = Exception.class)
    public void testDepositNullAmount() throws Exception {
        accountService.depositAmount(null, 100);
    }

    @Test
    public void testDepositAmount() throws Exception {
        accountService.depositAmount(payer, 100);
        assertAccountAmountEquals(payer, 600);
    }

    @Test
    public void testWithDrawAmountNullAccount() {
        try {
            accountService.depositAmount(null, 100);
        } catch(Exception e ) {
            Assert.assertEquals(e.getMessage(), accountService.ERROR_MESSAGE);
        }
    }

    @Test
    public void testWithDrawAmount() throws Exception {
        accountService.WithDrawAmount(payer, 100);
        assertAccountAmountEquals(payer, 400);
    }

    @Test
    public void testTransfert() throws Exception {
       accountService.transferMoney(payer, payee1, 100, new Date());

        assertAccountAmountEquals(payer, 400);
        assertAccountAmountEquals(payee1, 300.2f);
    }

    @Test(expected = Exception.class)
    public void testTransfertToNonExistantAccount() throws Exception {
        accountService.transferMoney(payer, null, 50, new Date());
    }

    @Test
    public void testTransfertAndCheckTransaction() throws Exception {
        Transaction transaction = accountService.transferMoney(payer, payee1, 50, new Date());

        Assert.assertNotNull(transaction);
        Assert.assertEquals(transaction.getPayer(), payer);
        Assert.assertEquals(transaction.getPayee(), payee1);
    }

    @Test
    public void testTransfertAndCheckTransactionsHistories() throws Exception {

        // payer -> payee 1
        accountService.transferMoney(payer, payee1, 100, new Date());
        // payer -> payee 2
        accountService.transferMoney(payer, payee2, 150, new Date());
        // payee1 -> payee 2
        accountService.transferMoney(payee1, payee2, 200, new Date());

        Assert.assertEquals(payer.getTransactions().size(), 2);
        Assert.assertEquals(payer.getTransactionsWhenPayer().size(), 2);
        Assert.assertEquals(payer.getTransactionsWhenPayee().size(), 0);


        Assert.assertEquals(payee1.getTransactions().size(), 2);
        Assert.assertEquals(payee1.getTransactionsWhenPayer().size(), 1);
        Assert.assertEquals(payee1.getTransactionsWhenPayee().size(), 1);

        Assert.assertEquals(payee2.getTransactions().size(), 2);
        Assert.assertEquals(payee2.getTransactionsWhenPayer().size(), 0);
        Assert.assertEquals(payee2.getTransactionsWhenPayee().size(), 2);

    }

    private void assertAccountAmountEquals(Account account, float amount) {
        Assert.assertTrue(account.getAmount() == amount);
    }
}
