package bankaccount;

import admin.Admin;

public abstract class Account {
    private int password;
    public void setPassword(int password) {
        this.password = password;
    }
    public int getPassword() {
        return password;
    }

    private int accountNumber;
    public int getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    private int balance;
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }

    private String kind;
    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }


    private Account_status status;
    public void setStatus(Account_status status) {
        this.status = status;
    }
    public Account_status getStatus() {
        return status;
    }

    public boolean IsOpen(){
        boolean isopened = status == Account_status.Opened;
        if (!isopened)
            System.out.println("акаунт заблокирован");
        return isopened;
    }


    private String bankName;
    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Account(int password, int accountNumber, String kind, String bankName) {
        this.password = password;
        this.accountNumber = accountNumber;
        this.kind = kind;
        this.status = Account_status.Opened;
        this.bankName = bankName;
        this.balance = 0;
    }
    public Account() {

    }
}
