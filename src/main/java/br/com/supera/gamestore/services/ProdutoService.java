package br.com.supera.gamestore.services;

import br.com.supera.gamestore.dao.ProdutoDAO;
import br.com.supera.gamestore.exceptions.ObjectNotFoundException;
import br.com.supera.gamestore.models.Produto;
import br.com.supera.gamestore.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoDAO produtoDAO;

    public ProdutoService(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    //Retorna uma lista de todos os produtos cadastrados na base de dados
    public List<Produto> listarTodosProdutos() {
        return produtoDAO.findAll();
    }

    //Busca um produto passando por parâmetro um id.
    public Produto buscarProdutoPorId(Long id) {
        return verificaSeExisteProduto(id);
    }

    //Salva um novo Produto na base de dados
    public Produto criarUsuario(Produto obj) {
        obj.setId(null);
        obj = produtoDAO.save(obj);
        return obj;
    }

    //Verifica se existe um produto na base de dados e atualiza suas informações
    public Produto atualizarProduto(Long id, Produto obj) {
        Produto newObj = verificaSeExisteProduto(id);
        atualizaData(newObj, obj);
        return produtoDAO.save(newObj);
    }

    //Verifica se existe um Produto na base de dados e deleta.
    public void deletarProduto(Long id) {
        verificaSeExisteProduto(id);
        produtoDAO.deleteById(id);
    }

    /*
    método com a responsabilidade de passar os novos dados de um
    produto para um produto já existente na base de dados.
     */
    private void atualizaData(Produto newObj, Produto obj) {
        newObj.setNome(obj.getNome());
        newObj.setPontuacao(obj.getPontuacao());
        newObj.setPreco(obj.getPreco());
        newObj.setUrlImagem(obj.getUrlImagem());
    }

    /*
    verifica existe um Produto na base de dados passando por parâmetro um id,
    caso contrario é lançado uma excessão
     */
    private Produto verificaSeExisteProduto(Long id) throws ObjectNotFoundException {
        return produtoDAO.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Produto com "+ id +" não encontrado, Tipo: " + Usuario.class.getName()));
    }

}
