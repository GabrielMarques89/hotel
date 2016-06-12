package hotel.controller;
/**
 * Created by grupoeuropa on 10/06/16.
 */

import hotel.Util.MsgUtil;
import hotel.dao.CartaoMagneticoDAO;
import hotel.model.CartaoMagnetico;
import hotel.model.Quarto;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

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
		return null;
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
		try{
			cartaoMagnetico = cartaoMagneticoDAO.findById(id);
			if(cartaoMagnetico != null){
				return cadastroCartaoMagnetico;
			}
			MsgUtil.addErrorMessage("Desculpe, mas não o quarto não foi encontrado.", "");
		}catch (Exception e){
			MsgUtil.addWarnMessage("Houve algum problema. Por favor, tente mais tarde.", "");
		}
		return listarCartoesMagneticos;
	}

	public String excluir(long id) throws Exception{
		try{
			cartaoMagnetico = cartaoMagneticoDAO.findById(id);
			if(cartaoMagnetico != null){
				cartaoMagneticoDAO.remove(cartaoMagnetico);
			}else{
				MsgUtil.addErrorMessage("Desculpe, mas não o cliente não foi encontrado.", "");
			}
		}catch (Exception e){
			MsgUtil.addWarnMessage("Ocorreu algum problema. Por favor, tente mais tarde.", "");
		}
		return listarQuartos;
	}
}
