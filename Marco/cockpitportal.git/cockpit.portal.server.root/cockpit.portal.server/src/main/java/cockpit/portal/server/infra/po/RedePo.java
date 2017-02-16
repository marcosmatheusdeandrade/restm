package cockpit.portal.server.infra.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = RedePo.TABELA)
public class RedePo implements Serializable{

	private static final long serialVersionUID = 7645476302858089657L;
	
	static final String TABELA 								= "portal_rede";
	
	static final String SEQUENCIA_ID_REDE 					= "sequencia_id_rede";
	
	private static final String FIELD_ID					= "p_rede_id";
	private static final String FIELD_ID_ERP 				= "p_rede_erp";
	private static final String FIELD_DESCRICAO_REDE 		= "p_rede_descricao_rede";
	private static final String FIELD_ID_RESPONSAVEL		= "p_rede_id_responsavel";

    @Id
	@GeneratedValue(generator = SEQUENCIA_ID_REDE)
	@GenericGenerator(
			name = SEQUENCIA_ID_REDE, 
			strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {
				@Parameter(name = "sequence_name", value = SEQUENCIA_ID_REDE),
				@Parameter(name = "optimizer", value = "hilo"),
				@Parameter(name = "increment_size", value = "1")
			})
	@Column(name=FIELD_ID, nullable=false, unique=true)
	private Integer id;
	
    @JoinColumn(name = FIELD_ID_RESPONSAVEL, nullable = false)
	@ManyToOne(optional = false)
	private ResponsavelPo responsavel;	
    
    @Column(name = FIELD_DESCRICAO_REDE, nullable = false)
    private String descricaoRede;
    
    @Column(name = FIELD_ID_ERP, nullable = false, unique = true)
    private String idErp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ResponsavelPo getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(ResponsavelPo responsavel) {
		this.responsavel = responsavel;
	}

	public String getDescricaoRede() {
		return descricaoRede;
	}

	public void setDescricaoRede(String descricaoRede) {
		this.descricaoRede = descricaoRede;
	}

	public String getIdErp() {
		return idErp;
	}

	public void setIdErp(String idErp) {
		this.idErp = idErp;
	}
    
}
