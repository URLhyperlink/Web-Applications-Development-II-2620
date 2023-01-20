/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.slcc.asdv.bl;

import jakarta.faces.context.FacesContext;
import java.util.Locale;

/**
 *
 * @author Tucker
 */
public interface Localable 
{
    public Locale locale();
    
    public String greekLocale();
    
    public String englishLocale();
    
    public String russianLocale();
    
    public String frenchLocale();
    
    public String arabicLocale();

    public String germanLocale();
    
    public String spanishLocale();
    
    public String chineseLocale();
  
}
