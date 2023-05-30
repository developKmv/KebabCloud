/*
package ru.dev.A1.A1.data;

import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.dev.A1.A1.models.Ingredient;
import ru.dev.A1.A1.models.Kebab;
import ru.dev.A1.A1.models.KebabOrder;
import ru.dev.A1.A1.models.IngredientRef;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcOrderRepository implements OrderRepository{
    private JdbcOperations jdbcOperations;
    public JdbcOrderRepository(JdbcOperations jdbcOperations){
        this.jdbcOperations = jdbcOperations;
    }
    @Override
    @Transactional
    public KebabOrder save(KebabOrder kebabOrder) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into Kebab_Order "
                        + "(delivery_name, delivery_street, delivery_city, "
                        + "delivery_state, delivery_zip, cc_number, "
                        + "cc_expiration, cc_cvv, placed_at) "
                        + "values (?,?,?,?,?,?,?,?,?)",
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        );

        pscf.setReturnGeneratedKeys(true);

        kebabOrder.setPlacedAt(new Date());
        PreparedStatementCreator psc =
                pscf.newPreparedStatementCreator(
                        Arrays.asList(
                                kebabOrder.getDeliveryName(),
                                kebabOrder.getDeliveryStreet(),
                                kebabOrder.getDeliveryCity(),
                                kebabOrder.getDeliveryState(),
                                kebabOrder.getDeliveryZip(),
                                kebabOrder.getCcNumber(),
                                kebabOrder.getCcExpiration(),
                                kebabOrder.getCcCVV(),
                                kebabOrder.getPlacedAt()));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcOperations.update(psc, keyHolder);

        long orderId = keyHolder.getKey().longValue();
        kebabOrder.setId(orderId);

        List<Kebab> kebabs = kebabOrder.getKebabs();
        int i=0;
        for (Kebab kebab:kebabs){
            saveKebab(orderId,i++,kebab);
        }
        return kebabOrder;
    }

    private long saveKebab(Long orderId, int orderKey, Kebab kebab) {
        kebab.setCreateDate(new Date());
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                        "insert into Kebab "
                                + "(name, created_at, kebab_order, kebab_order_key) "
                                + "values (?, ?, ?, ?)",
                        Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG
                );
        pscf.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc =
                pscf.newPreparedStatementCreator(
                        Arrays.asList(
                                kebab.getName(),
                                kebab.getCreateDate(),
                                orderId,
                                orderKey));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcOperations.update(psc, keyHolder);
        long kebabId = keyHolder.getKey().longValue();
        kebab.setId(kebabId);

        saveIngredientRefs(kebabId, kebab.getIngredients());
        return kebabId;
    }
    private void saveIngredientRefs(long kebabId, List<Ingredient> ingredient) {
        int key = 0;

        for (Ingredient i : ingredient) {
            jdbcOperations.update(
                    "insert into Ingredient_Ref (ingredient, kebab, kebab_key) "
                            + "values (?, ?, ?)",
                    i.getId(), kebabId, key++);
        }
    }
}
*/
