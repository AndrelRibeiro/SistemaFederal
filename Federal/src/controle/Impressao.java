package controle;


import beans.Cliente;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.Normalizer;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaTray;
import javax.swing.JOptionPane;

/**
 *
 * @author Andre
 */
  
public class Impressao{
     String impressora="";
     public Impressao(){
         
     }

    public String getImpressora() {
        return impressora;
    }

    public void setImpressora(String impressora) {
        this.impressora = impressora;
    }
     
    public void sendTextToPrinter( String jobName, List<Cliente> clientes) throws PrintException, InterruptedException {
 
        String texto="",printerName="";
        for(Cliente cliente:clientes){
        texto+="  "+cliente.getNome()+"\r\n  ENDERECO: "+cliente.getLogradouro()+"\r\n  BAIRRO: "+cliente.getBairro()+"\r\n  "+cliente.getCidade()+"          "+cliente.getEstado()+"\r\n"+"  CEP: "+cliente.getCep()+"\r\n Contrato: "+cliente.getNumeroContrato() +"\r\n \r\n \r\n \r\n";
 }        
        String NFD=Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto=NFD.replaceAll("[^\\p{ASCII}]", "");
        //System.out.println(texto);
//Attribute Set storage
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();	 
        //We're looking for Text-capable printers
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        System.out.println("---");
        //Return list of printers capable of printing Text
        PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
        //Impressoras imp=new Impressoras();
        //imp.listar(printService);
        //imp.setVisible(true);
        String impres[] = new String[printService.length];
    int f=0;
    String lista="";
    for(PrintService imp:printService){
        impres[f]=imp.getName();
        System.out.println("Impressora: "+imp.getName());
        lista+=imp.getName()+"\r\n";
        f++;
    }FacesContext.getCurrentInstance().addMessage("Impressoras", new FacesMessage(FacesMessage.SEVERITY_INFO,lista,null));
 	
         //Object opc= JOptionPane.showInputDialog(null, "Selecione a Impressora", "Impressoras", JOptionPane.QUESTION_MESSAGE ,null , impres, impres[0]);
        //System.out.println("Impressora Selecionada: "+opc.toString());
       // if(opc!=null){
           // printerName=opc.toString();
        printerName="";
         
        //Get the default printer
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
        //Display the print dialog with default printer selected
        pras.add(new JobName(jobName, null));
        pras.add(MediaTray.MAIN);

        PrintService service = null;
        if(!printerName.isEmpty()) {
            // Seleciona o serviço cujo nome foi passado pelo parametro printerName
            for(int i=0; i < printService.length; i++) {
                if(printService[i].getName().equalsIgnoreCase(printerName)) {
                    service = printService[i];
                    break;
                }
            }
        } else {
            // Se o nome da impressora não foi passado então abre a janela para o usuario escolher
            service = ServiceUI.printDialog(null, 50, 50, printService, defaultService, flavor, pras);
        }

        //If user selected "OK"...
        if (service != null) {
            //Create a print job
            DocPrintJob job = service.createPrintJob();
            Doc doc;
            //Create storage for attributes
            DocAttributeSet das = new HashDocAttributeSet();			
            //Create the print document from PDF file with default attributes
            doc = new SimpleDoc( newByteArrayInputStream(texto), flavor, das);

            //Try some times...
            boolean ok = false;
            int trials = 0;
            while (!ok && trials < 10) {
                //Print the job
                try {
                    job.print(doc, pras);
                    ok = true;
                } catch (PrintException ex) {
                   
                }
                //Wait a few seconds for job to spool
                Thread.sleep(2000);
                trials++;
            }
        }
         FacesContext.getCurrentInstance().addMessage("Informação", new FacesMessage(FacesMessage.SEVERITY_INFO,"Dados enviados para impressora,\r\n aguarde a impressão!",null));
     	
       // }
    }

    private static ByteArrayInputStream newByteArrayInputStream(String aText) {
        ByteArrayInputStream retorno = new ByteArrayInputStream(aText.getBytes());
        return retorno;  
    }

    private static InputStream newInputStream(String aText) {
        InputStream retorno =new ByteArrayInputStream(aText.getBytes());
        return retorno;
    }
}




