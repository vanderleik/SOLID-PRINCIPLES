package ocp;

/**
 * ProcessadorDePagamento
 * Essa classe viola o princípio do aberto/fechado, pois para adicionar um novo tipo de pagamento é necessário alterar o código fonte da classe.
 * 
 */
public class ProcessadorDePagamento {
   
    public void processar(Pagamento pagamento) {   
        if (pagamento.getTipo().equals("CartãoDeCrédito")) {
            // Lógica para processar pagamento com cartão de crédito
        } else if (pagamento.getTipo().equals("Boleto")) {
            // Lógica para processar pagamento com boleto
        } else {
            throw new IllegalArgumentException("Tipo de pagamento inválido.");
        }
    }
    
}
