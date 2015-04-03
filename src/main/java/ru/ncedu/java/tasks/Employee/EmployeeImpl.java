package ru.ncedu.java.tasks.Employee;


public class EmployeeImpl implements Employee{

    private String firstName;
    private String lastName;
    private int salary = 1000;
    private Employee manager;

    public EmployeeImpl() {
        this("", "");
    }

    public EmployeeImpl(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int getSalary() {
        return this.salary;
    }

    @Override
    public void increaseSalary(int value) {
        this.salary += value;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public void setManager(Employee manager) {
        if (!this.equals(manager)) {
            this.manager = manager;
        }
    }

    @Override
    public String getManagerName() {
        if (this.manager == null)
            return "No manager";
        return this.manager.getFullName();
    }

    @Override
    public Employee getTopManager() {
        return (!getManagerName().equals("No manager")) ? manager.getTopManager() : this;
    }

}
