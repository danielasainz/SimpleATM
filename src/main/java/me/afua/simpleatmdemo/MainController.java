package me.afua.simpleatmdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @Autowired
    AccountRepository accounts;

    @Autowired
    TransactionRepository transactions;

    @RequestMapping("/")
    public @ResponseBody String showMenu()
    {
        return "index";
    }

    @RequestMapping("/testaccount")
    public @ResponseBody
    String showAccountStuff()
    {

      StringBuilder builder = new StringBuilder();
      Account a = new Account(123456);
      accounts.save(a);

      Transaction transaction = new Transaction(a);
      transaction.deposit(200.50);
      transactions.save(transaction);

      transaction = new Transaction(a);
      transaction.deposit(500);
      transactions.save(transaction);


     if(!transaction.withdraw(800))
     {
         builder.append("Failure withdrawing "+transaction.getAmount());
     }
     else
     {
         builder.append("Successfully withdrew"+transaction.getAmount());
     }

        for (Transaction t:transactions.findAll()) {
         builder.append(t.getTransactionType().equalsIgnoreCase("deposit")?"Deposit:"+t.getAmount():(t.isSuccessful()?"Successful":"Unsuccessful")+"Withdrawal:"+t.getAmount());
         builder.append("<br/>");
        }

        builder.append("Current balance:"+a.getBalance());


        return builder.toString();
    }


}
