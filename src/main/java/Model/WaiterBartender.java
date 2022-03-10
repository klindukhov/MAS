package Model;

import java.time.LocalDate;

public class WaiterBartender extends Bartender implements Waiter{
    public double tip;

    public WaiterBartender(String empID, String name, String surname, String phoneNumber, LocalDate birthdate, LocalDate startDate, double wage, int workHours, double bonuses, double tip) {
        super(empID, name, surname, phoneNumber, birthdate, startDate, wage, workHours, bonuses);
        setTip(tip);

        getModifiableExtent().add(this);

    }

    @Override
    public double getPay() {
        return super.getPay()+tip;
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
