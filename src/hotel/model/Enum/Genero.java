package hotel.model.Enum;

/**
 * Created by grupoeuropa on 09/06/16.
 */
public enum Genero {
	MASCULINO("Masculino"), FEMININO("Feminino");

	private String label;

	Genero(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
