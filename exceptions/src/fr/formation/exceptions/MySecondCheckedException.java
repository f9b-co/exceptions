package fr.formation.exceptions;

@SuppressWarnings("serial")
public class MySecondCheckedException extends Exception {

    public MySecondCheckedException() {
	super();
    }

    public MySecondCheckedException(String message) {
	super(message);
    }

    public MySecondCheckedException(Throwable cause) {
	super(cause);
    }
}
