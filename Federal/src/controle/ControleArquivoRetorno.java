package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import beans.Arquivo;
import beans.Registro;
@ManagedBean(name="cArquivo")
@RequestScoped
public class ControleArquivoRetorno implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String tipoRegHeader;//HEADER
private String codRet;//HEADER
private String agencia;//HEADER
private String conta;//HEADER
private String tipoRegTrailer;//TRAILER
private int quantidadeTitulos;//TRAILER
private String valorTotal;//TRAILER
private int cents;
public ControleArquivoRetorno(){
	
}

public String getTipoRegHeader() {
	return tipoRegHeader;
}

public void setTipoRegHeader(String tipoRegHeader) {
	this.tipoRegHeader = tipoRegHeader;
}

public String getCodRet() {
	return codRet;
}

public void setCodRet(String codRet) {
	this.codRet = codRet;
}

public String getAgencia() {
	return agencia;
}

public void setAgencia(String agencia) {
	this.agencia = agencia;
}

public String getConta() {
	return conta;
}

public void setConta(String conta) {
	this.conta = conta;
}

public String getTipoRegTrailer() {
	return tipoRegTrailer;
}

public void setTipoRegTrailer(String tipoRegTrailer) {
	this.tipoRegTrailer = tipoRegTrailer;
}

public int getQuantidadeTitulos() {
	return quantidadeTitulos;
}

public void setQuantidadeTitulos(int quantidadeTitulos) {
	this.quantidadeTitulos = quantidadeTitulos;
}

public String getValorTotal() {
	return valorTotal;
}

public void setValorTotal(String valorTotal) {
	this.valorTotal = valorTotal;
}

public int getCents() {
	return cents;
}

public void setCents(int cents) {
	this.cents = cents;
}

public List<Registro> trataDados(Arquivo arquivo){
	Registro registro=new Registro();
	List<Registro>registros=new ArrayList<Registro>();
	String []conteudo=arquivo.getConteudo();
	int linhas=conteudo.length-1;
	int busca=1;
	String header=conteudo[0];
	String trailer=conteudo[linhas];
	tipoRegHeader=header.substring(0, 1);System.out.println("Tipo de Registro: "+tipoRegHeader);
	codRet=header.substring(1, 2);System.out.println("Código de retorno: "+codRet);
	agencia=header.substring(26, 30);System.out.println("Agencia: "+agencia);
	conta=header.substring(32, 38);System.out.println("Conta: "+conta);
	if(tipoRegHeader.equalsIgnoreCase("0")&&codRet.equalsIgnoreCase("2")&&agencia.equalsIgnoreCase("7145")&&conta.equalsIgnoreCase("036799")){
		while(busca<linhas){
			String reg=conteudo[busca];
			registro.setTipoRegRegistro(reg.substring(0, 1));
			registro.setNossoNumero(reg.substring(62, 70));
			String codigo=reg.substring(108, 110);
			if(codigo.equalsIgnoreCase("06")){
				registro.setCodOcorrencia("Liquidação Normal");
				}else{
					registro.setCodOcorrencia("Verifique junto ao banco");
				}
			
			String data=reg.substring(110,112)+"/"+reg.substring(112,114)+"/20"+reg.substring(114,116);
			registro.setDataOcorrencia(data);
			
			int vr =Integer.parseInt(reg.substring(152, 165));
			String valor=""+vr;
			
			String valor1="R$"+valor.substring(0,valor.length()-2)+","+valor.substring(valor.length()-2,valor.length());
			registro.setValorTitulo(valor1);			
			registro.setCodLiquidacao(reg.substring(392, 394));			
			registro.setNumeroSequencial(Integer.parseInt(reg.substring(394, 400)));
			registros.add(registro);System.out.println(registro.toString());
			registro=new Registro();
			busca++;
		}
	}else{
		System.out.println("Erro de verificação de início de arquivo");
	}
	tipoRegTrailer=trailer.substring(0, 1);System.out.println("Tipo registro: "+tipoRegTrailer);
	if(tipoRegTrailer.equalsIgnoreCase("9")){
		
		quantidadeTitulos=Integer.parseInt(trailer.substring(212, 220));System.out.println("Quantidade de Registros: "+quantidadeTitulos);
		int valorT=Integer.parseInt(trailer.substring(220, 232));
		
		cents=Integer.parseInt(trailer.substring(232, 234));
		valorTotal="R$"+valorT+","+cents;
		System.out.println("Valor total dos titulos: "+valorTotal);
		if(quantidadeTitulos!=registros.size()){
			System.out.println("Erro de leitura de registros do arquivo, algum registro deixou de ser lido");
		}
	}else{
		System.out.println("Erro de verificação de final de arquivo");
	}
	
	return registros;
	
}
public void adicionaArquivo(){
	
}
public void buscaArquivos(){
	
}
public void deletaArquivo(){
	
}
}
