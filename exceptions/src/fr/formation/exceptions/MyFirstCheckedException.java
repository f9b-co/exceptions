package fr.formation.exceptions;

@SuppressWarnings("serial")
public class MyFirstCheckedException extends Exception {

    public MyFirstCheckedException() {
	super();
    }

    public MyFirstCheckedException(String message) {
	super(message);
    }

    public MyFirstCheckedException(Throwable cause) {
	super(cause);
    }

    public MyFirstCheckedException(String message, Throwable cause) {
	super(message, cause);
    }
}
