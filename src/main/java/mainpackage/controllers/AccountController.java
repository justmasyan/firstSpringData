package mainpackage.controllers;

import mainpackage.model.Account;
import mainpackage.model.TransferRequest;
import mainpackage.services.TransferService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfer")
public class AccountController {

    final private TransferService transferService;

    AccountController(TransferService transferService){
        this.transferService = transferService;
    }

    @GetMapping()
    public Iterable<Account> infoAboutAccount(@RequestParam(required = false) String name) {
        if(name == null)
            return transferService.getAllAccounts();
        else
            return transferService.findAccountsByName(name);
    }

    @PostMapping()
    public void transferMoney(@RequestBody TransferRequest request){
        transferService.transactMoney(request.getFirst(),
                request.getSecond(),
                request.getAmount());
    }

}
