package admin;
import application.Application;
import bankaccount.Account_status;
import card.Card;
import java.util.ArrayList;

public class Admin {
    public static int Find(int password, int accountNumber) {
        ArrayList<Card> cards = Application.cards;
        int resCardIndex = -1;
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (card.getPassword() == password && card.getAccountNumber() == accountNumber)
                resCardIndex = i;
        }
        return resCardIndex;
    }
    private static void PrintCardInfo(Card card)
    {
        System.out.println(String.format("\tвладелец: %s\n\tдата выдачи: %s\n\t" +
                        "срок службы в днях: %d\n\tбаланс: %d\n\t" +
                        "банк: %s\n\tномер акаунта: %d\n\t" +
                        "тип карты: %s\n\tстатус карты: %s",
                card.getOwner(), card.getIssue_date(),
                card.getValidity(), card.getBalance(),
                card.getBankName(), card.getAccountNumber(),
                card.getKind(), card.getStatus().toString()));
    }
    public static void FindInfo(int password, int accountNumber) {
        ArrayList<Card> cards = Application.cards;
        Card resCard = new Card();
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            if (card.getPassword() == password && card.getAccountNumber() == accountNumber)
                resCard = card;
        }

        PrintCardInfo(resCard);
    }


    public static void BlockAccount(int password, int accountNumber) {
        Application.cards.get(Find(password, accountNumber)).setStatus(Account_status.Blocked);
    }

    public static void Reissue(int password, int accountNumber) {
        int cardIndex = Find(password, accountNumber);
        //Application.cards.add(Application.CreateCard(card.getOwner()));
        Application.cards.set(cardIndex, Application.CreateCard(Application.cards.get(cardIndex).getOwner()));
    }
}
