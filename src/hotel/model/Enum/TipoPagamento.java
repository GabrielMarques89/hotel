package hotel.model.Enum;

/**
 * Created by grupoeuropa on 10/06/16.
 */
public enum TipoPagamento {
	CARTAO("Cartão"),
	CHEQUE("Cheque"),
	DINHEIRO("Dinheiro");

	private String label;

	private TipoPagamento(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
