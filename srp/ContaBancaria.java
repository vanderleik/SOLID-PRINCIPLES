/**
 * Classe que representa uma conta bancária.
 * Essa classe viola o princípio da responsabilidade única, pois possui métodos que não são relacionados a uma conta bancária.
 */
public class ContaBancaria {
    private String numeroConta;
    private double saldo;

    public void depositar(double valor) {
        //lógica para depositar
    }

    public void sacar(double valor) {
        //lógica para sacar
    }

    public void enviarExtratoPorEmail() {
        //lógica para enviar extrato por email
    }
}