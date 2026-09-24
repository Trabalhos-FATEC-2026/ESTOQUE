package github.fatec.com.estoque.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProdutoEventoListener {
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@RabbitListener(queues = "Meus-Produtos")
	public void receberEventoProduto(String mensagem) {
		try {
			System.out.println("\n[ESTOQUE] Evento recebido do PRODUTO:");
			System.out.println("Mensagem: " + mensagem);
			
			// Verifica se é JSON válido
			if (mensagem.trim().startsWith("{") || mensagem.trim().startsWith("[")) {
				Object evento = objectMapper.readValue(mensagem, Object.class);
				System.out.println("[OK] Evento JSON processado com sucesso!");
				System.out.println("Dados: " + evento.toString());
			} else {
				// É apenas texto simples
				System.out.println("[OK] Mensagem de texto recebida com sucesso!");
				System.out.println("Conteúdo: " + mensagem);
			}
			
		} catch (Exception e) {
			System.out.println("[ERRO] Erro ao processar evento: " + e.getMessage());
		}
	}
}
