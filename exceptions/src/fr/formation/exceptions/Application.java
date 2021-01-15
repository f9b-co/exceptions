package fr.formation.exceptions;

public class Application {

    public static void main(String[] args) {
	String[] values = { "Bon", " ", "weekend!" };
	int index = 2;
	System.out.println("result=" + getValueAt(values, index));
    }

    private static String getValueAt(String[] values, int index) {
	try {
	    return handleExceptions(values, index);
	} catch (MyFirstCheckedException ex) {
	    throw new MyUncheckedException(ex);
	}
    }

    private static String handleExceptions(String[] values, int index)
	    throws MyFirstCheckedException {
	String result = null;
	try {
	    result = values[index];
	} catch (NullPointerException ex) {
	    throw new MyFirstCheckedException("array is null", ex);
	} catch (ArrayIndexOutOfBoundsException ex) {
	    throw new MyFirstCheckedException("bad value for index", ex);
	}
	return result;
    }
}
