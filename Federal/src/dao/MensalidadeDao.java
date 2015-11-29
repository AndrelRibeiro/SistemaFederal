package dao;

import java.util.Date;
import java.util.List;

import beans.Mensalidade;
import beans.Registro;

public interface MensalidadeDao {
public boolean adicionar(Mensalidade m);
public boolean excluir(Mensalidade m);
public boolean alterar(Mensalidade m);
public Mensalidade buscar(Mensalidade m);
public List<Mensalidade> listar();
public List<Mensalidade> listar(String situacao);
public List<Mensalidade> listar(int contrato);
public Mensalidade buscar(String nossoNumero);
public boolean geraCarne(Mensalidade m);
public List<Mensalidade> buscar(Date inicio, Date fim, String situacao);
public List<Mensalidade> listarCarnes();
public List<Mensalidade> listarSemCNR();
Mensalidade buscaUltimoPgm(int contrato);
boolean adicionarPagas(Mensalidade m);
public List<Registro> gerarRemessa();
boolean excluirMensalGerada(Mensalidade m);
List<Mensalidade> ImprimirCarnes();
int alteraGeraCarne(Mensalidade m);
List<Mensalidade> imprimirEtiquetas();
}
