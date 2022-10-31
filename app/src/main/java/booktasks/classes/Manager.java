package booktasks.classes;

public class Manager extends Employee {
    private double bonus;

    public Manager(String name, double salary) {
        super(name, salary);
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }
}
