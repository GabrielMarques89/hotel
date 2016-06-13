package hotel.Util;

import hotel.ExceptionHandlers.ConstraintViolationHandler;
import hotel.model.Entidade;
import org.hibernate.exception.ConstraintViolationException;

import javax.swing.text.MaskFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	private static final int[] PESOCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

	public static boolean isValid(Entidade entidade) {
		try {
			return entidade.getId() > 0;
		} catch (Exception e) {
			return false;
		}
	}

	public static ConstraintViolationHandler ConstraintViolationException(Throwable throwable) {
		try {
			if (throwable instanceof ConstraintViolationException) {
				ConstraintViolationHandler handler = new ConstraintViolationHandler(throwable.getLocalizedMessage(), true);
				return handler;
			}
			return ConstraintViolationException(throwable.getCause());
		} catch (NullPointerException e) {
			System.out.println(e);
		}
		return null;
	}

	public static String formatarCep(String value) throws Exception{
		MaskFormatter mask;
		try{
			mask = new MaskFormatter("##.###-###");
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(value);
		}
		catch (Exception e){
			throw e;
		}
	}

	public static boolean emailValido(String emailResponsavel) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailResponsavel);
		return matcher.find();
	}


	public static boolean validaCPF(String cpf) {
		if (cpf == null || cpf.length() != 11 || isCPFPadrao(cpf)) {
			return false;
		}
		try {
			Long.parseLong(cpf);
		} catch (NumberFormatException e) { // CPF não possui somente números
			return false;
		}

		return calcDigVerif(cpf.substring(0, 9)).equals(cpf.substring(9, 11));
	}

	private static boolean isCPFPadrao(String cpf) {
		if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555") || cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") || cpf.equals("99999999999")) {

			return true;
		}
		return false;
	}

	private static String calcDigVerif(String num) {
		Integer primDig, segDig;
		int soma = 0, peso = 10;
		for (int i = 0; i < num.length(); i++) {
			soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
		}

		if (soma % 11 == 0 || soma % 11 == 1) {
			primDig = new Integer(0);
		} else {
			primDig = new Integer(11 - soma % 11);
		}

		soma = 0;
		peso = 11;
		for (int i = 0; i < num.length(); i++) {
			soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
		}

		soma += primDig.intValue() * 2;
		if (soma % 11 == 0 || soma % 11 == 1) {
			segDig = new Integer(0);
		} else {
			segDig = new Integer(11 - soma % 11);
		}

		return primDig.toString() + segDig.toString();
	}

	public static boolean isValidCNPJ(String cnpj) {
		if (cnpj == null || cnpj.length() != 14) {
			return false;
		}

		Integer digito1 = calcularDigito(cnpj.substring(0, 12), PESOCNPJ);
		Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, PESOCNPJ);
		return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
	}

	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

	public static Boolean doublePositive(Double valor) {
		return valor == null ? Boolean.FALSE : valor.compareTo(0D) > 0;
	}

	public static String concatenar(Object... params) {
		StringBuilder retorno = new StringBuilder();
		for (Object param : params) {
			retorno.append(param);
		}
		return retorno.toString();
	}

	public static String concatenarLinha(Object... params) {
		StringBuilder retorno = new StringBuilder();
		for (Object param : params) {
			retorno.append(param);
		}
		return retorno.append("\n").toString();
	}
}
