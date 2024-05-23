package ocp;

/**
 * ProcessadorDePagamentoStrategy: classe aberta para extensão
 * Essa interface define um contrato para processar pagamentos.
 *
 */
public interface ProcessadorDePagamentoStrategy {

    void processar(Pagamento pagamento);    

}
