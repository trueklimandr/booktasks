package booktasks.classes;

public class NotCloneable extends MyObject {
    @Override
    public NotCloneable clone() throws CloneNotSupportedException {
        return (NotCloneable) super.clone();
    }
}
