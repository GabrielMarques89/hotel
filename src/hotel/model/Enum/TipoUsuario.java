package hotel.model.Enum;

/**
 * Created by grupoeuropa on 09/06/16.
 */
public enum TipoUsuario {
	ADMINISTRADOR("Administrador"),
	CLIENTE("Cliente"),
	FUNCIONARIO("Funcionario"),
	AFILIADO("Afiliado");

	private String label;

	TipoUsuario(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
