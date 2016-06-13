package hotel.controller;
/**
 * Created by grupoeuropa on 10/06/16.
 */

import hotel.ExceptionHandlers.ConstraintViolationHandler;
import hotel.Util.MsgUtil;
import hotel.dao.CartaoMagneticoDAO;
import hotel.model.CartaoMagnetico;
import hotel.model.Quarto;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

import static hotel.Util.Utilities.ConstraintViolationException;

/**
 * Created by grupoeuropa on 10/06/16.
 */

@Named
@ConversationScoped
public class CartaoMagneticoBean extends BaseBean {
	private static final long serialVersionUID = 1L;

	@Inject
	private CartaoMagneticoDAO cartaoMagneticoDAO;

	private CartaoMagnetico cartaoMagnetico;

	private List<CartaoMagnetico> listaCartoesMagneticos;

	public CartaoMagnetico getCartaoMagnetico() {
		return cartaoMagnetico;
	}

	public void setCartaoMagnetico(CartaoMagnetico cartaoMagnetico) {
		this.cartaoMagnetico = cartaoMagnetico;
	}

	@Override
	@PostConstruct
	public void postConst() {
		cartaoMagnetico = new CartaoMagnetico();
		cartaoMagnetico.setQuarto(new Quarto());
	}

	@Override
	public String salvar() {
        try{
            CartaoMagnetico cartaoMagneticoSalva = cartaoMagneticoDAO.merge(cartaoMagnetico);

            if(cartaoMagneticoSalva != null){
                MsgUtil.addInfoMessage("Dados salvos com sucesso!", "");
            }else{
                MsgUtil.addErrorMessage("Desculpe, mas não foi possível salvar os dados.", "");
            }
            return cadastroCartaoMagnetico;
        }catch (EJBException ex){
            ConstraintViolationHandler handler = ConstraintViolationException(ex);
            if(handler == null){
                throw ex;
            }
            if(handler.getHasError()){
                MsgUtil.addErrorMessage("Já existe um cartaoMagnetico registrado com este número.", "");
                return cadastroCartaoMagnetico;
            }
            throw ex;
        }catch (Exception e){
            throw e;
        }
	}

	public CartaoMagneticoDAO getCartaoMagneticoDAO() {
		return cartaoMagneticoDAO;
	}

	public void setCartaoMagneticoDAO(CartaoMagneticoDAO cartaoMagneticoDAO) {
		this.cartaoMagneticoDAO = cartaoMagneticoDAO;
	}

	public List<CartaoMagnetico> getListaCartoesMagneticos() {
		return listaCartoesMagneticos;
	}

	public void setListaCartoesMagneticos(List<CartaoMagnetico> listaCartoesMagneticos) {
		this.listaCartoesMagneticos = listaCartoesMagneticos;
	}

	public String irEditar(long id) throws Exception{
		cartaoMagnetico = cartaoMagneticoDAO.findById(id);
		if(cartaoMagnetico != null){
            return cadastroCartaoMagnetico;
        }
		MsgUtil.addErrorMessage("Desculpe, mas não o quarto não foi encontrado.", "");
		return listarCartoesMagneticos;
	}

	public String excluir(long id) throws Exception{
		cartaoMagnetico = cartaoMagneticoDAO.findById(id);
		if(cartaoMagnetico != null){
            cartaoMagneticoDAO.remove(cartaoMagnetico);
        }else{
            MsgUtil.addErrorMessage("Desculpe, mas não o cliente não foi encontrado.", "");
        }
		return listarQuartos;
	}
}
