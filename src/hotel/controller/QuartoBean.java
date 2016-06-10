package hotel.controller;
/**
 * Created by grupoeuropa on 10/06/16.
 */

import hotel.dao.QuartoDAO;
import hotel.model.Quarto;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class QuartoBean extends BaseBean {
	private static final long serialVersionUID = 1L;

	@Inject
	private QuartoDAO quartoBeanDAO;

	private Quarto quarto = new Quarto();

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}
}
