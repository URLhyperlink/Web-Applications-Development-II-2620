/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

/**
 *
 * @author idont
 */
@Named(value = "navigator")
@RequestScoped
public class Navigator {

    private String[] resultPages = {"a", "b", "defeat", "c", "victory"};
    int index;

    
    public String home() 
    {
        return ("index");
    }
    
    public String suppliersTable() 
    {
        return ("suppliers");
    }
    
    public String partTable() 
    {
        return ("part");
    }

   public String spTable() 
    {
        return ("sp");
    }
   
   

    public Navigator() {
    }

}
