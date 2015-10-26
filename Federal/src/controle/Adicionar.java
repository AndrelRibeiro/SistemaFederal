package controle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import beans.Beneficiario;
import beans.Mensalidade;
import dao.BeneficiarioDao;
import dao.BeneficiarioDaoImplementation;

public class Adicionar {

	public static void main(String[] args) {
		calculoData();

	}
	public static void bbuscar(){
		Beneficiario b=new Beneficiario();
		b.setContrato(6);
		List<Beneficiario>bens=new ArrayList<Beneficiario>();
		BeneficiarioDao bd=new BeneficiarioDaoImplementation();
		bens=bd.buscar(b.getContrato());
	}

	public static void calculoData(){
		Mensalidade m=new Mensalidade();
		Calendar c= Calendar.getInstance();
		m.setDataVencimento(c.getTime());
		c.add(Calendar.DAY_OF_MONTH, -1);
		m.setDataPagamento(c.getTime());
		System.out.println("Vencimento: "+m.getDataVencimento()+" Pagamento: "+m.getDataPagamento()+"Carência:"+m.getDataCarencia());
		CalculoDatas cc=new CalculoDatas();
		m=cc.carencia(m);
		if(m.getDataCarencia()==null){
		System.out.println("Vencimento: "+m.getDataVencimento()+" Pagamento: "+m.getDataPagamento()+"Carência: Pagamento em dia!");
		}else{
			System.out.println("Vencimento: "+m.getDataVencimento()+" Pagamento: "+m.getDataPagamento()+"Carência até: "+m.getDataCarencia());
		}
	}
}
