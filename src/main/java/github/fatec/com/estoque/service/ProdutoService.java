package github.fatec.com.estoque.service;

import github.fatec.com.estoque.model.Produto;
import github.fatec.com.estoque.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProdutoService {

	private final ProdutoRepository produtoRepository;

	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	public Produto cadastrarProduto(Produto produto) {
		if (produto.getNome() == null || produto.getNome().isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome do produto e obrigatorio.");
		}

		if (produto.getQtd() == null || produto.getQtd() < 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade invalida.");
		}

		return produtoRepository.save(produto);
	}

	public Produto consultarEstoque(Long id) {
		return buscarProduto(id);
	}

	public Produto registrarVenda(Long produtoId, Integer quantidade) {
		if (quantidade == null || quantidade <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade da venda invalida.");
		}

		Produto produto = buscarProduto(produtoId);

		if (produto.getQtd() < quantidade) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estoque insuficiente.");
		}

		produto.setQtd(produto.getQtd() - quantidade);
		return produtoRepository.save(produto);
	}

	private Produto buscarProduto(Long id) {
		return produtoRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado."));
	}
}