package fr.formation.exceptions;

/**
 * Une <b>exception</b> c'est quelque chose d'anormal qui se produit <b>pendant
 * l'exécution</b> du programme.
 * <p>
 * <b>Trois types d'exception en Java</b> :
 * <ul>
 * <li><b>Error</b> : un type d'exception qu'on ne doit pas gérer (plantage
 * système, plus de mémoire pour la JVM, erreur dans la JVM...), on ne se
 * préoccupe pas des ces exceptions sauf quand elles se produisent !
 * <li><b>Checked</b> : un type d'exception qu'on <i>doit gérer</i> d'une
 * manière ou d'une autre, correspond à une anormalité qui <i>peut</i> se
 * produire (serveur de base de données indisponible, plus d'espace disque pour
 * stocker un fichier...)
 * <li><b>Unchecked</b> : un type d'exception qu'on <i>peut gérer</i> mais
 * généralement on ne le fait pas, correspond à des bugs (accès à null, erreur
 * de cast...). On laisse donc l'exception se propager.
 * </ul>
 * <p>
 * La maman de toutes les exceptions est la classe {@code java.lang.Throwable},
 * Java s'appuie donc sur l'héritage pour gérer d'une manière ou d'une autre une
 * exception selon sa classe, son type:
 * <ul>
 * <li><b>Error</b> : {@code java.lang.Error} et ses sous classes
 * <li><b>Checked</b> : {@code java.lang.Exception} et ses sous classes sauf
 * {@code java.lang.RuntimeException}
 * <li><b>Unchecked</b> : {@code java.lang.RuntimeException} et ses sous classes
 * <p>
 * Toutes les classes d'exception peuvent être généralement instanciées sans
 * argument, avec un argument pour un message détaillé (information contextuelle
 * sur la cause du problème), ou encore avec une exception d'origine (chaînage
 * d'exceptions de cause en cause).
 * </ul>
 */
@SuppressWarnings("unused")
public class Examples {

    public static void main(String[] args) {
	//
    }

    private static void throwImplicitUnchecked(String value) {
	int length = value.length(); // exception when value is null
	System.out.println(length);
    }

    private static void throwExplicitUnchecked() {
	throw new IllegalArgumentException();
	// Code inaccessible
	// boolean flag = false;
    }

    private static void throwExplicitCustomUnchecked(String value) {
	if (value == null) {
	    throw new MyUncheckedException("value is null");
	    // Code inaccessible
	}
	// Code accessible
	System.out.println("value=" + value);
    }

    // throws MyCheckedException dans la signature de la méthode : c'est pour
    // indiquer au client de la méthode qu'une "checked" exception doit être
    // gérée.
    private static void throwExplicitChecked(String value)
	    throws MyFirstCheckedException {
	if (value == null) {
	    throw new MyFirstCheckedException("value is null");
	    // Code inaccessible
	}
	System.out.println("value=" + value);
    }

    private static void propagateChecked(String value)
	    throws MyFirstCheckedException {
	throwExplicitChecked(value);
    }

    /*
     * L'exception est gérée par un bloc try-catch, pas utile de déclarer throws
     * dans la signature.
     */
    private static void handleChecked(
	    String value) /* throws MyCheckedException */ {
	try {
	    throwExplicitChecked(value);
	    System.out.println("called throwExplicitChecked without error :)");
	} catch (MyFirstCheckedException ex) {
	    System.out.println("error calling throwExplicitChecked, message="
		    + ex.getMessage());
	}
    }

    private static void swallowException(String value) {
	try {
	    throwExplicitChecked(value);
	    System.out.println("called throwExplicitChecked without error :)");
	} catch (MyFirstCheckedException ex) {
	    // Ne jamais rien faire dans un bloc catch !
	}
    }

    private static void tryWithFinally(String value) {
	boolean success = false;
	try {
	    throwExplicitChecked(value);
	    success = true;
	} catch (MyFirstCheckedException ex) {
	    System.out.println("catch bloc, success=" + success);
	} finally {
	    System.out.println("finally bloc, success=" + success);
	}
    }

    private static void tryMultipleCatch(String first, String second) {
	try {
	    throwExplicitChecked(first);
	    System.out.println("called throwExplicitChecked without error :)");
	    throwSecondExplicitChecked(second);
	    System.out.println(
		    "called throwSecondExplicitChecked without error :)");
	} catch (MyFirstCheckedException ex) {
	    System.out.println("error calling throwExplicitChecked, message="
		    + ex.getMessage());
	} catch (MySecondCheckedException ex) {
	    System.out.println(
		    "error calling throwSecondExplicitChecked, message="
			    + ex.getMessage());
	}
    }

    private static void throwSecondExplicitChecked(String value)
	    throws MySecondCheckedException {
	if (value == null) {
	    throw new MySecondCheckedException("value is null");
	    // Code inaccessible
	}
	System.out.println("value=" + value);
    }

    private static void tryMultipleSingleCatch(String first, String second) {
	try {
	    throwExplicitChecked(first);
	    System.out.println("called throwExplicitChecked without error :)");
	    throwSecondExplicitChecked(second);
	    System.out.println(
		    "called throwSecondExplicitChecked without error :)");
	} catch (MyFirstCheckedException | MySecondCheckedException ex) {
	    // vs catch(Exception ex)
	    System.out.println("error in tryMultipleSingleCatch, message="
		    + ex.getMessage());
	}
    }

    private static void rethrowException(String value) {
	try {
	    throwExplicitChecked(value);
	    System.out.println("called throwExplicitChecked without error :)");
	} catch (MyFirstCheckedException ex) {
	    throw new MyUncheckedException("contextual message", ex);
	}
    }
}
