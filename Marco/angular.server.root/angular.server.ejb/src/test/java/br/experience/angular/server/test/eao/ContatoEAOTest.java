package br.experience.angular.server.test.eao;

import java.util.Collection;
import java.util.Date;

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

import antlr.CSharpCodeGenerator;
import br.experience.angular.server.model.Contato;
import br.experience.angular.server.model.Operadora;
import br.experience.angular.server.test.delegates.ContatoEAODelegate;
import br.experience.angular.server.test.delegates.OperadoraEAODelegate;
import br.experience.angular.server.test.util.AngularXPTestUtil;
import junit.framework.TestCase;

@RunWith(Arquillian.class)
public class ContatoEAOTest extends TestCase {

	private static final Date DATA_CONTATO = new Date();

	private static final Date DATA_CONTATO_ALT =
			AngularXPTestUtil.adicionaDiasData(DATA_CONTATO, 5);

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
	
	@EJB
	private ContatoEAODelegate contatoEAO;

	@Test
	public void testSalvarContato(){
		
		final Integer CODIGO_OPERADORA = AngularXPTestUtil.getCodigoOperadora();
		final String NOME_OPERADORA = "NOME_OPERADORA_"+CODIGO_OPERADORA;
		
		final String NOME_CONTATO = "NOME_CONTATO_"+CODIGO_OPERADORA;
		final String TELEFONE_CONTATO = "FONE_"+CODIGO_OPERADORA;
		
		Operadora operadora = new Operadora();
		operadora.setCodigo(CODIGO_OPERADORA);
		operadora.setNome(NOME_OPERADORA);
		
		operadora = operadoraEAO.salvarOperadora(operadora);
		
		Contato contato = new Contato();
		contato.setNome(NOME_CONTATO);
		contato.setTelefone(TELEFONE_CONTATO);
		contato.setData(DATA_CONTATO);
		contato.setOperadora(operadora);
		
		Contato contatoSalvo = contatoEAO.salvarContato(contato);
		
		assertNotNull(contatoSalvo);
		assertNotNull(contatoSalvo.getId());
		assertEquals(NOME_CONTATO, contatoSalvo.getNome());
		assertEquals(TELEFONE_CONTATO, contatoSalvo.getTelefone());
		assertEquals(0, DATA_CONTATO.compareTo(contatoSalvo.getData()));
		
		assertNotNull(contatoSalvo.getOperadora());
		assertEquals(operadora.getId(), contatoSalvo.getOperadora().getId());
		
	}

	@Test
	public void testAlterarContato(){
		
		final Integer CODIGO_OPERADORA = AngularXPTestUtil.getCodigoOperadora();
		final String NOME_OPERADORA = "NOME_OPERADORA_"+CODIGO_OPERADORA;
		
		final Integer CODIGO_OPERADORA_2 = AngularXPTestUtil.getCodigoOperadora();
		final String NOME_OPERADORA_2 = "NOME_OPERADORA_"+CODIGO_OPERADORA_2;
		
		final String NOME_CONTATO = "NOME_CONTATO_"+CODIGO_OPERADORA;
		final String TELEFONE_CONTATO = "FONE_"+CODIGO_OPERADORA;
		
		final String NOME_CONTATO_ALT = "NOME_CONTATO_ALT_"+CODIGO_OPERADORA;
		final String TELEFONE_CONTATO_ALT = "FONE_ALT_"+CODIGO_OPERADORA;
		
		Operadora operadora = new Operadora();
		operadora.setCodigo(CODIGO_OPERADORA);
		operadora.setNome(NOME_OPERADORA);
		
		operadora = operadoraEAO.salvarOperadora(operadora);
		
		Contato contato = new Contato();
		contato.setNome(NOME_CONTATO);
		contato.setTelefone(TELEFONE_CONTATO);
		contato.setData(DATA_CONTATO);
		contato.setOperadora(operadora);
		
		Contato contatoSalvo = contatoEAO.salvarContato(contato);
		
		assertNotNull(contatoSalvo);
		assertNotNull(contatoSalvo.getId());
		assertEquals(NOME_CONTATO, contatoSalvo.getNome());
		assertEquals(TELEFONE_CONTATO, contatoSalvo.getTelefone());
		assertEquals(0, DATA_CONTATO.compareTo(contatoSalvo.getData()));
		
		assertNotNull(contatoSalvo.getOperadora());
		assertEquals(operadora.getId(), contatoSalvo.getOperadora().getId());
		
		// ALTERAÇÃO ===================================
		
		Operadora operadora2 = new Operadora();
		operadora2.setCodigo(CODIGO_OPERADORA_2);
		operadora2.setNome(NOME_OPERADORA_2);
		
		operadora2 = operadoraEAO.salvarOperadora(operadora2);
		
		contatoSalvo.setNome(NOME_CONTATO_ALT);
		contatoSalvo.setTelefone(TELEFONE_CONTATO_ALT);
		contatoSalvo.setData(DATA_CONTATO_ALT);
		contatoSalvo.setOperadora(operadora2);
		
		Contato contatoAlterado = contatoEAO.salvarContato(contatoSalvo);
		
		assertNotNull(contatoAlterado);
		assertNotNull(contatoAlterado.getId());
		assertEquals(NOME_CONTATO_ALT, contatoAlterado.getNome());
		assertEquals(TELEFONE_CONTATO_ALT, contatoAlterado.getTelefone());
		assertEquals(0, DATA_CONTATO_ALT.compareTo(contatoAlterado.getData()));
		
		assertNotNull(contatoAlterado.getOperadora());
		assertEquals(operadora2.getId(), contatoAlterado.getOperadora().getId());
		
	}

	@Test
	public void testConsultarContatoPorId(){
		
		final Integer CODIGO_OPERADORA = AngularXPTestUtil.getCodigoOperadora();
		final String NOME_OPERADORA = "NOME_OPERADORA_"+CODIGO_OPERADORA;
		
		final String NOME_CONTATO = "NOME_CONTATO_"+CODIGO_OPERADORA;
		final String TELEFONE_CONTATO = "FONE_"+CODIGO_OPERADORA;
		
		Operadora operadora = new Operadora();
		operadora.setCodigo(CODIGO_OPERADORA);
		operadora.setNome(NOME_OPERADORA);
		
		operadora = operadoraEAO.salvarOperadora(operadora);
		
		Contato contato = new Contato();
		contato.setNome(NOME_CONTATO);
		contato.setTelefone(TELEFONE_CONTATO);
		contato.setData(DATA_CONTATO);
		contato.setOperadora(operadora);
		
		Contato contatoSalvo = contatoEAO.salvarContato(contato);
		
		assertNotNull(contatoSalvo);
		assertNotNull(contatoSalvo.getId());
		
		Contato contatoConsultdo = contatoEAO.consultarContatoPorId(contatoSalvo.getId());
		
		assertNotNull(contatoConsultdo);
		assertNotNull(contatoConsultdo.getId());
		assertEquals(NOME_CONTATO, contatoConsultdo.getNome());
		assertEquals(TELEFONE_CONTATO, contatoConsultdo.getTelefone());
		assertEquals(0, DATA_CONTATO.compareTo(contatoConsultdo.getData()));
		
		assertNotNull(contatoConsultdo.getOperadora());
		assertEquals(operadora.getId(), contatoConsultdo.getOperadora().getId());
		
	}

	@Test
	public void testConsultarContatoPorNome(){
		
		final Integer CODIGO_OPERADORA = AngularXPTestUtil.getCodigoOperadora();
		final String NOME_OPERADORA = "NOME_OPERADORA_"+CODIGO_OPERADORA;
		
		final String NOME_CONTATO = "TEST_NOME_CONTATO_"+CODIGO_OPERADORA;
		final String TELEFONE_CONTATO = "FONE_"+CODIGO_OPERADORA;
		
		Operadora operadora = new Operadora();
		operadora.setCodigo(CODIGO_OPERADORA);
		operadora.setNome(NOME_OPERADORA);
		
		operadora = operadoraEAO.salvarOperadora(operadora);
		
		Contato contato = new Contato();
		contato.setNome(NOME_CONTATO);
		contato.setTelefone(TELEFONE_CONTATO);
		contato.setData(DATA_CONTATO);
		contato.setOperadora(operadora);
		
		Contato contatoSalvo = contatoEAO.salvarContato(contato);
		
		assertNotNull(contatoSalvo);
		
		Collection<Contato> contatosConsultdos = contatoEAO.consultarContatosPorNome("TEST_");
		
		assertNotNull(contatosConsultdos);
		assertEquals(1, contatosConsultdos.size());
		
	}

	@Test
	public void testDeletarContato(){
		
		final Integer CODIGO_OPERADORA = AngularXPTestUtil.getCodigoOperadora();
		final String NOME_OPERADORA = "NOME_OPERADORA_"+CODIGO_OPERADORA;
		
		final String NOME_CONTATO = "NOME_CONTATO_"+CODIGO_OPERADORA;
		final String TELEFONE_CONTATO = "FONE_"+CODIGO_OPERADORA;
		
		Operadora operadora = new Operadora();
		operadora.setCodigo(CODIGO_OPERADORA);
		operadora.setNome(NOME_OPERADORA);
		
		operadora = operadoraEAO.salvarOperadora(operadora);
		
		Contato contato = new Contato();
		contato.setNome(NOME_CONTATO);
		contato.setTelefone(TELEFONE_CONTATO);
		contato.setData(DATA_CONTATO);
		contato.setOperadora(operadora);
		
		Contato contatoSalvo = contatoEAO.salvarContato(contato);
		
		assertNotNull(contatoSalvo);
		assertNotNull(contatoSalvo.getId());
		
		contatoEAO.deletarContato(contatoSalvo);
		
		Contato contatoConsultado = contatoEAO
				.consultarContatoPorId(contatoSalvo.getId());
		
		assertNotNull(contatoConsultado);
		
	}
	
}
