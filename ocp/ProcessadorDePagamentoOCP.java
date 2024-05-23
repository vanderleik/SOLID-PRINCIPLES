package ocp;

/**
 * ProcessadorDePagamentoOCP
 * Essa classe implementa o princípio do aberto/fechado, pois recebe a estratégia de processamento através do construtor, permitindo a extensão do comportamento sem modificar a classe original.
 * 
 */
public class ProcessadorDePagamentoOCP {
    
    private ProcessadorDePagamentoStrategy strategy;

    public ProcessadorDePagamentoOCP(ProcessadorDePagamentoStrategy strategy) {
        this.strategy = strategy;
    }

    public void processar(Pagamento pagamento) {
        strategy.processar(pagamento);
    }

}
