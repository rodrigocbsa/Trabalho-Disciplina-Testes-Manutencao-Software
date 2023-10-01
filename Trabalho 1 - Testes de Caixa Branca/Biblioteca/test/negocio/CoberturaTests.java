package negocio;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CaminhosBasicosTest.class, ClientTest.class, DataUtilsTest.class, MulticondicaoTest.class,
		RenovarEmprestimoTest.class, TodosOsNosTest.class })
public class CoberturaTests {

}
