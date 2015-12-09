package controle;

import java.util.ArrayList;
import java.util.List;

import dao.ClienteDao;
import dao.ClienteDaoImplementation;
import dao.MensalidadeDao;
import dao.MensalidadeDaoImplementation;
import beans.Cliente;
import beans.Mensalidade;

public class Teste {
	
	public static void main(String[]args){
		validaCPF();
	}
public static void validaCPF(){
	ClienteDao cd=new ClienteDaoImplementation();
	Cliente cliente=new Cliente();
	List<Cliente>clientes=new ArrayList<Cliente>();
	List<Mensalidade>mensalidades=new ArrayList<Mensalidade>();
	MensalidadeDao md=new MensalidadeDaoImplementation();
	mensalidades=md.listarCarnes();
	for(Mensalidade m:mensalidades){
		cliente=cd.buscar(m.getContrato());
		if(cliente!=null){
			
			clientes.add(cliente);
			cliente=new Cliente();
		}
	}
	for(Cliente c:clientes){
		if(ValidaCPF.valida(c)){
			c.setCpfok(1);
			cd.atualizar(c);
			System.out.println("Cliente OK: "+c.toString());
		}else{
			System.out.println("Cliente com CPF inválido: "+c.toString());
		}
	}
	
	/*cliente=cd.buscar(50312);
	if(ValidaCPF.valida(cliente)){
		System.out.println(cliente.toString());
		
	}*/
}
}
