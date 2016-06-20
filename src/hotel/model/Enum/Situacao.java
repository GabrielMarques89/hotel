package hotel.model.Enum;

/**
 * Created by grupoeuropa on 17/06/16.
 */
public enum Situacao {
	Ativo("Ativo"),
	Inativo("Inativo");
	
	private String label;
	
	Situacao(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
