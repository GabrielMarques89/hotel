package hotel.controller;


import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.Serializable;

public abstract class BaseBean implements Serializable {

	public BaseBean(){};

	private static final long serialVersionUID = 1L;

	protected static String loginPage = "login.xhtml";
	protected static String indexPage = "index.xhtml";
	protected static String cadastroPage = "registro.xhtml";

	@Inject
	protected SessionBean sessionBean;

	@PostConstruct
	public abstract void postConst();

	public abstract String cadastro();
}
