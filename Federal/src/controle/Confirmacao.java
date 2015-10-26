package controle;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
 
@ManagedBean
@RequestScoped
public class Confirmacao implements Serializable{
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public Confirmacao(){
	 
 }
    public void erro(String summary,String detail) {
        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
     
    public void message(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}