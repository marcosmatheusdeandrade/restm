package br.linx.contato;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.linx.enums.ETipoRetorno;

@XmlAccessorType(XmlAccessType.FIELD)
public class RetornoInsercaoContato {

	@XmlElement(nillable=false, required=true)
	private ETipoRetorno tipoRetorno;

	public ETipoRetorno getTipoRetorno() {
		return tipoRetorno;
	}

	public void setTipoRetorno(ETipoRetorno tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}
}
