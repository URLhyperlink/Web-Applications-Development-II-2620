package edu.slcc.asdv.bl;

/**
 *
 * @author asdv
 */

public class Employee implements Comparable<Employee>
{
    
    private String employeeFirst;
    private String employeeLast;
    private String employeePhone;
    private String employeeHome;
    private String photo;
    private String employeeEmail;

    public Employee(String employeeFirst, String employeeLast, String employeePhone, String employeeEmail, String employeeHome) {
        this.employeeFirst = employeeFirst;
        this.employeeLast = employeeLast;
        this.employeePhone = employeePhone;
        this.employeeEmail = employeeEmail;
        this.employeeHome = employeeHome;
        this.photo = (int) (1 + Math.random() * 11) + ".png" ;
    }

    public Employee(String employeeFirst, String employeeLast) {
        this.employeeFirst = employeeFirst;
        this.employeeLast = employeeLast;
        
    }
    
    public String getEmployeeFirst() {
        return employeeFirst;
    }

    public void setEmployeeFirst(String employeeFirst) {
        this.employeeFirst = employeeFirst;
    }

    public String getEmployeeLast() {
        return employeeLast;
    }

    public void setEmployeeLast(String employeeLast) {
        this.employeeLast = employeeLast;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }     

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }


    public String getEmployeeHome() {
        return employeeHome;
    }

    public void setEmployeeHomeAddress(String employeeHome) {
        this.employeeHome = employeeHome;
    }

    
    public String getPhoto()
    {
        return photo;
    }
    
    public String setPhoto(String photo)
    {
        return this.photo = photo;
    }

     @Override
    public int compareTo(Employee e) 
    {
        return this.employeeLast.compareToIgnoreCase(e.employeeLast);
    }

    
    @Override
    public boolean equals(Object o) 
    {
        if (o instanceof Employee == false)
        {
            return false;
        }
        else if (this.employeeLast.compareToIgnoreCase(((Employee)o).employeeLast) == 0) 
        {
            return true;
        }
        return false;
    }
    
    
    @Override
    public String toString() {
        return "Employee{" + employeeFirst + " " + employeeLast + ", Employee Phone: " + employeePhone + '}';
    }

    
    
    
    
    



}