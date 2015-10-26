package controle;

import java.util.ArrayList;
import java.util.List;

import dao.ClienteDao;
import dao.ClienteDaoImplementation;
import beans.Cliente;

public class ValidaCPF {
	
	
	public ValidaCPF(){
		
	}
public static boolean valida(Cliente c){
	boolean valida=false;
	String cpfv="";
	int valor=0,f=0;
	//System.out.println(c.getCpf());
	if(c.getCpf()==null||c.getCpf().isEmpty()||c.getCpf().length()<11){
		
	}else{
		String cpf[]=c.getCpf().split("\\.|\\-|\\_|\\*|\\/|\\ |\\,|\\+");
		String cpfVal[]=new String[11];
		
		for(int z=0;z<cpf.length;z++){
			cpfv+=cpf[z];
		}
		if(cpfv.length()==11){
		for(int a=0;a<cpfv.length();a++){
			cpfVal[a]=cpfv.charAt(a)+"";
		}
		for(int i=10;i>=2;i--){
			int v=Integer.parseInt(cpfVal[f]);
		valor+=v*i;
		f++;
		}
		int valido=11-(valor%11);
		if(valido==10||valido==11){
			valido=0;
		}
		int s=Integer.parseInt(cpfVal[9]);
		if(valido==s){
			f=0;valor=0;
			for(int i=11;i>=2;i--){
				int v2=Integer.parseInt(cpfVal[f]);
			valor+=v2*i;
			f++;
			}
			int valido2=11-(valor%11);
			if(valido2==10||valido2==11){
				valido2=0;
			}
			int s2=Integer.parseInt(cpfVal[10]);
			if(valido2==s2){
				//System.out.println("CPF válido!");
				valida=true;
			}else{
				//System.out.println("CPF inválido!Erro de validação 2");
				valida=false;
			}
			
		}else{
			//System.out.println("CPF inválido! Erro de validação 1");
			valida=false;
		}
	}
		}
	return valida;
}
public static void main(String[]args){
	ClienteDao cd=new ClienteDaoImplementation();
	List<Cliente>clientes=new ArrayList<Cliente>();
	boolean valido=false;
	clientes=cd.listarAtivos();
	List<Cliente> clientesvalidos=new ArrayList<Cliente>();
			
	for(int i=0;clientes.size()>i;i++){
		valido=valida(clientes.get(i));
		if(valido){
			clientesvalidos.add(clientes.get(i));
		}
	}
	/*int clientesvalidos[]=new int[clientes.size()];System.out.println("Lista de clientes: "+clientes.size());
	for(int i=0;clientes.size()>i;i++){
		clientesvalidos[i]=valida(clientes.get(i));
		System.out.println(clientesvalidos[i]+"Cliente : "+i);
		
	}
	for(int contrato :clientesvalidos){
		System.out.println(contrato);
	}*/
	
}
}
