package card;

import admin.Admin;
import bankaccount.Account;
import bankaccount.Account_status;

import java.util.Date;

public class Card extends Account {
    private Date issueDate;
    public Date getIssue_date() {
        return issueDate;
    }
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    private int validity; //срок действия карты в днях
    public void setValidity(int validity) {
        this.validity = validity;
    }
    public int getValidity() {
        return validity;
    }

    private String owner;
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getOwner() {
        return owner;
    }

    public Card(Date issue_date, int validity, String owner,
                int password, int accountNumber, String kind, String bankName) {
        super(password, accountNumber, kind, bankName);
        this.issueDate = issue_date;
        this.validity = validity;
        this.owner = owner;
    }
    public Card() {}
}
