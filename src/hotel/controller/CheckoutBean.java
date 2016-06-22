package hotel.controller;
/**
 * Created by grupoeuropa on 20/06/16.
 * Template by Gabriel Marques
 */

import hotel.dao.CartaoMagneticoDAO;
import hotel.dao.CheckoutDAO;
import hotel.dao.ReservaDAO;
import hotel.model.Checkout;
import hotel.model.Enum.SituacaoReserva;
import hotel.model.Reserva;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class CheckoutBean extends BaseBean {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CheckoutDAO checkoutDAO;

	@Inject
	private ReservaDAO reservaDAO;

	@Inject
	private CartaoMagneticoDAO cartaoMagneticoDAO;

	private Checkout checkout = new Checkout();
	
	@PostConstruct
	public void postConst(){
	}

	public Checkout getCheckout() {
		return checkout;
	}
	
	public void setCheckout(Checkout checkout) {
		this.checkout = checkout;
	}

	public void salvar() throws Exception {
		Reserva reserva = checkout.getReserva();
		if(reserva.getSituacaoReserva().equals(SituacaoReserva.HOSPEDADA)){
			reserva.setSituacaoReserva(SituacaoReserva.ARQUIVADA);
			reservaDAO.merge(reserva);
			checkoutDAO.merge(checkout);
		}else{
			throw new Exception("Erro - Tentativa de checkout em reserva inadequada (SituReserva != Hospedada) ");
		}
	}
}
