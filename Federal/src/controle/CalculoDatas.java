package controle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import beans.Mensalidade;

public class CalculoDatas {

	public Mensalidade carencia(Mensalidade mensalidade){
		int tempo=0;System.out.println(mensalidade.toString());
		tempo=comparaData(mensalidade.getDataVencimento(), mensalidade.getDataPagamento());System.out.println("calculando dias");
		
		if(mensalidade.getDataVencimento().before(mensalidade.getDataPagamento())){
			Calendar c=Calendar.getInstance();
			c.setTime(mensalidade.getDataPagamento());
			if(tempo>=1&&tempo<=10){
				c.add(Calendar.DAY_OF_MONTH, 5);	System.out.println("Atraso de até 10 dias!"+c.getTime());
			}else if(tempo>10&&tempo<=30){
				c.add(Calendar.DAY_OF_MONTH, 10);   System.out.println("Atraso de mais de 10 e menos de 30 dias!"+c.getTime());
			}else if(tempo>30&&tempo<=60){
				c.add(Calendar.DAY_OF_MONTH, 15);   System.out.println("Atraso de mais de 30 e menos de 60 dias!"+c.getTime());
			}else if(tempo>60&&tempo<=90){
				c.add(Calendar.DAY_OF_MONTH, 30);   System.out.println("Atraso de mais de 60 e menos de 90 dias!"+c.getTime());
			}else if(tempo>90){
				c.add(Calendar.DAY_OF_MONTH, 60);
				mensalidade.setSituacao("Cancelar");   System.out.println("Atraso de mais de 90 dias!"+c.getTime());
			}
			mensalidade.setDataCarencia(c.getTime());
		}else{
			System.out.println("Pagamento em dia!");
		}
		
		return mensalidade;
	}
	public boolean pagamentos(ArrayList<Mensalidade>mensalidades){
		boolean retorno=false;
		for(Mensalidade m : mensalidades){
			if(!m.getSituacao().equalsIgnoreCase("P")){
				retorno=false;
			}else{
				retorno=true;
			}
		}
		return retorno;
	}
	public Date calculaVencimento(Date ultimaParc,int parcelas){
		Calendar c=Calendar.getInstance();
		if(ultimaParc!=null){
			c.setTime(ultimaParc);
			if(parcelas==12){
				c.add(Calendar.MONTH, 1);	System.out.println("Pagamento Mensal!"+c.getTime());
			}else if(parcelas==6){
				c.add(Calendar.MONTH,2);   System.out.println("Pagamento Bimestral!"+c.getTime());
			}else if(parcelas==4){
				c.add(Calendar.MONTH,3);   System.out.println("Pagamento Trimestral!"+c.getTime());
			}
			ultimaParc=c.getTime();
		}
		return ultimaParc;
	}
	public int calculaIdade(Date nascimento){
		Calendar c = Calendar.getInstance();
		long difference = c.getTimeInMillis() - nascimento.getTime();
		int idade = (int) (difference / 1000 / 60 / 60 / 24 / 365);
		return idade;
	}

	public int comparaData(Date vencimento, Date pagamento){
		System.out.println("Pagamento: "+pagamento+"\nVencimento: "+vencimento);
		long difference = pagamento.getTime() - vencimento.getTime();
		int diferenca = (int) (difference / 1000 / 60 / 60 / 24);
		return diferenca;
	}
	
	public String converteSqlToString(java.sql.Date dataSql){
		String dt="";
		Date dataUtil;
		if(dataSql!=null){
			dataUtil=new Date(dataSql.getTime());
			dt=new SimpleDateFormat("dd/MM/yyyy").format(dataUtil);
		}else{
			dt="";
		}
		return dt;

	}
	public String converteUtilToString(Date dataUtil){
		String data="";
		if(dataUtil!=null){
			data=new SimpleDateFormat("dd/MM/yyyy").format(dataUtil);
		}
		return data;
	}
	public java.sql.Date converteStringToSQL(String dataString){
		java.sql.Date dataSQL=null;
		Date dataUtil=null;
		if(!dataString.equalsIgnoreCase(null)){
			try {
				dataUtil=new SimpleDateFormat("dd/MM/yyyy").parse(dataString);
				dataSQL=new java.sql.Date(dataUtil.getTime());
			} catch (ParseException e) {
				dataSQL=null;
				e.printStackTrace();
			}
		}
		return dataSQL;
	}
	public Date converteStringToDate(String dataString){
		Date dataUtil=null;
		if(!dataString.equalsIgnoreCase(null)){
			try {
				dataUtil=new SimpleDateFormat("dd/MM/yyyy").parse(dataString);
			}catch(ParseException e){
				e.printStackTrace();
			}
		}
		return dataUtil;
	}
}
