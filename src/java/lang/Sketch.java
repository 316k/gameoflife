package java.lang;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;

<<<<<<< HEAD
/**
 * Définit si un bout de code a été écrit de façon sketch. E.g.: Transformer
 * un nombre en String et vérifier si le charAt(nombre.length-1) = 0, 2, 4, 6, ou 8
 * pour tester s'il est pair.
 * @author k
 */
=======
>>>>>>> b182e6d... Adds an @interface : @sketch. (I hope I
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE})
public @interface Sketch {
}
