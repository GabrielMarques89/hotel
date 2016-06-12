package hotel.controller;
/**
 * Created by grupoeuropa on 10/06/16.
 */

import hotel.dao.CartaoMagneticoDAO;
import hotel.model.CartaoMagnetico;
import hotel.model.Quarto;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
}
