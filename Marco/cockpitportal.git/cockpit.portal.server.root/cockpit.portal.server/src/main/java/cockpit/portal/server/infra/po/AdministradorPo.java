package cockpit.portal.server.infra.po;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import cockpit.portal.server.infra.enums.ENivelAcesso;

@Entity
@Table(name=AdministradorPo.TABELA)
@AttributeOverrides({
	@AttributeOverride(name = "nome", column = @Column(name = AdministradorPo.FIELD_NOME)),
	@AttributeOverride(name = "login", column = @Column(name = AdministradorPo.FIELD_LOGIN, nullable = false)),
	@AttributeOverride(name = "senha", column = @Column(name = AdministradorPo.FIELD_SENHA, nullable = false)),
	@AttributeOverride(name = "email", column = @Column(name = AdministradorPo.FIELD_EMAIL, nullable = false)),
	@AttributeOverride(name = "dataCadastro", column = @Column(name = AdministradorPo.FIELD_DATA_CADASTRO, nullable = false))
})
public class AdministradorPo extends UsuarioPo implements Serializable {

	private static final long serialVersionUID = 551436169958322656L;
	
	static final String TABELA 								= "portal_administrador";
    
    static final String SEQUENCIA_ID_ADMINISTRADOR 			= "sequencia_id_administrador";
    
    private static final String FIELD_ID 					= "p_adm_id";
    private static final String FIELD_NIVEL_ACESSO			= "p_adm_nivel_acesso";
	                                                             
    static final String FIELD_NOME 							= "p_adm_nome";
    static final String FIELD_LOGIN 						= "p_adm_login";
    static final String FIELD_SENHA 						= "p_adm_senha";
    static final String FIELD_DATA_CADASTRO 				= "p_adm_data_cadastro";
    static final String FIELD_EMAIL 						= "p_adm_email";
    
    @Id
	@GeneratedValue(generator = SEQUENCIA_ID_ADMINISTRADOR)
	@GenericGenerator(
			name = SEQUENCIA_ID_ADMINISTRADOR, 
			strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {
				@Parameter(name = "sequence_name", value = SEQUENCIA_ID_ADMINISTRADOR),
				@Parameter(name = "optimizer", value = "hilo"),
				@Parameter(name = "increment_size", value = "1")
			})
	@Column(name=FIELD_ID, nullable=false, unique=true)
    private Integer id;
    
    @Column(name = FIELD_NIVEL_ACESSO, nullable = false)
    private ENivelAcesso nivelAcesso;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ENivelAcesso getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(ENivelAcesso nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}

}
