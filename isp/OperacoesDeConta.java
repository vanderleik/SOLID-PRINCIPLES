package isp;

public interface OperacoesDeConta {

    void depositar(double valor);
    void sacar(double valor);
    void transferir(double valor, String contaDestino);
    
}
