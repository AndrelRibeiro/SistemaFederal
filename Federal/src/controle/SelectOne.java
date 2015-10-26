package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
@ManagedBean(name="select")
@RequestScoped
public class SelectOne {
private List <String> classificacao=new ArrayList <String>();
private List<String> estadoCivil=new ArrayList<String>();
public SelectOne(){
	classificacao.add("Titular");
	classificacao.add("Filho(a)");
	classificacao.add("Enteado(a)");
	classificacao.add("Primo(a)");
	classificacao.add("Sobrinho(a)");
	classificacao.add("Tio(a)");
	classificacao.add("Avô(a)");
	classificacao.add("Amigo(a)");
	classificacao.add("Funcionario(a)");
		
	estadoCivil.add("Casado(a)");
	estadoCivil.add("Solteiro(a)");
	estadoCivil.add("Separado(a)");
	estadoCivil.add("Divorciado(a)");
	estadoCivil.add("Viuvo(a)");
	estadoCivil.add("Outros");
	
}
public List<String> getClassificacao() {
	return classificacao;
}
public void setClassificacao(List<String> classificacao) {
	this.classificacao = classificacao;
}
public List<String> getEstadoCivil() {
	return estadoCivil;
}
public void setEstadoCivil(List<String> estadoCivil) {
	this.estadoCivil = estadoCivil;
}

}
