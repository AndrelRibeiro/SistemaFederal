package managedBeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dao.ClienteDao;
import dao.ClienteDaoImplementation;
import dao.MensalidadeDao;
import dao.MensalidadeDaoImplementation;
import beans.Cliente;
import beans.Notificacao;
import beans.Mensalidade;
@ManagedBean
@RequestScoped
public class InfoMB implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Notificacao informacaoNova=new Notificacao();
private List<Notificacao>lista;

public InfoMB(){
	
}

public Notificacao getInformacaoNova() {
	return informacaoNova;
}

public void setInformacaoNova(Notificacao informacaoNova) {
	this.informacaoNova = informacaoNova;
}

public List<Notificacao> getLista() {
	return lista;
}

public void setLista(List<Notificacao> lista) {
	this.lista = lista;
}

public List<Notificacao> listar(){
	lista=new ArrayList<Notificacao>();
	MensalidadeDao md=new MensalidadeDaoImplementation();
	List<Mensalidade>mens=new ArrayList<Mensalidade>();
	Cliente c=null;
	ClienteDao cd=new ClienteDaoImplementation();
	mens=md.listar("ATRASADO");
	for(Mensalidade m:mens){
		informacaoNova=new Notificacao();
		informacaoNova.setTipo("Importante");
		informacaoNova.setTitulo("Mensalidades");
		String vencimento=new SimpleDateFormat("dd/MM/yyyy").format(m.getDataVencimento());
		informacaoNova.setDescricao("Mensalidade em atraso! Contrato: "+m.getContrato()+" - Vencimento: "+vencimento);
		lista.add(informacaoNova);
	}
	List<Mensalidade>carnes=new ArrayList<Mensalidade>();
	carnes=md.listarCarnes();
	for(Mensalidade mg:carnes){ 
		informacaoNova=new Notificacao();
		c=new Cliente();
		c=cd.buscar(mg.getContrato());
		if(c==null){
			informacaoNova.setDescricao("Contrato n�o possui cliente, Para gerar mensalidades o cliente deve ser cadastrado, Contrato: "+mg.getContrato());
		}else{
		if(c.getCpfok()==0){
			informacaoNova.setDescricao("CPF inv�lido! Atualize o CPF para gerar o carn�! Contrato: "+c.getNumeroContrato());
		}else{
		informacaoNova.setDescricao("O carn� do contrato: "+mg.getContrato()+" "+mg.getSituacao());
		}	}	
		informacaoNova.setTipo("Importante");
		informacaoNova.setTitulo("Carn�");
		lista.add(informacaoNova);
		
	}
	/*List<Mensalidade>cnrs=new ArrayList<Mensalidade>();
	cnrs=md.listarSemCNR();
	for(Mensalidade cnr:cnrs){
		informacaoNova=new Notificacao();
		informacaoNova.setTipo("Importante");
		informacaoNova.setTitulo("CNRs");
		informacaoNova.setDescricao("A mensalidade do contrato: "+cnr.getContrato()+" n�o possui n�mero de CNR");
		lista.add(informacaoNova);
	}*/
	return lista;
}
public void adicionar(){
	
}
public void excluir(){
	
}

}
