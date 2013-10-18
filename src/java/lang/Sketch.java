package java.lang;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;

/**
 * Définit si un bout de code a été écrit de façon sketch. E.g.: Transformer
 * un nombre en String et vérifier si le charAt(nombre.length-1) = 0, 2, 4, 6, ou 8
 * pour tester s'il est pair.
 * @author k
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE})
public @interface Sketch {
}
