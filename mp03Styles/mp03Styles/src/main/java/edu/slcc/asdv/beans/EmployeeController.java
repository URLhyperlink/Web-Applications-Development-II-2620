/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package edu.slcc.asdv.beans;

import edu.slcc.asdv.bl.Company;
import edu.slcc.asdv.bl.Employee;
import edu.slcc.asdv.bl.EmployeeInterface;
import edu.slcc.asdv.bl.Localable;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * @author juneau
 */
@Named("employeeController")
@SessionScoped
public class EmployeeController implements Serializable, Localable {

    private boolean isSortAscending = true;
    private String employeeFirst;
    private String employeeLast;
    private String employeePhone;
    private String employeeEmail;
    private String employeeHome;
    private List <Employee> employeeList;

    EmployeeInterface company;

    public EmployeeController() {
        company = new Company();
    }
    
    public EmployeeInterface getCompany()
    {
        return company;
    }

    /**
     * @return the employeeFirst
     */
    public String getEmployeeFirst() {
        return employeeFirst;
    }

    /**
     * @param employeeFirst the employeeFirst to set
     */
    public void setEmployeeFirst(String employeeFirst) {
        this.employeeFirst = employeeFirst;
    }

    /**
     * @return the employeeLast
     */
    public String getEmployeeLast() {
        return employeeLast;
    }

    /**
     * @param employeeLast the employeeLast to set
     */
    public void setEmployeeLast(String employeeLast) {
        this.employeeLast = employeeLast;
    }

    /**
     * @return the employeePhone
     */
    public String getEmployeePhone() {
        return employeePhone;
    }

    /**
     * @param employeePhone the employeePhone to set
     */
    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }
    
    public String getEmployeeEmail() {
        return employeeEmail;
    }

    /**
     * @param employeeEmail the employeeEmail to set
     */
    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }
    
    public String getEmployeeHome() {
        return employeeHome;
    }

    /**
     * @param employeeHome the employeeHome to set
     */
    public void setEmployeeHome(String employeeHome) {
        this.employeeHome = employeeHome;
    }
    

    public void insert() 
    {
        boolean b = company.insertEmployee(new Employee(this.employeeFirst, this.employeeLast, this.employeePhone, this.employeeEmail, this.employeeHome));
        
        FacesMessage facesMsg = null;
        
        if (b) 
        {
            facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee Successfully Added", null);
            this.employeeFirst = "";
            this.employeeLast = "";
            this.employeePhone = "";
            this.employeeEmail = "";
            this.employeeHome = "";
            
        }
        else
        {
            facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee Not Added", null);
        }
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        
    }
    
    public void remove()
    {
      company.removeEmployee(new Employee(this.getEmployeeFirst(), this.getEmployeeLast()));
    }
    
    public void find()
    {    
        Employee query = company.findEmployee(new Employee(this.employeeFirst, this.employeeLast));       
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(query.toString()));
       
    }
    

    
    
    public List<Employee> sortByLastName() {
    if(isSortAscending){
      company.getEmployees().sort((Employee e1, Employee e2) -> e1.getEmployeeLast().compareTo(e2.getEmployeeLast()));
      isSortAscending = false;
    }else{
      //descending book
      company.getEmployees().sort(new Comparator<Employee>() {
        @Override
        public int compare(Employee e1, Employee e2) {
          return e2.getEmployeeLast().compareTo(e1.getEmployeeLast());
        }
      });
      isSortAscending = true;
    }
    return null;
  }

    @Override
    public Locale locale() {
        return FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }

    @Override
    public String greekLocale() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale( "el"));
        return null;
    }

    @Override
    public String englishLocale() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale( "en"));
        return null;
    }

    @Override
    public String russianLocale() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale( "ru"));
        return null;
    }

    @Override
    public String frenchLocale() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale( "fr"));
        return null;
    }

    @Override
    public String arabicLocale() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale( "ar"));
        return null;
    }

    @Override
    public String germanLocale() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale( "de"));
        return null;
    }

    @Override
    public String spanishLocale() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale( "es"));
        return null;
    }

    @Override
    public String chineseLocale() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale( "zh"));
        return null;
    }
    

}