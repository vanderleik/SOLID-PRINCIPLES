/**
 * Classe que representa uma Conta Poupança
 * Essa classe extende a ContaBancaria porém não consegue executar a função sacar, pois a lógica desenvolvida na classe pai 
 * é para sacar em conta bancária padrão.
 */
public class ContaPoupanca extends ContaBancaria {

    @Override
    public void sacar(double valor) {
        throw new UnsupportedOperationException("Saque não permitido em conta poupança.");
    }
    
}
