package br.experience.angular.server.test.eao;

import java.util.Collection;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.experience.angular.server.model.Operadora;
import br.experience.angular.server.test.delegates.OperadoraEAODelegate;
import br.experience.angular.server.test.util.AngularXPTestUtil;
import junit.framework.TestCase;

@RunWith(Arquillian.class)
public class OperadoraEAOTest extends TestCase {

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "teste.war")
				.addPackage("br.experience.angular.server.eao")
				.addPackage("br.experience.angular.server.model")
				.addPackage("br.experience.angular.server.test.delegates")
				.addPackage("br.experience.angular.server.test.util")
				.addAsResource("META-INF/test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource("test-postgres-ds.xml", "test-ds.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE,
						ArchivePaths.create("beans.xml"));
	}
	
	@EJB
	private OperadoraEAODelegate operadoraEAO;
	
	@Test
	public void testSalvarOperadora(){
		
		final Integer CODIGO_OPERADORA = AngularXPTestUtil.getCodigoOperadora();
		final String NOME_OPERADORA = "NOME_"+CODIGO_OPERADORA;
		
		Operadora operadora = new Operadora();
		operadora.setCodigo(CODIGO_OPERADORA);
		operadora.setNome(NOME_OPERADORA);
		
		Operadora operadoraSalva = operadoraEAO.salvarOperadora(operadora);
		
		assertNotNull(operadoraSalva);
		assertNotNull(operadoraSalva.getId());
		assertEquals(CODIGO_OPERADORA, operadoraSalva.getCodigo());
		assertEquals(NOME_OPERADORA, operadoraSalva.getNome());
		
		
	}

	@Test
	public void testAlterarOperadora(){
		
		final Integer CODIGO_OPERADORA = AngularXPTestUtil.getCodigoOperadora();
		final String NOME_OPERADORA = "NOME_"+CODIGO_OPERADORA;
		
		final Integer CODIGO_OPERADORA_ALT = AngularXPTestUtil.getCodigoOperadora();
		final String NOME_OPERADORA_ALT = "NOME_ALTERADO_"+CODIGO_OPERADORA;
		
		Operadora operadora = new Operadora();
		operadora.setCodigo(CODIGO_OPERADORA);
		operadora.setNome(NOME_OPERADORA);
		
		Operadora operadoraSalva = operadoraEAO.salvarOperadora(operadora);
		
		assertNotNull(operadoraSalva);
		assertNotNull(operadoraSalva.getId());
		operadoraSalva.setCodigo(CODIGO_OPERADORA_ALT);
		operadoraSalva.setNome(NOME_OPERADORA_ALT);
		
		Operadora operadoraAlterada = operadoraEAO.salvarOperadora(operadoraSalva);
		
		assertNotNull(operadoraAlterada);
		assertNotNull(operadoraAlterada.getId());
		assertEquals(CODIGO_OPERADORA_ALT, operadoraAlterada.getCodigo());
		assertEquals(NOME_OPERADORA_ALT, operadoraAlterada.getNome());
		
	}
	
	@Test
	public void testConsultarOperadoraPorId(){
		
		final Integer CODIGO_OPERADORA = AngularXPTestUtil.getCodigoOperadora();
		final String NOME_OPERADORA = "NOME_"+CODIGO_OPERADORA;
		
		Operadora operadora = new Operadora();
		operadora.setCodigo(CODIGO_OPERADORA);
		operadora.setNome(NOME_OPERADORA);
		
		Operadora operadoraSalva = operadoraEAO.salvarOperadora(operadora);
		
		assertNotNull(operadoraSalva);
		assertNotNull(operadoraSalva.getId());
		
		Operadora operadoraConsultada = operadoraEAO
				.consultarOperadoraPorId(operadoraSalva.getId());
		
		assertNotNull(operadoraConsultada);
		assertNotNull(operadoraConsultada.getId());
		assertEquals(CODIGO_OPERADORA, operadoraConsultada.getCodigo());
		assertEquals(NOME_OPERADORA, operadoraConsultada.getNome());
		
		
	}
	
	@Test
	public void testConsultarOperadoraPornome(){
		
		final Integer CODIGO_OPERADORA = AngularXPTestUtil.getCodigoOperadora();
		final String NOME_OPERADORA = "NOME_"+CODIGO_OPERADORA;
		
		final String NOME_OPERADORA_TESTE_NOME = "test_"+NOME_OPERADORA;
		
		Operadora operadora = new Operadora();
		operadora.setCodigo(CODIGO_OPERADORA);
		operadora.setNome(NOME_OPERADORA_TESTE_NOME);
		
		Operadora operadoraSalva = operadoraEAO.salvarOperadora(operadora);
		
		assertNotNull(operadoraSalva);
		assertNotNull(operadoraSalva.getId());
		
		Collection<Operadora> operadoraConsultada = operadoraEAO
				.consultarOperadorasPorNome("test_");
		
		assertNotNull(operadoraConsultada);
		assertEquals(1, operadoraConsultada.size());
		
	}
	
	@Test
	public void testDeletarOperadora(){
		
		final Integer CODIGO_OPERADORA = AngularXPTestUtil.getCodigoOperadora();
		final String NOME_OPERADORA = "NOME_"+CODIGO_OPERADORA;
		
		Operadora operadora = new Operadora();
		operadora.setCodigo(CODIGO_OPERADORA);
		operadora.setNome(NOME_OPERADORA);
		
		Operadora operadoraSalva = operadoraEAO.salvarOperadora(operadora);
		
		assertNotNull(operadoraSalva);
		assertNotNull(operadoraSalva.getId());
		
		operadoraEAO.deletarOperadora(operadoraSalva);
		
		Operadora operadoraConsultada = operadoraEAO
				.consultarOperadoraPorId(operadoraSalva.getId());
		
		assertNull(operadoraConsultada);
		
	}
		
}
