package controle;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Plano implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private boolean sepultamento;
private boolean crematorio;
private boolean urna;

	
	
	public Plano(){
		
	}

	public boolean isSepultamento() {
		return sepultamento;
	}

	public void setSepultamento(boolean sepultamento) {
		this.sepultamento = sepultamento;
	}

	public boolean isCrematorio() {
		return crematorio;
	}

	public void setCrematorio(boolean crematorio) {
		this.crematorio = crematorio;
	}

	public boolean isUrna() {
		return urna;
	}

	public void setUrna(boolean urna) {
		this.urna = urna;
	}

	public String buscaPlanos(String plano){
		if(!plano.isEmpty()){
		if(plano.equalsIgnoreCase("A")){
			plano="0";
			sepultamento=false;
			crematorio=false;
			urna=false;	
			plano="EXECUTIVO";
		}else if(plano.equalsIgnoreCase("B")){
			plano="1";
			sepultamento=false;
			crematorio=false;
			urna=false;
			plano="ESPECIAL";
		}else if(plano.equalsIgnoreCase("C")){
			plano="0";
			sepultamento=true;
			crematorio=false;
			urna=false;
			plano="EXECUTIVO + TAXA DE SEPULTAMENTO";
		}else if(plano.equalsIgnoreCase("D")){
			plano="0";
			sepultamento=false;
			crematorio=true;
			urna=false;
			plano="EXECUTIVO + CREMATÓRIO";
		}else if(plano.equalsIgnoreCase("E")){
			plano="0";
			sepultamento=false;
			crematorio=false;
			urna=true;
			plano="EXECUTIVO + URNA GORDA SUPER GORDA E COMPRIDA";
		}else if(plano.equalsIgnoreCase("F")){
			plano="0";
			sepultamento=true;
			crematorio=true;
			urna=false;
			plano="EXECUTIVO + TAXA DE SEPULTAMENTO + CREMATÓRIO";
		}else if(plano.equalsIgnoreCase("G")){
			plano="0";
			sepultamento=true;
			crematorio=false;
			urna=true;
			plano="EXECUTIVO + TAXA DE SEPULTAMENTO + URNA GORDA SUPER GORDA E COMPRIDA";
		}else if(plano.equalsIgnoreCase("H")){
			plano="0";
			sepultamento=false;
			crematorio=true;
			urna=true;
			plano="EXECUTIVO + CREMATÓRIO + URNA GORDA SUPER GORDA E COMPRIDA";
		}else if(plano.equalsIgnoreCase("I")){
			plano="1";
			sepultamento=true;
			crematorio=false;
			urna=false;
			plano="ESPECIAL + TAXA SEPULTAMENTO";
		}else if(plano.equalsIgnoreCase("J")){
			plano="1";
			sepultamento=false;
			crematorio=true;
			urna=false;
			plano="ESPECIAL + CREMATÓRIO";
		}else if(plano.equalsIgnoreCase("K")){
			plano="1";
			sepultamento=false;
			crematorio=false;
			urna=true;
			plano="ESPECIAL + URNA GORDA SUPER GORDA E COMPRIDA";
		}else if(plano.equalsIgnoreCase("L")){
			plano="1";
			sepultamento=true;
			crematorio=true;
			urna=false;
			plano="ESPECIAL + TAXA DE SEPULTAMENTO + CREMATÓRIO";
		}else if(plano.equalsIgnoreCase("M")){
			plano="1";
			sepultamento=true;
			crematorio=false;
			urna=true;
			plano="ESPECIAL + TAXA DE SEPULTAMENTO + URNA GORDA SUPER GORDA E COMPRIDA";
		}else if(plano.equalsIgnoreCase("N")){
			plano="1";
		sepultamento=false;
		crematorio=true;
		urna=true;
		plano="ESPECIAL + CREMATÓRIO + URNA GORDA SUPER GORDA E COMPRIDA";
	}}
		return plano;
		}
	public String configuraPlanos(String plano){
		if(plano.equalsIgnoreCase("0")&&sepultamento==false&&crematorio==false&&urna==false){
			plano="A";
		}else if(plano.equalsIgnoreCase("1")&&sepultamento==false&&crematorio==false&&urna==false){
			plano="B";
		}else if(plano.equalsIgnoreCase("0")&&sepultamento&&crematorio==false&&urna==false){
			plano="C";
		}else if(plano.equalsIgnoreCase("0")&&sepultamento==false&&crematorio&&urna==false){
			plano="D";
		}else if(plano.equalsIgnoreCase("0")&&sepultamento==false&&crematorio==false&&urna){
			plano="E";
		}else if(plano.equalsIgnoreCase("0")&&sepultamento&&crematorio&&urna==false){
			plano="F";
		}else if(plano.equalsIgnoreCase("0")&&sepultamento&&crematorio==false&&urna){
			plano="G";
		}else if(plano.equalsIgnoreCase("0")&&sepultamento==false&&crematorio&&urna){
			plano="H";
		}else if(plano.equalsIgnoreCase("1")&&sepultamento&&crematorio==false&&urna==false){
			plano="I";
		}else if(plano.equalsIgnoreCase("1")&&sepultamento==false&&crematorio&&urna==false){
			plano="J";
		}else if(plano.equalsIgnoreCase("1")&&sepultamento==false&&crematorio==false&&urna){
			plano="K";
		}else if(plano.equalsIgnoreCase("1")&&sepultamento&&crematorio&&urna==false){
			plano="L";
		}else if(plano.equalsIgnoreCase("1")&&sepultamento&&crematorio==false&&urna){
			plano="M";
		}else if(plano.equalsIgnoreCase("1")&&sepultamento==false&&crematorio&&urna){
			plano="N";
	}
		return plano;
		}
}
