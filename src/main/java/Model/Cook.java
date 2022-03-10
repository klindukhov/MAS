package Model;

import java.time.LocalDate;

public class Cook extends Employee{
    private CookPosition position;

    public Cook(String empID, String name, String surname, String phoneNumber, LocalDate birthdate, LocalDate startDate, double wage, int workHours, CookPosition position) {
        super(empID, name, surname, phoneNumber, birthdate, startDate, wage, workHours);
        setPosition(position);

        getModifiableExtent().add(this);
    }

    public double getPay(){
        return getWage()*getWorkHours();
    }


    public CookPosition getPosition() {
        return position;
    }

    public void setPosition(CookPosition position) {
        this.position = position;
    }
}
