package Model;

import java.time.LocalDate;

public class WaiterImpl extends Employee implements Waiter{
    public double tip;

    public WaiterImpl(String empID, String name, String surname, String phoneNumber, LocalDate birthdate, LocalDate startDate, double wage, int workHours, double tip) {
        super(empID, name, surname, phoneNumber, birthdate, startDate, wage, workHours);
        setTip(tip);

        getModifiableExtent().add(this);
    }

    @Override
    public double getPay() {
        return getWage()*getWorkHours()+tip;
    }

    @Override
    public double getTip() {
        return tip;
    }

    @Override
    public void setTip(double tip) {
        this.tip = tip;
    }
}
