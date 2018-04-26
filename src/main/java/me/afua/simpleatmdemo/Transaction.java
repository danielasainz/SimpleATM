package me.afua.simpleatmdemo;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @ManyToOne
    Account theAccount;

    private String transactionType;

    private boolean successful;

    private double amount;

    private double balanceAfter;

    public Transaction() {
        this.successful=false;
    }

    public Transaction(Account theAccount) {
        this.theAccount = theAccount;
        this.successful=false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Account getTheAccount() {
        return theAccount;
    }

    public void setTheAccount(Account theAccount) {
        this.theAccount = theAccount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean withdraw(double theamount)
    {
        this.setAmount(theamount);
        this.transactionType="withdrawal";
        if(theamount<=this.theAccount.getBalance())
        {
            this.theAccount.setBalance(this.theAccount.getBalance()-theamount);
            successful=true;
        }
        else
            successful=false;
        this.balanceAfter=this.theAccount.getBalance();
        return successful;
    }

    public void deposit(double theamount)
    {
        this.setAmount(theamount);
        this.theAccount.setBalance(this.theAccount.getBalance()+theamount);
        this.balanceAfter=this.theAccount.getBalance();
        this.transactionType="deposit";
        successful=true;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(double balanceAfter) {
        this.balanceAfter = balanceAfter;
    }
}
