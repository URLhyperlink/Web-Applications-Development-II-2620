/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.slcc.asdv.bl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author asdv
 */
public class Company implements EmployeeInterface, Comparator<Employee> {

    private List<Employee> employeeList;

    public Company() {
        employeeList = new ArrayList<Employee>();
        populateEmployeeList();
    }

    private void populateEmployeeList() {
        employeeList.add(new Employee("Saul", "Goodman", "503-4455", "saulgoodman@associates.com", "9800 Montgomery Blvd NE, Albuquerque, New Mexico 87111"));
        employeeList.add(new Employee("Marjorie", "Keck", "928-4395", "themarjpluskeck@gmail.com", "4942 Grant Street Sherman, TX 75090"));
        employeeList.add(new Employee("Justin", "Davis", "555-1799", "justinjdavis@yahoo.com", "4301 Woodbridge Lane Pontiac, MI 48342"));
        employeeList.add(new Employee("Richard", "Stewart", "621-4887", "richierich@bellsouth.net", "4088 Vine Street Wheeling, IL 60090"));

    }

    @Override
    public List sortAscending(List<Employee> list) {
        for (int i = 0; i < list.size(); i++) {
            Employee temp;
            for (int j = i + 1; j < list.size(); j++) {
                if ((list.get(i)).compareTo(list.get(j)) > 0) {
                    temp = list.get(j);
                    list.set(j, list.get(i));
                    list.set(i, temp);
                }

            }
        }
        return list;
    }

    @Override
    public List sortDescending(List<Employee> list) {
        for (int i = 0; i < list.size(); i++) {
            Employee temp;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).compareTo(list.get(i)) > 0) {
                    temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }

            }
        }
        return list;
    }

    @Override
    public boolean insertEmployee(Employee e) {
        return employeeList.add(e);
    }

    @Override
    public boolean removeEmployee(Employee e) {
        Employee info;
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getEmployeeLast().equals(e.getEmployeeLast())) {
                employeeList.remove(i);
                return true;

            }
        }
        return false;

    }

    @Override
    public Employee findEmployee(Employee e) {
        Employee info;
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).getEmployeeLast().equals(e.getEmployeeLast())) {
                info = employeeList.get(i);
                return info;
            }
        }
        return null;

    }

    @Override
    public List<Employee> getEmployees() {
        return employeeList;
    }

    @Override
    public int compare(Employee e1, Employee e2) {
        return e1.getEmployeeLast().compareToIgnoreCase(e2.getEmployeeLast());
    }

    
};
