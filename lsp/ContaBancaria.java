/**
 * Classe que representa uma conta bancária.
 * Há uma violação do princípio de substituição de Liskov porque essa classe pai não pode ser substituída por suas classes filhas sem quebrar o comportamento do programa.
 */

public class ContaBancaria {

    public void sacar(double valor) {
        // Lógica para saque de conta bancária padrão
    }
    
}