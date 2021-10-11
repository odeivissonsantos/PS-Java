package br.com.supera.gamestore.services;

import br.com.supera.gamestore.dao.ProdutoDAO;
import br.com.supera.gamestore.dtos.ProdutoDTO;
import br.com.supera.gamestore.exceptions.ObjectNotFoundException;
import br.com.supera.gamestore.models.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProdutoServiceTeste {

    private static final Integer ID        = 1;
    private static final String NOME       = "VSuper Mario Odyssey";
    private static final BigDecimal PRECO  = BigDecimal.valueOf(197.88);
    private static final Integer PONTUACAO = 100;
    private static final String URLIMAGEM  = "super-mario-odyssey.png";
    private static final Integer INDEX_0   = 0;

    private static final String JA_CADASTRADO_NO_SISTEMA = "já cadastrado no sistema";

    @InjectMocks
    ProdutoService produtoService;

    @Mock
    ProdutoDAO produtoDAO;

    private Optional<Produto> optionalProduto;
    private Produto produto;
    private ProdutoDTO produtoDTO;

    @BeforeEach
    void setUp() {
        iniciaOptionalProduto();
        iniciaProduto();
        iniciaProdutoDTO();
    }

    @Test
    void findByIdSuccessTest() {
        when(produtoDAO.findById((long) anyInt())).thenReturn(this.optionalProduto);

        Produto response = produtoService.buscarProdutoPorId(Long.valueOf(ID));

        if(optionalProduto.isPresent()) {
            assertEquals(optionalProduto.get().getId(), response.getId());
            assertEquals(optionalProduto.get().getNome(), response.getNome());
            assertEquals(optionalProduto.get().getPreco(), response.getPreco());
            assertEquals(optionalProduto.get().getPontuacao(), response.getPontuacao());
            assertEquals(optionalProduto.get().getUrlImagem(), response.getUrlImagem());
        }
    }

    @Test
    void findByIdErrorTest() {
        ObjectNotFoundException notFoundException = new ObjectNotFoundException("Produto com "+ ID +" não encontrado, Tipo: " + Produto.class.getName());
        when(produtoDAO.findById((long) anyInt())).thenThrow(notFoundException);

        try{
            produtoService.buscarProdutoPorId(Long.valueOf(ID));
        } catch (Exception ex) {
            assertEquals(notFoundException.getClass(), ex.getClass());
            assertEquals(notFoundException.getMessage(), ex.getMessage());
        }
    }

    @Test
    void findAllTest() {
        List<Produto> list = List.of(produto);

        when(produtoDAO.findAll()).thenReturn(list);
        List<Produto> response = produtoService.listarTodosProdutos();

        assertEquals(response.size(), list.size());
        assertEquals(response.get(INDEX_0).getId(), list.get(INDEX_0).getId());
        assertEquals(response.get(INDEX_0).getNome(), list.get(INDEX_0).getNome());
        assertEquals(response.get(INDEX_0).getPreco(), list.get(INDEX_0).getPreco());
        assertEquals(response.get(INDEX_0).getPontuacao(), list.get(INDEX_0).getPontuacao());
        assertEquals(response.get(INDEX_0).getUrlImagem(), list.get(INDEX_0).getUrlImagem());
    }

    @Test
    void createSuccessTest() {
        when(produtoDAO.save(Mockito.any(Produto.class))).thenReturn(produto);

        Produto response = produtoService.criarProduto(produto);

        assertEquals(produto.getId(), response.getId());
        assertEquals(produto.getNome(), response.getNome());
        assertEquals(produto.getPreco(), response.getPreco());
        assertEquals(produto.getPontuacao(), response.getPontuacao());
        assertEquals(produto.getUrlImagem(), response.getUrlImagem());
    }

    @Test
    void updateWithObjectNotFoundErrorTest() {
        try {
            produtoService.atualizarProduto(Long.valueOf(ID), produto);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Produto com "+ ID +" não encontrado, Tipo: " + Produto.class.getName(), ex.getMessage());
        }
    }

    @Test
    void deleteByIdSuccessTest() {
        doNothing().when(produtoDAO).deleteById(Long.valueOf(anyInt()));
        produtoService.deletarProduto(Long.valueOf(ID));
    }


    private void iniciaOptionalProduto() {
        optionalProduto = Optional.of(new Produto());

        optionalProduto.get().setId((long) ID);
        optionalProduto.get().setNome(NOME);
        optionalProduto.get().setPreco(PRECO);
        optionalProduto.get().setPontuacao(PONTUACAO);
        optionalProduto.get().setUrlImagem(URLIMAGEM);
    }

    private void iniciaProduto() {
        produto = new Produto();
        produto.setId((long) ID);
        produto.setNome(NOME);
        produto.setPreco(PRECO);
        produto.setPontuacao(PONTUACAO);
        produto.setUrlImagem(URLIMAGEM);
    }

    private void iniciaProdutoDTO() {
        produtoDTO = new ProdutoDTO();

        produtoDTO.setId((long) ID);
        produtoDTO.setNome(NOME);
        produtoDTO.setPreco(String.valueOf(PRECO));
        produtoDTO.setPontuacao(PONTUACAO);
        produtoDTO.setUrlImagem(URLIMAGEM);
    }

}
