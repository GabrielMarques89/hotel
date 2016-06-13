package hotel.ExceptionHandlers;

/**
 * Created by grupoeuropa on 13/06/16.
 */
public class ConstraintViolationHandler {
	private String constraintName;
	private Boolean hasError;

	public ConstraintViolationHandler() {}

	public ConstraintViolationHandler(String constraintName, Boolean hasError) {
		this.constraintName = constraintName;
		this.hasError = hasError;
	}

	public String getConstraintName() {
		return constraintName;
	}

	public void setConstraintName(String constraintName) {
		this.constraintName = constraintName;
	}

	public Boolean getHasError() {
		return hasError;
	}

	public void setHasError(Boolean hasError) {
		this.hasError = hasError;
	}
}
