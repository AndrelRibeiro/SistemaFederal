package dao;

public interface NossoNumeroDao {

	boolean adicionar(int nossoNumero);

	boolean alterar(int nossoNumero);
 
	boolean excluir(int nossoNumero);

	int buscar();

}
