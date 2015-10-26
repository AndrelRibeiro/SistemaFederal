package controle;

import java.util.Date;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import beans.Mensalidade;

public class GeraBoletos {

	public void geraBoleto(List<Mensalidade> mensalidades) {

		try {
			JasperReport report = JasperCompileManager
					.compileReport("impressos/boletos.jrxml");
			JasperPrint print = JasperFillManager.fillReport(report, null,
					new JRBeanCollectionDataSource(mensalidades));
			JasperExportManager.exportReportToPdfFile(print,
					"C:/Federal impressos/Carne/carne" + new Date().getTime()
							+ ".pdf");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
