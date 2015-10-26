package controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import beans.Estado;
import dao.EstadoCidadeDao;
import dao.EstadoCidadeDaoImplementation;
@FacesConverter("estadoConverter")
public class EstadoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && !value.equals("")) {
	           EstadoCidadeDao ecd=new EstadoCidadeDaoImplementation();
	           
	            return ecd.buscarEstadoPorId(Long.valueOf(value));}
		 else{
			 return null;
		 }
	
	
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object value) {
		if (value instanceof Estado) {
			Estado estado = (Estado) value;//System.out.println(String.valueOf(estado.getNome()));
			return String.valueOf(estado.getId());
			}
			return "";
	}

}
