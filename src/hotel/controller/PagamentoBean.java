package hotel.controller;
/**
 * Created by grupoeuropa on 10/06/16.
 */

import hotel.dao.PagamentoDAO;
import hotel.model.Pagamento;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class PagamentoBean extends BaseBean {
	private static final long serialVersionUID = 1L;

	@Inject
	private PagamentoDAO pagamentoDAO;

	private Pagamento pagamento = new Pagamento();

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
}
