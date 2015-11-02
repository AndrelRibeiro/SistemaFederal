package controle;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import managedBeans.MensalidadeMB;

import org.primefaces.event.FileUploadEvent;

import beans.Arquivo;
import beans.Mensalidade;
import beans.Registro;


@ManagedBean(name="upload")
@RequestScoped
public class FileUploadView implements Serializable{    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Arquivo arquivo=new Arquivo();
    List<Registro>registros=new ArrayList<Registro>();
    List<Mensalidade> mensalidades=new ArrayList<Mensalidade>();
    ControleArquivoRetorno ca=new ControleArquivoRetorno();
    ControleArquivoRemessa carem=new ControleArquivoRemessa();
    private String valorTotal;
    public FileUploadView(){
    	
    }
    
	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	public List<Registro> getRegistros() {
		return registros;
	}

	public void setRegistros(List<Registro> registros) {
		this.registros = registros;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		
		
		this.valorTotal = ca.getValorTotal();
	}

	public List<Mensalidade> getMensalidades() {
		return mensalidades;
	}

	public void setMensalidades(List<Mensalidade> mensalidades) {
		this.mensalidades = mensalidades;
	}


	public void fileUpload(FileUploadEvent event) throws IOException{  
    	try{ 
    		
		FacesMessage msg = new FacesMessage("Sucesso!", event.getFile().getFileName() + " foi carregado com sucesso!");  
         FacesContext.getCurrentInstance().addMessage(null, msg);  
         arquivo.setNome(event.getFile().getFileName());
         
         @SuppressWarnings("resource")
		String texto=new Scanner(event.getFile().getInputstream()).useDelimiter("\\A").next();
         String []linha=texto.split("\n");
         arquivo.setConteudo(linha);
         for(int i=0;i<linha.length;i++){
        	 System.out.println("Linha: "+i+" -->  "+linha[i]); 
         }
         System.out.println("Linhas= "+linha.length);
         
         
     System.out.println("Nome: "+arquivo.getNome()+"\n Conteúdo: "+texto);
     System.out.println("-----------------------------------------------------------------------------------------------------------------");
     ControleArquivoRetorno ca=new ControleArquivoRetorno();
     
     registros=ca.trataDados(arquivo);
     MensalidadeMB mensal=new MensalidadeMB();
     mensalidades=mensal.baixa(registros);
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    	
	}
    public List<Registro> remessa(){
    	registros=new ArrayList<Registro>();
    	ControleArquivoRemessa car=new ControleArquivoRemessa();
    	registros=car.geraArquivo();
    	int regs=0;
    	for(Registro r:registros){
    		String d=r.getVencimento().substring(0, 2)+"/"+r.getVencimento().substring(2, 4)+"/"+r.getVencimento().substring(4, 6);
    		r.setVencimento(d);
    		int t=r.getValorTitulo().length();
    		int t2=t-2;
    		String valor="R$"+r.getValorTitulo().substring(0, t2)+","+r.getValorTitulo().substring(t2, t);
    		r.setValorTitulo(valor);
    		regs++;
    		r.setNumeroSequencial(regs);	
    	}
    	
    	return registros;
    }
    
}
