package br.com.supera.gamestore.services;

import br.com.supera.gamestore.dao.UsuarioDAO;
import br.com.supera.gamestore.exceptions.ObjectNotFoundException;
import br.com.supera.gamestore.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    //Retorna uma lista de todos os usuarios cadastrados na base de dados
    public List<Usuario> listarTodosUsuarios() {
        return usuarioDAO.findAll();
    }

    //Busca um Usuario passando por parâmetro um id.
    public Usuario buscarUsuarioPorId(Long id) {
        return verificaSeExisteUsuario(id);
    }

    //Salva um novo usuario na base de dados
    public Usuario criarUsuario(Usuario obj) {
        obj.setId(null);
        obj = usuarioDAO.save(obj);
        return obj;
    }

    //Verifica se existe um usuario na base de dados e atualiza suas informações
    public Usuario atualizarUsuario(Long id, Usuario obj) {
        Usuario newObj = verificaSeExisteUsuario(id);
        atualizaData(newObj, obj);
        return usuarioDAO.save(newObj);
    }

    //Verifica se existe um usuário na base de dados e deleta.
    public void deletarUsuario(Long id) {
        verificaSeExisteUsuario(id);
        usuarioDAO.deleteById(id);
    }

    /*
    método com a responsabilidade de passar os novos dados de um
    usuario para um usuario já existente na base de dados.
     */
    private void atualizaData(Usuario newObj, Usuario obj) {
        newObj.setNomeCompleto(obj.getNomeCompleto());
        newObj.setEmail(obj.getEmail());
        newObj.setCpf(obj.getCpf());
        newObj.setTelefone(obj.getTelefone());
    }

    /*
    verifica existe Usuario na base de dados passando por parâmetro um id,
    caso contrario é lançado uma excessão
     */
    private Usuario verificaSeExisteUsuario(Long id) throws ObjectNotFoundException {
        return usuarioDAO.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Usuario com "+ id +" não encontrado, Tipo: " + Usuario.class.getName()));
    }





}
