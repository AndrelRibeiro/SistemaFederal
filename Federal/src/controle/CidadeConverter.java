package controle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import beans.Cidade;
import dao.EstadoCidadeDao;
import dao.EstadoCidadeDaoImplementation;
@FacesConverter("cidadeConverter")
public class CidadeConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		 if(value != null && value.trim().length() > 0) {
			 EstadoCidadeDao ecd=new EstadoCidadeDaoImplementation();
	           
	            return ecd.buscarCidadePorId(Long.valueOf(value));
	        }
	        else {
	            return null;
	
	}
	}
	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object value) {
		if (value instanceof Cidade) {
			Cidade cidade = (Cidade) value;
			return String.valueOf(cidade.getId());
			}
			return "";
	}
}
