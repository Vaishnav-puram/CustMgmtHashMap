package Exceptions;

public class DuplicateEmailException extends Exception {
	public DuplicateEmailException(String msg) {
		super(msg);
	}
}

