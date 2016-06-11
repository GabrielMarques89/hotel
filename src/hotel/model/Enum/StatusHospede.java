package hotel.model.Enum;

/**
 * Created by grupoeuropa on 09/06/16.
 */
public enum StatusHospede {
	ATIVO("Ativo"),
	INATIVO("Inativo"),
	HOSPEDADO("Hospedado"),
	RESERVADO("Reservado"),
	PENDENCIA("Pendência");

	private String label;

	private StatusHospede(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
