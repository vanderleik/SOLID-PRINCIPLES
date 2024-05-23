package isp;

/**
 * Interface que define as operações bancárias que podem ser realizadas por um cliente.
 * A interface agrupa operações de diferentes naturezas (conta, empréstimo, investimento), tornando-a genérica demais. 
 */
public interface OperacoesBancarias {

    void depositar(double valor);
    void sacar(double valor);
    void transferir(double valor, String contaDestino);
    void solicitarEmprestimo(double valor, int parcelas);
    void investir(double valor, String tipoInvestimento);

}
