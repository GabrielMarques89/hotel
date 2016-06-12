package hotel.controller;

import hotel.Util.MsgUtil;
import hotel.dao.UsuarioDAO;
import hotel.model.Enum.Genero;
import hotel.model.Enum.StatusHospede;
import hotel.model.Usuario;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Luiz Guilherme on 11/06/2016.
 */
@Named
@ConversationScoped
public class ClienteBean extends BaseBean{
    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioDAO usuarioDAO;

    private Usuario usuario;

    private List<Usuario> listaUsuarios;

    @PostConstruct
    public void postConst(){
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuarios() {
        return usuarioDAO.listAll();
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String salvar() throws Exception {
        usuario = usuarioDAO.merge(usuario);
        if(usuario != null) {
            MsgUtil.addInfoMessage("Dados salvos com sucesso!", "");
        }else{
            MsgUtil.addErrorMessage("Desculpe, mas não foi possível salvar os dados.", "");
        }
        return cadastroCliente;
    }

    public String irEditar(long id) throws Exception{
        usuario = usuarioDAO.findById(id);
        if(usuario != null){
            return cadastroCliente;
        }
        MsgUtil.addErrorMessage("Desculpe, mas não o cliente não foi encontrado.", "");
        return listarClientes;
    }

    public String alterarStatus(long id) throws Exception{
        usuario = usuarioDAO.findById(id);
        if(usuario != null){
            if(usuario.getStatus().equals(StatusHospede.ATIVO)){
                usuario.setStatus(StatusHospede.INATIVO);
            }else{
                usuario.setStatus(StatusHospede.ATIVO);
            }
            usuarioDAO.merge(usuario);
        }else{
            MsgUtil.addErrorMessage("Desculpe, mas não o cliente não foi encontrado.", "");
        }
        return listarClientes;
    }
}