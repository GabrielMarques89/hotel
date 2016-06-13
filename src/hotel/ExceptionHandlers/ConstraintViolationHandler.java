package hotel.ExceptionHandlers;

/**
 * Created by grupoeuropa on 13/06/16.
 */
public class ConstraintViolationHandler {
	private String contraintName;
	private Boolean hasError;

	public ConstraintViolationHandler() {}

	public ConstraintViolationHandler(String contraintName, Boolean hasError) {
		this.contraintName = contraintName;
		this.hasError = hasError;
	}

	public String getContraintName() {
		return contraintName;
	}

	public void setContraintName(String contraintName) {
		this.contraintName = contraintName;
	}

	public Boolean getHasError() {
		return hasError;
	}

	public void setHasError(Boolean hasError) {
		this.hasError = hasError;
	}
}
