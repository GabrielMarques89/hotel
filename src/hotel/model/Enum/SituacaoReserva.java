package hotel.model.Enum;

/**
 * Created by grupoeuropa on 10/06/16.
 */
public enum SituacaoReserva {
	ATIVA("Ativa"),
	CANCELADA("Cancelada"),
	SUSPENSA("Suspensa"),
	ARQUIVADA("Arquivada");

	private String label;

	private SituacaoReserva(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
