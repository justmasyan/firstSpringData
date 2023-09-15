import mainpackage.AccountNotFoundException;
import mainpackage.model.Account;
import mainpackage.repository.AccountRepository;
import mainpackage.services.TransferService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


public class TransferServiceUnitTest {
    @Test
    @DisplayName("Check correct work with transfer money from one id to other")
    public void moneyTranserHappyFlow(){
        AccountRepository repository = mock(AccountRepository.class);
        TransferService service = new TransferService(repository);

        Account first = new Account();
        first.setAmount(new BigDecimal(1000));
        first.setId_account(1);

        Account second = new Account();
        second.setAmount(new BigDecimal(1000));
        second.setId_account(2);

        given(repository.findById(first.getId_account())).willReturn(Optional.of(first));
        given(repository.findById(second.getId_account())).willReturn(Optional.empty());
        assertThrows(AccountNotFoundException.class,
                () -> service.transactMoney(first.getId_account(),second.getId_account(),new BigDecimal(200))
        );

        verify(repository,never()).changeAmount(first.getId_account(),new BigDecimal(800));
        verify(repository,never()).changeAmount(second.getId_account(), new BigDecimal(1200));
    }
}
