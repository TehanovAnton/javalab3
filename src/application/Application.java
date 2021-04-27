package application;
import admin.Admin;
import bankaccount.Account_status;
import card.Card;
import client.Client;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import src.Main;

import javax.swing.*;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Application {
    public static ArrayList<Card> cards = new ArrayList<Card>();
    public static ArrayList<Client> clients = new ArrayList<Client>();
    private static Scanner in = new Scanner(System.in);
    private static Act act;
    private static Logger LOG = Logger.getLogger(Main.class);

    public static Card CreateCard(String clientFio)
    {
        Card newCard = new Card();
        System.out.print("информация о карте:\n\t" +
                "введите время службы: ");
        newCard.setValidity(in.nextInt());
        newCard.setOwner(clientFio);

        System.out.print("\tпридумайте пароль: ");
        newCard.setPassword(in.nextInt());

        System.out.print("\tпридумайте номер акаунта: ");
        newCard.setAccountNumber(in.nextInt());

        in = new Scanner(System.in);
        System.out.print("\tукажите тип карты: ");
        newCard.setKind(in.nextLine());

        System.out.print("\tукажите название банка: ");
        newCard.setBankName(in.nextLine());
        newCard.setIssueDate(new Date());
        newCard.setStatus(Account_status.Opened);
        return newCard;
    }

    public static void AddClient(){
        Client newClient = new Client();

        System.out.print("информация о клиенте:\n\t" +
                "введите фио: ");
        newClient.setFio(in.nextLine());

        newClient.setCard(CreateCard(newClient.getFio()));
        cards.add(newClient.getCard());
        clients.add(newClient);
    }
    public static void Print()
    {
        for(Card cl : cards)
        {
            System.out.println(String.format("\tвладелец: %s\n\tдата выдачи: %s\n\t" +
                            "срок службы в днях: %d\n\tбаланс: %d\n\t" +
                            "банк: %s\n\tномер акаунта: %d\n\t" +
                            "тип карты: %s\n\tстатус карты: %s",
                            cl.getOwner(), cl.getIssue_date(),
                            cl.getValidity(), cl.getBalance(),
                            cl.getBankName(), cl.getAccountNumber(),
                            cl.getKind(), cl.getStatus().toString()));
        }
    }

    public static void AskAdmin()
    {
        System.out.print("информация для админнистратора:\n\t" +
                "пароль: ");
        int password = in.nextInt();
        System.out.print("");

        System.out.print("\tномер акаунта: ");
        int accountnumber = in.nextInt();

        System.out.print("найти информацию о карте - [f]\n" +
                "заблокирровать - [b]\n" +
                "переиздать - [r]\n" +
                "введите [f|b|r]: ");
        in = new Scanner(System.in);
        String input = in.nextLine();
        if (input.equals("f"))
            Admin.FindInfo(password, accountnumber);
        else if (input.equals("b"))
            Admin.BlockAccount(password, accountnumber);
        else
            Admin.Reissue(password, accountnumber);
    }

    public static void Run()
    {
        System.out.println("\tздраствуйте");
        Continue();
    }

    private static void Choice()
    {
        String input = in.nextLine();
        if (input.equals("c"))
            act = Act.AddClient;
        else if (input.equals("p"))
            act = Act.ShowInfo;
        else if (input.equals("a"))
            act = Act.AskAdmin;
        else if (input.equals("b"))
            act = Act.TopupBalanc;
        else if (input.equals("pay"))
            act = Act.Pay;
        else if (input.equals("block"))
            act = Act.Block;
        else if (input.equals("v"))
            act = Act.ViewBalance;
        else
            act = Act.Stop;
    }

    public static void Continue()
    {
        for (boolean work = true; work;)
        {
            try
            {
                System.out.print("введите символ действия:\n\t" +
                        "добавить клиетна - [c]\n\t" +
                        "вывести информацию о клиентах - [p]\n\t" +
                        "обратитья к администратору - [a]\n\t" +
                        "пополнить счёт - [b]\n\t" +
                        "оплатить - [pay]\n\t" +
                        "заблокировать акаунт - [block]\n\t" +
                        "посмотреть баланс - [v]\n\t" +
                        "закончить - [s]\n\t" +
                        "ввод [c|p|a|b|pay|block|v|s]: ");
                Choice();
                System.out.println("");


                if (act == Act.AddClient)
                    AddClient();
                else if (act == Act.ShowInfo)
                    Print();
                else if (act == Act.AskAdmin)
                    AskAdmin();
                else if (act == Act.Pay)
                {
                    System.out.print("введите fio: ");
                    String fio = in.nextLine();
                    clients.get(Client.Find(fio)).Pay(fio, 500);
                }
                else if (act == Act.TopupBalanc)
                {
                    System.out.print("введите fio: ");
                    String fio = in.nextLine();
                    clients.get(Client.Find(fio)).TopUpBalance(fio, 500);
                }
                else if (act == Act.Block)
                {
                    System.out.print("введите fio: ");
                    String fio = in.nextLine();
                    clients.get(Client.Find(fio)).BlockAccount(fio);
                }
                else if (act == Act.ViewBalance)
                {
                    System.out.print("введите fio: ");
                    String fio = in.nextLine();
                    clients.get(Client.Find(fio)).GetBalance(fio);
                }
                else
                    work = false;

                System.out.println("");
            }
            catch(Error e)
            {
                LOG.info(e.getMessage());
            }
        }

    }
}
