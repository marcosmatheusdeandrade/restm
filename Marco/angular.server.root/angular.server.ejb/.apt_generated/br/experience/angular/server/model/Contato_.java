package br.experience.angular.server.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-04-08T16:22:50.121-0300")
@StaticMetamodel(Contato.class)
public class Contato_ {
	public static volatile SingularAttribute<Contato, Integer> id;
	public static volatile SingularAttribute<Contato, String> nome;
	public static volatile SingularAttribute<Contato, String> telefone;
	public static volatile SingularAttribute<Contato, Date> data;
	public static volatile SingularAttribute<Contato, Operadora> operadora;
}
