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
	if(c.getCpf().equalsIgnoreCase("")||c.getCpf()==null||c.getCpf().isEmpty()||c.getCpf().length()<11){
		valida=false;
	}else{
		String cpf[]=c.getCpf().split("\\.|\\-|\\_|\\*|\\/|\\ |\\,|\\+");
		String cpfVal[]=new String[11];
		
		for(int z=0;z<cpf.length;z++){
			cpfv+=cpf[z];
		}
		if(cpfv.length()==11&&!cpfv.equalsIgnoreCase("00000000000")&&!cpfv.equalsIgnoreCase("11111111111")&&!cpfv.equalsIgnoreCase("22222222222")&&!cpfv.equalsIgnoreCase("33333333333")&&!cpfv.equalsIgnoreCase("44444444444")&&!cpfv.equalsIgnoreCase("55555555555")&&!cpfv.equalsIgnoreCase("66666666666")&&!cpfv.equalsIgnoreCase("77777777777")&&!cpfv.equalsIgnoreCase("88888888888")&&!cpfv.equalsIgnoreCase("99999999999")){
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
	boolean retorno=false;
	clientes=cd.listarAtivos();
	Cliente cliente;
	List<Cliente> clientesvalidos=new ArrayList<Cliente>();
			
	for(int i=0;clientes.size()>i;i++){
		valido=valida(clientes.get(i));
		if(valido){
			cliente=new Cliente();
			cliente=clientes.get(i);
			cliente.setCpfok(1);
			retorno=cd.atualizar(cliente);
			clientesvalidos.add(clientes.get(i));
			System.out.println("CPF válido: "+cliente.getCpf());
		}else{
			cliente=new Cliente();
			cliente=clientes.get(i);
			cliente.setCpfok(0);
			retorno=cd.atualizar(cliente);
			System.out.println("CPF inválido: "+cliente.getCpf());
		}
		if(retorno){
			System.out.println("Cliente atualizado: "+cliente.toString());
		}else{
			System.out.println("Cliente não atualizado: "+cliente.toString());
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
