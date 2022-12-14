package booktasks.classes;

import booktasks.annotations.Cloneable;

public class MyObject {
    @Override
    public Object clone() throws CloneNotSupportedException {
        if (this.getClass().getDeclaredAnnotation(Cloneable.class) == null) {
            throw new CloneNotSupportedException("The annotation is absent");
        }
        return super.clone();
    }
}
