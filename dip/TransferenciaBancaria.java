package dip;

public class TransferenciaBancaria {
    
    private EmailService emailService;

    public TransferenciaBancaria() {
        this.emailService = new EmailService(); 
    }

    public void transferir(Conta origem, Conta destino, double valor) {
        // Lógica para realizar a transferência
        
        // Envio de email após a transferência
        emailService.enviarEmail(origem.getEmail(), "Transferência realizada", "Transferência de " + valor + " para " + destino.getNome());
    }
    
}
