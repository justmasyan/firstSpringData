package mainpackage.withoutSpringData;

import mainpackage.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class AccountRepository {

    final private JdbcTemplate jdbc;

    AccountRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public Account findById(int id){
        String sql =    "SELECT *\n" +
                "FROM account\n" +
                "WHERE id_account = " + id + ";";

        RowMapper<Account> rowmapper = (rs, rowNum) -> {
            Account account = new Account();
            account.setId_account(id);
            account.setName(rs.getString("name"));
            account.setAmount(rs.getBigDecimal("amount"));
            return account;
        };

        List<Account> list = jdbc.query(sql,rowmapper);


        return list.get(0);
    }

    public void changeAmount(int id, BigDecimal amount){
        String sql = "UPDATE account\n" +
                "SET amount = " + amount + "\n" +
                "WHERE id_account = " + id + ";";
        jdbc.update(sql);
    }

    public List<Account> findAllAccounts(){
        String sql = "SELECT * FROM account;";

        RowMapper<Account> rowMapper = (rs, rowNum) -> {
            Account account = new Account();
            account.setId_account(rs.getInt("id_account"));
            account.setName(rs.getString("name"));
            account.setAmount(rs.getBigDecimal("amount"));
            return account;
        };

        return jdbc.query(sql,rowMapper);
    }
}
