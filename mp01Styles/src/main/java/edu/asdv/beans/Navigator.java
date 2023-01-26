/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package edu.asdv.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

/**
 *
 * @author Tucker
 */
@Named(value = "navigator")
@RequestScoped
public class Navigator {

    private String[] resultPages = {"a", "b", "defeat", "c", "victory"};
    int index;

    public String start() {
        return ("a");
    }
    
    public String home() {
        return ("index");
    }

    public String choosePage1() {

        int choice = (int) (Math.random() * 2);

        if (choice == 1) {
            return ("b");
        } else {
            return ("defeat");
        }

    }

    public String choosePage2() {
        int choice = (int) (Math.random() * 2);

        if (choice == 1) {
            return ("c");
        } else {
            return ("defeat");
        }
    }

    public String choosePage3() {
                int choice = (int) (Math.random() * 2);

        if (choice == 1) {
            return ("victory");
        } else {
            return ("defeat");
        }

    }

    public Navigator() {
    }

}
