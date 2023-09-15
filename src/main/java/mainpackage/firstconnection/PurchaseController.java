package mainpackage.firstconnection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PurchaseController {

    final private PurchaseRepository repository;

    PurchaseController(PurchaseRepository repository){
        this.repository = repository;
    }

    @GetMapping("/purchase")
    public List<Purchase> findAllPurchases(){
        return repository.findAllPurchases();
    }

    @PostMapping("/create")
    public void createTable(){
        repository.createTable();
    }
    @PostMapping("/purchase")
    public void addPurchase(
            @RequestBody Purchase purchase
            ){
        repository.storePurchase(purchase);
    }
}
