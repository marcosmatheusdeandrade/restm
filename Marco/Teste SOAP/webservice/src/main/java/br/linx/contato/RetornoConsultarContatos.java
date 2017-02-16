package br.linx.contato;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.linx.enums.ETipoRetorno;

@XmlAccessorType(XmlAccessType.FIELD)
public class RetornoConsultarContatos {
	
	@XmlElement(required=true, nillable=false)
	private ETipoRetorno tipoRetorno;
	
	@XmlElement(required=true, nillable=false)
	private ContatosTO contatosTO;

	public ETipoRetorno getTipoRetorno() {
		return tipoRetorno;
	}

	public void setTipoRetorno(ETipoRetorno tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}

	public ContatosTO getContatosTO() {
		return contatosTO;
	}

	public void setContatosTO(ContatosTO contatosTO) {
		this.contatosTO = contatosTO;
	}
}
