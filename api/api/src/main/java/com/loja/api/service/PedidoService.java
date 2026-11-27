package com.loja.api.service;

import com.loja.api.dto.PedidoDTO;
import com.loja.api.exception.ConflitoEX;
import com.loja.api.exception.RecursoNaoEncontradoEX;
import com.loja.api.model.Pedido;
import com.loja.api.model.Produto;
import com.loja.api.repository.PedidoRepository;
import com.loja.api.repository.ProdutoRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {
    private final PedidoRepository pedidos;
    private final ProdutoRepository produtos;

    public PedidoService(PedidoRepository pedidos, ProdutoRepository produtos) {
        this.pedidos = pedidos;
        this.produtos = produtos;
    }

    public List<Pedido> listar() { return pedidos.findAll(); }

    public Pedido buscar(Long id) {
        return pedidos.findById(id).orElseThrow(() -> new RecursoNaoEncontradoEX("Pedido " + id + " não encontrado"));
    }

    @Transactional
    public Pedido criar(PedidoDTO req) {
        Produto produto = produtos.findById(req.produtoId()).orElseThrow(() -> new RecursoNaoEncontradoEX("Produto " + req.produtoId() + " não encontrado"));

        if (produto.getEstoque() < req.quantidade()) {
            throw new ConflitoEX("Estoque insuficiente: disponível " + produto.getEstoque() + ", solicitado " + req.quantidade());
        }
        produto.setEstoque(produto.getEstoque() - req.quantidade());
        produtos.save(produto);

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setProduto(produto);
        pedido.setQuantidade(req.quantidade());
        return pedidos.save(pedido);
    }

    @Transactional
    public void cancelar(Long id) {
        Pedido pedido = buscar(id);
        Produto produto = pedido.getProduto();
        produto.setEstoque(produto.getEstoque() + pedido.getQuantidade());
        produtos.save(produto);
        pedidos.delete(pedido);
    }
}
