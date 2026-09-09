package github.fatec.com.estoque.controller;

import github.fatec.com.estoque.model.Produto;
import github.fatec.com.estoque.model.RegistrarVendaRequest;
import github.fatec.com.estoque.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProdutoController {

	private final ProdutoService produtoService;

	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@PostMapping("/produtos")
	@ResponseStatus(HttpStatus.CREATED)
	public Produto cadastrarProduto(@RequestBody Produto produto) {
		return produtoService.cadastrarProduto(produto);
	}

	@GetMapping("/produtos/{id}/estoque")
	public Produto consultarEstoque(@PathVariable Long id) {
		return produtoService.consultarEstoque(id);
	}

	@PostMapping("/vendas")
	public Produto registrarVenda(@RequestBody RegistrarVendaRequest request) {
		return produtoService.registrarVenda(request.getProdutoId(), request.getQuantidade());
	}
}