package hotel.model.Enum;

/**
 * Created by grupoeuropa on 10/06/16.
 */
public enum SituacaoReserva {
	ATIVA("Ativa"),
	CANCELADA("Cancelada"),
	ATRASADA("Atrasada"),
	ARQUIVADA("Arquivada"),
	AGENDADA("Agendada"),
	HOSPEDADA("Hospedada");

	private String label;

	SituacaoReserva(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
