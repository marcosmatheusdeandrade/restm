package cockpit.portal.server.infra.po;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name=ResponsavelPo.TABELA)
@AttributeOverrides({
	@AttributeOverride(name = "nome", column = @Column(name = ResponsavelPo.FIELD_NOME)),
	@AttributeOverride(name = "login", column = @Column(name = ResponsavelPo.FIELD_LOGIN, nullable = false)),
	@AttributeOverride(name = "senha", column = @Column(name = ResponsavelPo.FIELD_SENHA, nullable = false)),
	@AttributeOverride(name = "email", column = @Column(name = ResponsavelPo.FIELD_EMAIL, nullable = false)),
	@AttributeOverride(name = "dataCadastro", column = @Column(name = ResponsavelPo.FIELD_DATA_CADASTRO, nullable = false))
})
public class ResponsavelPo extends UsuarioPo implements Serializable {

	private static final long serialVersionUID = -3387011346022345154L;
	
	
    static final String TABELA 								= "portal_responsavel";
    
    static final String SEQUENCIA_ID_RESPONSAVEL 			= "sequencia_id_responsavel";
    
    static final String FIELD_ID 					= "p_resp_id";
    private static final String FIELD_QUANTIDADE_CODIGOS_ADQUIRIDOS_DIRETOR 
    														= "p_resp_quantidade_codigos_adquiridos_diretor";
    private static final String FIELD_QUANTIDADE_CODIGOS_ADQUIRIDOS_VENDEDOR 
    														= "p_resp_quantidade_codigos_adquiridos_vendedor";
    private static final String FIELD_CPF					= "p_resp_cpf";
    
    static final String FIELD_NOME 							= "p_resp_nome";
    static final String FIELD_LOGIN 						= "p_resp_login";
    static final String FIELD_SENHA 						= "p_resp_senha";
    static final String FIELD_DATA_CADASTRO 				= "p_resp_data_cadastro";
    static final String FIELD_EMAIL							= "p_resp_email";
    
    
    @Id
	@GeneratedValue(generator = SEQUENCIA_ID_RESPONSAVEL)
	@GenericGenerator(
			name = SEQUENCIA_ID_RESPONSAVEL, 
			strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {
				@Parameter(name = "sequence_name", value = SEQUENCIA_ID_RESPONSAVEL),
				@Parameter(name = "optimizer", value = "hilo"),
				@Parameter(name = "increment_size", value = "1")
			})
	@Column(name=FIELD_ID, nullable=false, unique=true)
    private Integer id;
    
    @Column(name = FIELD_QUANTIDADE_CODIGOS_ADQUIRIDOS_DIRETOR, nullable=false)
    private Integer quantidadeCodigosAdquiridosDiretor;
    
    @Column(name = FIELD_QUANTIDADE_CODIGOS_ADQUIRIDOS_VENDEDOR, nullable=false)
    private Integer quantidadeCodigosAdquiridosVendedor;
    
    @Column(name = FIELD_CPF, nullable = false, unique = true)
    private String cpf;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "responsavel")
    private Set<RedePo> redes = new LinkedHashSet<>();
    
	public Set<RedePo> getRedes() {
		return redes;
	}

	public void setRedes(Set<RedePo> redes) {
		this.redes = redes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantidadeCodigosAdquiridosDiretor() {
		return quantidadeCodigosAdquiridosDiretor;
	}

	public void setQuantidadeCodigosAdquiridosDiretor(Integer quantidadeCodigosAdquiridosDiretor) {
		this.quantidadeCodigosAdquiridosDiretor = quantidadeCodigosAdquiridosDiretor;
	}

	public Integer getQuantidadeCodigosAdquiridosVendedor() {
		return quantidadeCodigosAdquiridosVendedor;
	}

	public void setQuantidadeCodigosAdquiridosVendedor(Integer quantidadeCodigosAdquiridosVendedor) {
		this.quantidadeCodigosAdquiridosVendedor = quantidadeCodigosAdquiridosVendedor;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
