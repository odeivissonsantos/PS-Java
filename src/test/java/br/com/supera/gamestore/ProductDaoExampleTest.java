package br.com.supera.gamestore;

import java.util.List;

import br.com.supera.gamestore.models.Produto;

import com.github.dbunit.rules.DBUnitRule;
import com.github.dbunit.rules.api.configuration.DBUnit;
import com.github.dbunit.rules.api.dataset.DataSet;
import com.github.dbunit.rules.util.EntityManagerProvider;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.github.dbunit.rules.util.EntityManagerProvider.em;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(JUnit4.class)
public class ProductDaoExampleTest {

    @Rule
    public EntityManagerProvider emProvider = EntityManagerProvider.instance("productDS");

    @Rule
	public DBUnitRule dbUnitRule = DBUnitRule.instance(emProvider.connection());
	
	@Test
	@DBUnit(allowEmptyFields = true)
    @DataSet("products.yml")
    public void shouldListProducts() {
		try {
			List<Produto> produtos = em().
					createQuery("select p from Produto p").
					getResultList();
			assertNotNull(produtos);
			assertEquals(9, produtos.size());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
        
    }
}
