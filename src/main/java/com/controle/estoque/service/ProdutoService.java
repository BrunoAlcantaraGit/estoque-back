package com.controle.estoque.service;

import com.controle.estoque.model.Produto;
import com.controle.estoque.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Data
public class ProdutoService {

    ProdutoRepository produtoRepository;


    public Produto salvarProduto(Produto produto) throws Exception {
        Optional<Produto> verificarCliente = produtoRepository.findById(produto.getId());

        if (verificarCliente.isEmpty()) {
            return produtoRepository.save(produto);
        } else {
            throw new RuntimeException("Produdo já cadastrado b");
        }

    }
    @Transactional
    public Optional<Produto> listarPorId(Long id) throws Exception{
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()){
            return produto;
        }else{
            throw new Exception("Porduto inexitente");
        }
    }

    @Transactional
    public Produto EditarPorId(Long id, Produto produto) throws Exception {
        Optional<Produto> buscarProduto = produtoRepository.findById(id);
        if (buscarProduto.isPresent()) {
            Produto produtoAtualizado = buscarProduto.get();
            produtoAtualizado.setCategoria(produto.getCategoria());
            produtoAtualizado.setDescricao(produto.getDescricao());
            produtoAtualizado.setMarca(produto.getMarca());
            produtoAtualizado.setCodigo(produto.getCodigo());
            return produto;
        } else {
            throw new Exception("Produto não cadastrado");
        }

    }
    @Transactional
    public void deletarID(Long id) throws Exception {
        Optional<Produto> validarProduto = produtoRepository.findById(id);
        if (validarProduto.isPresent()) {
            produtoRepository.deleteById(id);
        } else {
            throw new Exception("Id inválido");
        }

    }

    public List<Produto> listarTudo() throws Exception{
        List<Produto> verificarLista = produtoRepository.findAll();
        if (!verificarLista.isEmpty()){
            List<Produto>produtos = new ArrayList<>();
            for (Produto p: verificarLista){
                produtos.add(p);
            }
            return produtos;
        }else {
            throw new Exception("Lista não contem elementos");
        }
    }



}
