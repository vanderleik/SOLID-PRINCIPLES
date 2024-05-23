package dip;

public class TransferenciaBancariaDIP {
    
    private Notificador notificador;

    public TransferenciaBancariaDIP(Notificador notificador) {
        this.notificador = notificador;
    }

    public void transferir(Conta origem, Conta destino, double valor) {
        // Lógica para realizar a transferência
        
        // Envio de notificação após a transferência
        notificador.enviarNotificacao(origem.getEmail(), "Transferência realizada", "Transferência de " + valor + " para " + destino.getNome());
    }
    
}
