package booktasks.annotations;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@SupportedAnnotationTypes("booktasks.annotations.Todo")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class TodoProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (annotations.size() == 0) return true;
        try {
            FileObject file = processingEnv.getFiler().createResource(StandardLocation.SOURCE_OUTPUT, "", "Todos.txt");
            try (PrintWriter out = new PrintWriter(file.openWriter())) {
                for (Element e : roundEnv.getElementsAnnotatedWith(Todo.class)) {
                    if (e instanceof TypeElement te) {
                        writeTodo(out, te);
                    }
                }
            }
        } catch (IOException e) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.getMessage());
        }
        return true;
    }

    private void writeTodo(PrintWriter out, TypeElement te) {
        String className = te.getQualifiedName().toString();
        String todoText = te.getAnnotation(Todo.class).value();
        out.println(className);
        out.println(todoText);
    }
}
