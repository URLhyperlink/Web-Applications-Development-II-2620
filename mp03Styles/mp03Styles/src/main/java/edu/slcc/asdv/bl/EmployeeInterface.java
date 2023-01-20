/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.slcc.asdv.bl;

import java.util.List;

/**
 *
 * @author asdv
 */
public interface EmployeeInterface 
{
    boolean insertEmployee(Employee e);
    
    boolean removeEmployee(Employee e);
    
    Employee findEmployee(Employee e);
    
    List<Employee> getEmployees();

    public List<Employee> sortAscending(List<Employee> list);
    
    public List<Employee> sortDescending(List<Employee> list);
}
