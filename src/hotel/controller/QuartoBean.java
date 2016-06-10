package hotel.controller;
/**
 * Created by grupoeuropa on 10/06/16.
 */

import hotel.dao.QuartoDAO;
import hotel.dao.TipoQuartoDAO;
import hotel.model.Quarto;
import hotel.model.TipoQuarto;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ConversationScoped
public class QuartoBean extends BaseBean {
	private static final long serialVersionUID = 1L;

	@Inject
	private QuartoDAO quartoDAO;

	@Inject
	private TipoQuartoDAO tipoQuartoDAO;

	private List<TipoQuarto> listTipos;

	@Override
	@PostConstruct
	public void postConst(){
		quarto = new Quarto();
		quarto.setTipo(new TipoQuarto());
	}

	private Quarto quarto;

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public List<TipoQuarto> getListTipos() {
		return tipoQuartoDAO.listAll();
	}

	public void setListTipos(List<TipoQuarto> listTipos) {
		this.listTipos = listTipos;
	}

	public String cadastro(){
		quartoDAO.merge(quarto);
		return indexPage;
	}
}
