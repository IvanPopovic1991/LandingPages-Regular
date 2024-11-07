package listeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Transform implements IAnnotationTransformer {
    /**
     * Ova metoda poziva RetryAnalyzer klasu i njenu metodu retry. Koristi se u .xml fajlu.
     * @param annotation The annotation that was read from your test class.
     * @param testClass If the annotation was found on a class, this parameter represents this class
     *     (null otherwise).
     * @param testConstructor If the annotation was found on a constructor, this parameter represents
     *     this constructor (null otherwise).
     * @param testMethod If the annotation was found on a method, this parameter represents this
     *     method (null otherwise).
     */
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}
