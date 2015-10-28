package managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import controle.Confirmacao;
import dao.CepDao;
import dao.CepDaoImplementation;
import dao.VendedorDao;
import dao.VendedorDaoImplementation;
import beans.Endereco;
import beans.Vendedor;
@ManagedBean
@ViewScoped
public class VendedorMB implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = -5447816115053594213L;
Vendedor vendedorNovo=new Vendedor();
Endereco endereco=new Endereco();
List<Vendedor>vendedores;
Confirmacao c = new Confirmacao();
public VendedorMB(){
	
}

public Endereco getEndereco() {
	return endereco;
}

public void setEndereco(Endereco endereco) {
	this.endereco = endereco;
}

public Vendedor getVendedorNovo() {
	return vendedorNovo;
}
public void setVendedorNovo(Vendedor vendedorNovo) {
	this.vendedorNovo = vendedorNovo;
}
public List<Vendedor> getVendedores() {
	return vendedores;
}
public void setVendedores(List<Vendedor> vendedores) {
	this.vendedores = vendedores;
}
public List<Vendedor> listar(){
vendedores=new ArrayList<Vendedor>();
VendedorDao vd=new VendedorDaoImplementation();
vendedores=vd.listar();
return vendedores;
}
public void adicionar(){
	VendedorDao vd=new VendedorDaoImplementation();
	vendedorNovo.setLogradouro(vendedorNovo.getLogradouro()+", "+vendedorNovo.getNumero());
	boolean retorno=vd.adicionar(vendedorNovo);System.out.println("VendedorMB"+vendedorNovo.toString());
	if(retorno){
		c.message("Vendedor adicionado com sucesso!", "Sucesso");
	}else{
		c.message("Erro ao adicionar vendedor","Erro");}
}
public void remover(){
	VendedorDao vd=new VendedorDaoImplementation();
	boolean retorno=vd.remover(vendedorNovo);
	if(retorno){
		c.message("Vendedor removido com sucesso!", "Sucesso");
	}else{
		c.message("Erro ao remover vendedor","Erro");}
}
public void buscar(){
	VendedorDao vd=new VendedorDaoImplementation();
	vendedorNovo=vd.buscar(vendedorNovo);
}
public void alterar(){
	VendedorDao vd=new VendedorDaoImplementation();
	boolean retorno=vd.adicionar(vendedorNovo);
}
public void addMessage(String mensagem) {
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem,  null);
    FacesContext.getCurrentInstance().addMessage(null, message);
}

	public void pesqEndereco() {
		
		if (endereco.getCep().isEmpty()||endereco.getCep().length()!=8) {
			FacesContext.getCurrentInstance().addMessage("Aviso", new FacesMessage(FacesMessage.SEVERITY_INFO,"Informe um cep válido",null));
		}else{
			// FacesContext.getCurrentInstance().addMessage("Aviso", new
			// FacesMessage(FacesMessage.SEVERITY_INFO,"Aguarde!",null));
			CepDao cep = new CepDaoImplementation();
			endereco = cep.buscar(endereco.getCep());
			vendedorNovo.setBairro(endereco.getBairro());
			vendedorNovo.setCep(endereco.getCep());
			vendedorNovo.setCidade(endereco.getCidade());
			vendedorNovo.setEstado(endereco.getEstado());
			vendedorNovo.setLogradouro(endereco.getLogradouro());
			if (endereco.getLogradouro() == null
					|| endereco.getLogradouro().equalsIgnoreCase("")) {
				FacesContext.getCurrentInstance().addMessage("Aviso",	new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe um cep válido", null));
			}
		}}
	}

