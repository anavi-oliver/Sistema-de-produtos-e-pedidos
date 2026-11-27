package com.loja.api.service;

import com.loja.api.exception.ConflitoEX;
import com.loja.api.exception.RecursoNaoEncontradoEX;
import com.loja.api.model.Produto;
import com.loja.api.repository.PedidoRepository;
import com.loja.api.repository.ProdutoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    private final ProdutoRepository produtos;
    private final PedidoRepository pedidos;

    public ProdutoService(ProdutoRepository produtos, PedidoRepository pedidos) {
        this.produtos = produtos;
        this.pedidos = pedidos;
    }

    public List<Produto> listar() { return produtos.findAll(); }

    public Produto buscar(Long id) {
        return produtos.findById(id).orElseThrow(() -> new RecursoNaoEncontradoEX("Produto " + id + " não encontrado"));
    }

    public Produto criar(Produto p) {
        p.setId(null);
        return produtos.save(p);
    }

    public Produto atualizar(Long id, Produto dados) {
        Produto p = buscar(id);
        p.setNome(dados.getNome());
        p.setDescricao(dados.getDescricao());
        p.setPreco(dados.getPreco());
        p.setEstoque(dados.getEstoque());
        return produtos.save(p);
    }

    public void remover(Long id) {
        Produto p = buscar(id);
        if (pedidos.existsByProdutoId(id)) {throw new ConflitoEX("Produto não pode ser removido: existe pedido associado a ele");
        }
        produtos.delete(p);
    }
}
