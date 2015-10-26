package controle;

import java.util.ArrayList;
import java.util.List;

import dao.ClienteDao;
import dao.ClienteDaoImplementation;
import beans.Cliente;



public class ControleDocs {
public String CPF(Cliente cliente){
String cpf="";
String []cpfMod;
	cpf=cliente.getCpf();
	cpfMod=cpf.split("\\.|\\-|\\_|\\*|\\/|\\ |\\,");
	cpf="";
	for(int f=0;cpfMod.length>f;f++){
		
		cpf+=cpfMod[f];
	}
	return cpf;
}
public String RG(Cliente cliente){
	String rg="";
	
	return rg;
}
 
public static void main(String[]args){
	System.out.println("Chamando método");
	testes();
}
public static void testes(){
	ClienteDao cli=new ClienteDaoImplementation();
	List<Cliente>clientes=new ArrayList<Cliente>();
	ControleDocs cc=new ControleDocs();
	int t=0,cont=0,fora=0,branco=0,outro=0;
	clientes=cli.listarAtivos();
	for(Cliente c: clientes){
		
		String cpf=cc.CPF(c);
		if(cpf.length()<11&&cpf.length()>=1){
			
			System.out.println(cont+1+" ---------------------------------------------------------CPF fora do padrão: "+cpf);cont++;fora++;
			c.setCpf(null);
		}else if(cpf.length()==11){
			String novo=cpf.substring(0,3)+"."+cpf.substring(3, 6)+"."+cpf.substring(6, 9)+"-"+cpf.substring(9);
			System.out.println(cont+1+" CPF no padrão: "+cpf+"CPF corrigido: "+novo);cont++;
			c.setCpf(novo);
		}else if(cpf.length()==0){
			System.out.println(cont+1+" ----------------------------CPF em branco: "+cpf);cont++;branco++;
			c.setCpf(null);
		}else if(cpf.length()>11){
			
			System.out.println(cont+1+" --------CPF maior que o padrão: "+cpf);cont++;outro++;
			c.setCpf(null);
		}
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean retorno=cli.atualizar(c);
		if(retorno){
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++OK"+cont);
		}else{
			System.out.println("--------------------------------------------------------------------------------------------------------------------Erro"+cont);
		}
	}System.out.println(" Total de CPF menor que o padrão: "+fora);
	System.out.println(" Total de CPF em branco: "+branco);
	System.out.println(" Total de CPF no padrão: "+(cont-(fora+branco)));
	System.out.println(" Total de CPF: "+cont);
	System.out.println(" Total de Clientes "+clientes.size());
	System.out.println(" Total de CPF maior que o padrão "+outro);
	
}
	}
