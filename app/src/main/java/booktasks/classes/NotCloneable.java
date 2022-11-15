package booktasks.classes;

import booktasks.annotations.Todo;

@Todo("Add the Cloneable annotation")
public class NotCloneable extends MyObject {
    @Todo("Check it works after fixing the first todo")
    @Override
    public NotCloneable clone() throws CloneNotSupportedException {
        return (NotCloneable) super.clone();
    }
}
