package mainpackage.services;

import mainpackage.AccountNotFoundException;
import mainpackage.model.Account;
import mainpackage.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {
    final private AccountRepository repository;

    public TransferService(AccountRepository repository){
        this.repository = repository;
    }

    @Transactional
    public void transactMoney(Integer idFirst, int idSecond, BigDecimal amount){
        var first = repository.findById(idFirst).orElseThrow(() -> new AccountNotFoundException());
        Account second = repository.findById(idSecond).orElseThrow(() -> new AccountNotFoundException());

        BigDecimal firstAmount = first.getAmount().subtract(amount);
        BigDecimal secondAmount = second.getAmount().add(amount);

        repository.changeAmount(idFirst,firstAmount);
        repository.changeAmount(idSecond,secondAmount);

    }

    public Iterable<Account> getAllAccounts(){
        return repository.findAll();
    }

    public List<Account> findAccountsByName(String name){
        return repository.findAccountsByName(name);
    }
}
