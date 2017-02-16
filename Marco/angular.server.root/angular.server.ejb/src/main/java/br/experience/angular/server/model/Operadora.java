package br.experience.angular.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name=Operadora.TABELA)
@SequenceGenerator(name=Operadora.SEQUENCE_ID)
public class Operadora implements Serializable {

	private static final long serialVersionUID = -7324861438407804021L;
	
	public static final String TABELA = "operadora";
	
	public static final String SEQUENCE_ID = "seq_id_operadora";
	
	private static final String ATRIBUTO_ID 		= "ope_id";
	private static final String ATRIBUTO_NOME 		= "ope_nome";
	private static final String ATRIBUTO_CODIGO 	= "ope_codigo";
	
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
	@Column(name=ATRIBUTO_ID, nullable=false, unique=true)
	private Integer id;
	
	@Column(name=ATRIBUTO_NOME, nullable=false, unique=true)
	private String nome;
	
	@Column(name=ATRIBUTO_CODIGO, nullable=false, unique=true)
	private Integer codigo;
	
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

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

}
