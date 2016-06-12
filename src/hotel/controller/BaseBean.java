package hotel.controller;


import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.Serializable;

public abstract class BaseBean implements Serializable {

	public BaseBean(){};

	private static final long serialVersionUID = 1L;

	protected static String loginPage = "login.xhtml";
	protected static String indexPage = "index.xhtml";
	protected static String editarDados = "editarDados.xhtml";
	protected static String listarQuartos = "listarQuartos.xhtml";
	protected static String listarReservas = "listarReservas.xhtml";
	protected static String listarClientes = "listarClientes.xhtml";
	protected static String cadastroPage = "registro.xhtml";
	protected static String cadastroCliente = "cadastroCliente.xhtml";
	protected static String cadastroReserva = "cadastroReserva.xhtml";
	protected static String cadastroQuarto = "cadastroQuarto.xhtml";
	protected static String cadastroTipoQuarto = "cadastroTipoQuarto.xhtml";

	@Inject
	protected SessionBean sessionBean;

	@PostConstruct
	public abstract void postConst();

	public abstract String salvar() throws Exception;
}
