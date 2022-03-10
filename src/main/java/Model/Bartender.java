package Model;

import java.time.LocalDate;

public class Bartender extends Employee{
    public double bonuses;

    public Bartender(String empID, String name, String surname, String phoneNumber, LocalDate birthdate, LocalDate startDate, double wage, int workHours, double bonuses) {
        super(empID, name, surname, phoneNumber, birthdate, startDate, wage, workHours);
        setBonuses(bonuses);

        getModifiableExtent().add(this);
    }

    @Override
    public double getPay() {
        return getWage()*getWorkHours()+bonuses;
    }

    public double getBonuses() {
        return bonuses;
    }

    public void setBonuses(double bonuses) {
        this.bonuses = bonuses;
    }
}
