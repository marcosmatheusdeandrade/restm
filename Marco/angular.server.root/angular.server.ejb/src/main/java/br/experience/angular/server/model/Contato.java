package br.experience.angular.server.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name=Contato.TABELA)
@SequenceGenerator(name=Contato.SEQUENCE_ID)
public class Contato implements Serializable {

	private static final long serialVersionUID = 3903166938014658909L;
	
	public static final String TABELA = "contato";
	
	public static final String SEQUENCE_ID = "seq_id_contato";
	
	private static final String ATRIBUTO_ID 			= "con_id";
	private static final String ATRIBUTO_NOME 			= "con_nome";
	private static final String ATRIBUTO_TELEFONE 		= "con_telefone";
	private static final String ATRIBUTO_DATA 			= "con_data";
	private static final String ATRIBUTO_OPERADORA 		= "con_operadora";
	
	@Id
	@GeneratedValue(generator = SEQUENCE_ID)
	@GenericGenerator(
			name = SEQUENCE_ID, 
			strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {
				@Parameter(name = "sequence_name", value = SEQUENCE_ID),
				@Parameter(name = "optimizer", value = "hilo"),
				@Parameter(name = "increment_size", value = "1")
			})
	@Column(name=ATRIBUTO_ID)
	private Integer id;
	
	@Column(name=ATRIBUTO_NOME)
	private String nome;
	
	@Column(name=ATRIBUTO_TELEFONE)
	private String telefone;
	
	@Temporal(TemporalType.DATE)
	@Column(name=ATRIBUTO_DATA)
	private Date data;
	
	@ManyToOne(optional=false)
	@JoinColumn(name=ATRIBUTO_OPERADORA, nullable=false)
	private Operadora operadora;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Operadora getOperadora() {
		return operadora;
	}

	public void setOperadora(Operadora operadora) {
		this.operadora = operadora;
	}

}
