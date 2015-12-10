package managedBeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dao.ClienteDao;
import dao.ClienteDaoImplementation;
import dao.ContratoDao;
import dao.ContratoDaoImplementation;
import dao.MensalidadeDao;
import dao.MensalidadeDaoImplementation;
import beans.Cliente;
import beans.Contrato;
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
	System.out.println("Mensalidades");
	lista=new ArrayList<Notificacao>();
	MensalidadeDao md=new MensalidadeDaoImplementation();
	List<Mensalidade>mens=new ArrayList<Mensalidade>();
	Cliente c=null;
	ClienteDao cd=new ClienteDaoImplementation();
	ContratoDao cod=new ContratoDaoImplementation();
	mens=md.listar("ATRASADO");
	for(Mensalidade m:mens){
		informacaoNova=new Notificacao();
		informacaoNova.setTipo("Importante");
		informacaoNova.setTitulo("Mensalidades");
		String vencimento=new SimpleDateFormat("dd/MM/yyyy").format(m.getDataVencimento());
		informacaoNova.setDescricao("Mensalidade em atraso! Contrato: "+m.getContrato()+" - Vencimento: "+vencimento);
		lista.add(informacaoNova);
	}System.out.println("Carnes");
	List<Mensalidade>carnes=new ArrayList<Mensalidade>();
	carnes=md.listarCarnes();System.out.println("Listar carnês");
	if(!carnes.isEmpty()){
	for(Mensalidade mg:carnes){ 
		informacaoNova=new Notificacao();
		c=null;
		Contrato contrato=null;
		c=cd.buscar(mg.getContrato());System.out.println("Buscar Cliente");
		
		contrato=cod.buscar(mg.getContrato());System.out.println("Buscar Contrato");
		if(c==null&&contrato==null){			
			md.excluirMensalGerada(mg.getContrato());
			System.out.println("Remover geracarne");
		}else if(c==null&&contrato!=null){
			informacaoNova.setDescricao("Contrato não possui cliente, Para gerar mensalidades o cliente deve ser cadastrado, Contrato: "+mg.getContrato());
		}else if(c!=null&&contrato==null){
			if(c.getCpfok()==0){
				informacaoNova.setDescricao("CPF inválido! Atualize o CPF e cadastre o CONTRATO para gerar o carnê! Contrato: "+c.getNumeroContrato());
			}else{
				informacaoNova.setDescricao("Cadastre o CONTRATO para gerar o carnê! Contrato: "+c.getNumeroContrato());
			}
			
		}else if(c!=null&&contrato!=null){
			if(c.getCpfok()==0){
				informacaoNova.setDescricao("CPF inválido! Atualize o CPF para gerar o carnê! Contrato: "+c.getNumeroContrato());	
			}
		}
		informacaoNova.setTipo("Importante");
		informacaoNova.setTitulo("Carnê");
		lista.add(informacaoNova);
		
		
	}}
	/*List<Mensalidade>cnrs=new ArrayList<Mensalidade>();
	cnrs=md.listarSemCNR();
	for(Mensalidade cnr:cnrs){
		informacaoNova=new Notificacao();
		informacaoNova.setTipo("Importante");
		informacaoNova.setTitulo("CNRs");
		informacaoNova.setDescricao("A mensalidade do contrato: "+cnr.getContrato()+" não possui número de CNR");
		lista.add(informacaoNova);
	}*/
	return lista;
}
public void adicionar(){
	
}
public void excluir(){
	
}

}
