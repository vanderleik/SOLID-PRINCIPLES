<h1>Princípios de design de software: S.O.L.I.D.</h1>

<p>Quando falamos em princípios de design de software estamos nos referindo à ideia por trás da orientação a objetos caracterizada pela escrita de um código limpo, organizado, flexível e fácil de manter. É nesse ponto que entra o S.O.L.I.D., um acrônimo que representa os cinco princípios que veremos a seguir.</p>

<p><strong>O Princípio da Responsabilidade Única (SRP) </strong>é fundamental no desenvolvimento de software. Ele determina que cada classe deve ter apenas uma responsabilidade bem definida, evitando que seja sobrecarregada com múltiplas funcionalidades. Seguir este princípio contribui para um código mais coeso, compreensível e fácil de manter.
</p>
<p>O código abaixo mostra como fica uma classe onde esse princípio não é respeitado.</p>

```
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
```
<p>A classe, embora represente uma conta bancária, viola o Princípio da Responsabilidade Única por conter um método não diretamente relacionado à sua função principal. Essa mistura de responsabilidades aumenta a complexidade da classe e dificulta sua manutenção.</p>
<p>Ao separar as responsabilidades em duas classes, o código apresentado promove diversas vantagens. A coesão é aprimorada, tornando mais fácil entender a função de cada classe. A classe NotificadorEmail, por exemplo, pode ser reutilizada para enviar notificações de outras classes. Além disso, a testabilidade é beneficiada, pois classes com escopo de funcionalidade reduzido simplificam a criação e execução de testes.</p>

```
public class ContaBancaria {
    private String numeroConta;
    private double saldo;

    public void depositar(double valor) {
        // ... lógica para depósito
    }

    public void sacar(double valor) {
        // ... lógica para saque
    }
}
```

```
public class NotificadorEmail {
    public void enviarEmailExtrato(ContaBancaria conta) {
        // ... lógica para enviar email com extrato da conta
    }
}
```



<p><strong>O Princípio do Aberto/Fechado (OCP)</strong> afirma que classes, módulos e outros componentes de software devem ser abertos para extensão, mas fechados para modificação. Em outras palavras, devem permitir a adição de novas funcionalidades sem a necessidade de alterar o código-fonte original. Para alcançar esse objetivo, técnicas como herança, interfaces e polimorfismo são frequentemente empregadas.</p>
<p>A classe ProcessadorDePagamento abaixo viola o OCP, pois a cada novo tipo de pagamento, a classe precisa ser modificada para incluir a lógica de processamento.</p>

```
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
```
<p>A correção se dá pela criação da interface ProcessadorDePagamentoStrategy, que define o método processar. Em seguida, são implementadas classes separadas, como ProcessadorCartaoDeCredito e ProcessadorBoleto, cada qual implementando a interface ProcessadorDePagamentoStrategy com sua lógica específica de processamento. A classe ProcessadorDePagamentoOCP, por sua vez, recebe a estratégia de processamento através do construtor. Essa abordagem, portanto, permite a extensão do comportamento sem a necessidade de modificar seu código original.</p>

```
public interface ProcessadorDePagamentoStrategy {

    void processar(Pagamento pagamento);    

}
```

```
public class ProcessadorCartaoDeCredito implements ProcessadorDePagamentoStrategy {

    @Override
    public void processar(Pagamento pagamento) {
        // Lógica para processar pagamento com cartão de crédito
    }
    
}
```

```
public class ProcessadorBoleto implements ProcessadorDePagamentoStrategy {

    @Override
    public void processar(Pagamento pagamento) {
         // Lógica para processar pagamento com boleto
    }
    
}
```

```
public class ProcessadorDePagamentoOCP {
    
    private ProcessadorDePagamentoStrategy strategy;

    public ProcessadorDePagamentoOCP(ProcessadorDePagamentoStrategy strategy) {
        this.strategy = strategy;
    }

    public void processar(Pagamento pagamento) {
        strategy.processar(pagamento);
    }

}
```

<p>Dessa forma, adicionamos novos tipos de pagamento sem alterar a classe ProcessadorDePagamentoOCP (flexibilidade), as estratégias de pagamento podem ser reutilizadas em diferentes partes do código (Reuso) e simplificamos a manutenção, pois as alterações ficam restritas às classes de estratégia de pagamento (Manutenção).</p>

<p><strong>O Princípio da Substituição de Liskov (LSP)</strong> afirma que os objetos de uma classe derivada devem poder substituir os objetos de sua classe base sem comprometer a correção do programa. Em outras palavras, o comportamento esperado de um programa que utiliza uma classe base não deve ser alterado quando uma instância de uma classe derivada é utilizada em seu lugar. Essa característica é crucial para garantir a confiabilidade e a previsibilidade de hierarquias de classes, as quais são amplamente utilizadas em conjunto com os conceitos de herança e polimorfismo.</p>

<p>A primeira implementação do código viola o Princípio da Substituição de Liskov (LSP) porque a classe ContaPoupanca redefine o método sacar para lançar uma exceção. Esse comportamento contradiz a expectativa estabelecida pela classe base ContaBancaria, que presume a possibilidade de saque em qualquer conta. Consequentemente, um código escrito para funcionar corretamente com a classe ContaBancaria pode apresentar falhas ao lidar com uma instância de ContaPoupanca.</p>

```
public class ContaBancaria {

    public void sacar(double valor) {
        // Lógica para saque de conta bancária padrão
    }
    
}
```

```
public class ContaPoupanca extends ContaBancaria {

    @Override
    public void sacar(double valor) {
        throw new UnsupportedOperationException("Saque não permitido em conta poupança.");
    }
    
}
```
<p>Em contrapartida, a segunda implementação utiliza uma abordagem mais adequada ao LSP. A classe ContaBancaria é definida como abstrata e declara o método sacar como abstrato, garantindo que todas as subclasses forneçam sua própria implementação. Dessa forma, tanto ContaCorrente quanto ContaPoupanca implementam o método sacar de acordo com suas regras específicas, respeitando o contrato estabelecido pela classe base e evitando comportamentos inesperados.</p>

```
public abstract class ContaBancariaLSP {

    public abstract void sacar(double valor);

}
```

```
public class ContaCorrenteLSP extends ContaBancariaLSP {

    @Override
    public void sacar(double valor) {
        // Lógica para saque de conta corrente
    }
    
}
```

```
public class ContaPoupancaLSP extends ContaBancariaLSP {

    @Override
    public void sacar(double valor) {
        // Lógica para saque de conta poupança, 
        // possivelmente com restrições específicas
    }
    
}
```


<p><strong>O Princípio da Segregação de Interface (ISP) </strong> defende que as interfaces devem ser específicas e coesas, atendendo às necessidades de cada cliente de forma individualizada. Isso significa que, em vez de criar interfaces amplas e genéricas, devem ser projetadas interfaces menores e mais especializadas, evitando assim que os clientes dependam de métodos que não utilizam.</p>

<p>A interface OperacoesBancarias, como definida no exemplo, viola o Princípio da Segregação de Interface (ISP) ao agregar operações de naturezas distintas, como gestão de conta, empréstimos e investimentos. Essa generalização excessiva força as classes que a implementam a fornecer implementações para métodos potencialmente irrelevantes. Uma classe ContaPoupanca, por exemplo, que não prevê a funcionalidade de empréstimo, seria obrigada a implementar o método solicitarEmprestimo(double valor, int parcelas), violando o princípio da coesão.</p>

```
public interface OperacoesBancarias {

    void depositar(double valor);
    void sacar(double valor);
    void transferir(double valor, String contaDestino);
    void solicitarEmprestimo(double valor, int parcelas);
    void investir(double valor, String tipoInvestimento);

}
```

<p>A criação de interfaces mais especializadas e coesas, como OperacoesDeConta, OperacoesDeEmprestimo e OperacoesDeInvestimento, cada qual abordando uma única responsabilidade, soluciona o problema da interface genérica. Essa abordagem, alinhada ao ISP, resulta em classes mais coesas, flexíveis e com acoplamento reduzido, características desejáveis em projetos de software orientados a objetos.</p>

```
public interface OperacoesDeConta {

    void depositar(double valor);
    void sacar(double valor);
    void transferir(double valor, String contaDestino);
    
}
```

```
public interface OperacoesDeEmprestimo {
    
    void solicitarEmprestimo(double valor, int parcelas);
    
}
```

```
public interface OperacoesDeInvestimento {

    void investir(double valor, String tipoInvestimento);    
    
}
```

<p><strong>O Princípio da Inversão de Dependência (DIP) </strong> afirma que módulos de alto nível não devem depender diretamente de módulos de baixo nível. Ambos devem depender de abstrações, como interfaces. Em outras palavras, as dependências entre os módulos devem ser invertidas, com os módulos de alto nível definindo o comportamento desejado através de abstrações, e os módulos de baixo nível implementando essas abstrações. Para ilustrar, imagine uma classe de alto nível que precisa utilizar uma funcionalidade específica, como enviar uma notificação. Em vez de depender diretamente de uma classe concreta de envio de notificações (baixo nível), a classe de alto nível dependeria de uma interface Notificador. Uma classe concreta, como NotificadorEmail, implementaria a interface Notificador e forneceria a lógica específica para o envio de emails. Essa inversão de dependência, utilizando interfaces como intermediárias, promove maior flexibilidade, testabilidade e reuso de código.</p>

<p>A classe TransferenciaBancaria depende diretamente da classe concreta EmailService. Isso cria um alto acoplamento, tornando difícil a alteração da forma de notificação no futuro ou a reutilização da classe TransferenciaBancaria em contextos onde outro tipo de notificação seja necessário (SMS, push notification, etc.).</p>

```
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
```

<p>A fim de alcançar um código com menor acoplamento, maior flexibilidade e testabilidade aprimorada, a solução implementada utiliza a interface Notificador, a qual define o método enviarNotificacao. A classe EmailService, por sua vez, implementa essa interface, fornecendo a lógica específica para o envio de emails. Dessa forma, a classe TransferenciaBancariaDIP passa a depender da abstração (Notificador), e não mais da implementação concreta (EmailService). A injeção da dependência, nesse caso, ocorre através do construtor da classe TransferenciaBancariaDIP, o qual recebe uma instância de Notificador.</p>

```
public interface Notificador {

    void enviarNotificacao(String destinatario, String assunto, String mensagem);
    
}
```

```
public class EmailService implements Notificador {

    @Override
    public void enviarNotificacao(String destinatario, String assunto, String mensagem) {
        // Lógica para enviar email
    }
    
}
```

```
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
```


<p>Ao longo deste texto, exploramos os cinco princípios SOLID, alicerces essenciais para o desenvolvimento de software orientado a objetos robusto e de fácil manutenção. Vimos como a Responsabilidade Única (SRP) contribui para classes mais coesas, o Aberto/Fechado (OCP) promove a extensibilidade sem modificações em código existente, a Substituição de Liskov (LSP) garante a confiabilidade em hierarquias de classes, a Segregação de Interfaces (ISP) evita dependências desnecessárias e a Inversão de Dependência (DIP) reduz o acoplamento entre módulos. Aplicando esses princípios em conjunto, como demonstrado nos exemplos da API bancária, desenvolvedores podem construir sistemas mais flexíveis, escaláveis, testáveis e resilientes a mudanças, características cruciais em um cenário tecnológico em constante evolução. As práticas incentivadas pelo SOLID não são apenas boas práticas, mas sim investimentos na qualidade e longevidade do software, garantindo que o código se mantenha robusto e adaptável ao longo do tempo.</p>
