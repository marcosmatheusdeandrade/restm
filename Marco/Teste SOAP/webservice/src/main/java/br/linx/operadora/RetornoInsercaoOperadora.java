package br.linx.operadora;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import br.linx.enums.ETipoRetorno;

@XmlAccessorType(XmlAccessType.FIELD)
public class RetornoInsercaoOperadora {

	@XmlElement(nillable=true, required=true)
	private ETipoRetorno tipoRetorno;

	public ETipoRetorno getTipoRetorno() {
		return tipoRetorno;
	}

	public void setTipoRetorno(ETipoRetorno tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}
}
