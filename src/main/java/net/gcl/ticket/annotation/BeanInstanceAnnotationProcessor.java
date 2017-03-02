package net.gcl.ticket.annotation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * Created by guochenglai on 1/9/17.
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class BeanInstanceAnnotationProcessor extends AbstractProcessor{
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        int a = 1;
        return false;
    }
}
