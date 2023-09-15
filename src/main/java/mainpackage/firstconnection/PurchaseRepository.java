package mainpackage.firstconnection;

import mainpackage.firstconnection.Purchase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PurchaseRepository {
    final private JdbcTemplate jdbc;

    PurchaseRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public void storePurchase(Purchase purchase){
        String sql = "INSERT INTO purchase(product,price)" +
                "VALUES('" + purchase.getProduct() + "'," + purchase.getPrice() + ");";
        jdbc.update(sql);
    }

    public void createTable(){
        String sql ="CREATE TABLE purchase IF NOT EXISTS(\n" +
                "    id_product SERIAL PRIMARY KEY,\n" +
                "    product VARCHAR(30) NOT NULL,\n" +
                "    price BIGINT NOT NULL\n" +
                ");";
        jdbc.update(sql);
    }

    public List<Purchase> findAllPurchases(){
        String sql = "SELECT * FROM purchase;";

        RowMapper<Purchase> purchaseRowMapper = (rs, rowNum) -> {
            Purchase purchase = new Purchase();
            purchase.setId(rs.getInt("id_product"));
            purchase.setProduct(rs.getString("product"));
            purchase.setPrice(rs.getBigDecimal("price"));
            return purchase;
        };

        return jdbc.query(sql,purchaseRowMapper);
    }
}
