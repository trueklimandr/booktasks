package booktasks.annotations;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
@Repeatable(Todos.class)
public @interface Todo {
    String value();
}

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
@interface Todos {
    Todo[] value();
}
