package controle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import beans.Mensalidade;

public class MensalDataModel extends LazyDataModel<Mensalidade> {

    private List<Mensalidade> datasource;

    public MensalDataModel(List<Mensalidade> datasource) {
        this.datasource = datasource;
    }

    @Override
    public Mensalidade getRowData(String rowKey) {
        for(Mensalidade men : datasource) {
            if(men.getNossoNumero().equals(rowKey))
                return men;
        }

        return null;
    }

    @Override
    public Object getRowKey(Mensalidade men) {
        return men.getNossoNumero();
    }

    public List<Mensalidade> load(int first, int pageSize, final String sortField, final SortOrder sortOrder, Map<String, Object> filters) {
        List<Mensalidade> data = new ArrayList<Mensalidade>();

        for(Mensalidade men : datasource) {
            boolean match = true;

            for(String filterProperty : filters.keySet()) {
                try {
                    String filterValue = (String)filters.get(filterProperty);
                    String fieldValue = String.valueOf(men.getClass().getField(filterProperty).get(men));

                    if(filterValue == null || fieldValue.startsWith(filterValue)) {
                        match = true;
                    }
                    else {
                        match = false;
                        break;
                    }
                } catch(Exception e) {
                    match = false;
                }
            }
            if(match) {
                data.add(men);
            }
        }

        //sort
        if(sortField != null) {
            Collections.sort(data, new Comparator<Mensalidade>() {
                @Override
                public int compare(Mensalidade men1, Mensalidade men2) {
                    Object value1 = null;
                    try {
                        value1 = Mensalidade.class.getField(sortField).get(men1);
                        Object value2 = Mensalidade.class.getField(sortField).get(men2);
                        int value = ((Comparable)value1).compareTo(value2);
                        return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
                    }
                    catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            );
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
}


