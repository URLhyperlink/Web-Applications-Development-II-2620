/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package edu.slcc.asdv.suppliersparts;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.Connections;

/**
 *
 * @author idont
 */
@Named(value = "part")
@SessionScoped
public class Part implements Serializable {

    private static final Logger LOG = Logger.getLogger(Part.class.getName());

    String pnumber = "";
    String pname = "";    
    String city = "";
    String color = "";

    String result = "";

    public Part()
    {

    }

    public String getPnumber()
    {
        return pnumber;
    }

    public void setPnumber(String pnumber)
    {
        this.pnumber = pnumber;
    }
    
    public String getPname()
    {
        return pname;
    }

    public void setPname(String pname)
    {
        this.pname = pname;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }
    
    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }
    

    public String getConnectionResponse()
    {
        Connection con = Connections.getConnection();

        if (con != null)
        {
            return "<p style=\"color:green\">Connection succesfull! <br />";
        }
        else
        {

            return "<p style=\"color:red\">Connection failed! <br />";
        }

    }

    public void clear()
    {
        pnumber = "";
        pname = "";
        color = "";
        city = "";

        result = "";
    }

    public void listAll()
    {
        Connection con = Connections.getConnection();
        if (con == null)
        {
            result = "cannot connect to database";
            return;
        }
        String table = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlStr = "SELECT  *  FROM part";
        try
        {
            //prepare statement
            ps = con.prepareStatement(sqlStr);
            //execute
            rs = ps.executeQuery();
            while (rs.next())
            {
                String pNumber = rs.getString(1) + " ";
                String pName = rs.getString(2) + " ";
                String color = rs.getString(3) + " ";
                String city = rs.getString(4) + " ";
                table += pNumber + pName + color + city + "</br>";
            }
        }
        catch (Exception ex)
        {
            Logger.getLogger(Part.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                Connections.closeDatabaseConnection(con);
                // close the resources 
                if (ps != null)
                {
                    ps.close();
                }
            }
            catch (SQLException sqle)
            {
                Logger.getLogger(Part.class.getName()).log(Level.WARNING, null, sqle);
                sqle.printStackTrace();
            }
        }
        result = table;
    }

    public void viewPart()
    {
        Connection con = Connections.getConnection();
        if (con == null)
        {
            result = "cannot connect to database";
            return;
        }
        String ret = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlStr = "SELECT * FROM part WHERE pnumber=?";
        try
        {
            //prepare statement
            ps = con.prepareStatement(sqlStr);
            ps.setString(1, pnumber);
            //execute
            rs = ps.executeQuery();
            if (rs.next())
            {
                this.pnumber = rs.getString("pnumber");
                ret += this.pnumber + " ";
                this.pname = rs.getString("pname");
                ret += this.pname + " ";
                this.color = rs.getString("color");
                ret += this.color + " ";
                this.city = rs.getString("city");
                ret += this.city + " ";
                
            }
            else
            {
                ret = this.pnumber + " doesn't exist.";
            }
        }
        catch (Exception ex)
        {
            Logger.getLogger(Part.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                Connections.closeDatabaseConnection(con);
                if (ps != null)
                {
                    ps.close();
                }
            }
            catch (SQLException sqle)
            {
                Logger.getLogger(Part.class.getName()).log(Level.WARNING, null, sqle);

                sqle.printStackTrace();
            }
        }
        this.result = ret;
    }

    public void updatePart()
    {
        Connection con = Connections.getConnection();
        if (con == null)
        {
            result = "cannot connect to database";
            return;
        }
        PreparedStatement updatePart = null;
        try
        {

            updatePart = con.prepareStatement(
                    "UPDATE part SET pnumber=?, pname=?, color=?, city=? WHERE pnumber=?");

            updatePart.setString(1, pnumber);
            updatePart.setString(2, pname);
            updatePart.setString(3, color);          
            updatePart.setString(4, city);
            updatePart.setString(5, pnumber);
            int updateCount = updatePart.executeUpdate();

            result = "number of rows affected: " + updateCount;

        }
        catch (Exception ex)
        {
            Logger.getLogger(Part.class.getName()).log(Level.SEVERE, null, ex);

            System.err.println(ex.toString());
        }
        finally
        {
            try
            {
                Connections.closeDatabaseConnection(con);
                // close the resources 
                if (updatePart != null)
                {
                    updatePart.close();
                }

            }
            catch (SQLException sqlee)
            {
                sqlee.printStackTrace();
            }
        }
    }

    public void deletePart()
    {

        Connection con = Connections.getConnection();
        if (con == null)
        {
            result = "cannot connect to database";
            return;
        }
        PreparedStatement ps = null;
        int rowsAffected = -1;

        try
        {

            String query = "DELETE FROM part WHERE pnumber=? ";
            ps = con.prepareStatement(query);
            ps.setString(1, pnumber);
            rowsAffected = ps.executeUpdate();
            result = "number of rows affected: " + rowsAffected;

        }
        catch (Exception ex)
        {
            Logger.getLogger(Part.class.getName()).log(Level.SEVERE, null, ex);

            System.err.println(ex.toString());
        }
        finally
        {
            try
            {
                Connections.closeDatabaseConnection(con);
                // close the resources 
                if (ps != null)
                {
                    ps.close();
                }

            }
            catch (SQLException sqlee)
            {
                sqlee.printStackTrace();
            }
        }
    }

    public void insertPart()
    {
        Connection con = Connections.getConnection();
        if (con == null)
        {
            result = "cannot connect to database";
            return;
        }
        PreparedStatement updatePart = null;
        try
        {
            updatePart = con.prepareStatement(
                    "INSERT INTO part (pnumber, pname, color, city ) "
                    + "VALUES ( ?, ?, ?, ? )");
            updatePart.setString(1, pnumber);
            updatePart.setString(2, pname);
            updatePart.setString(3, color);
            updatePart.setString(4, city);

            int updateCount = updatePart.executeUpdate();

            result = "number of rows affected: " + updateCount;
        }
        catch (Exception ex)
        {
            Logger.getLogger(Part.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.toString());
            result = ex.toString();
        }
        finally
        {
            try
            {
                Connections.closeDatabaseConnection(con);
                // close the resources 
                if (updatePart != null)
                {
                    updatePart.close();
                }

            }
            catch (SQLException e)
            {

                System.err.println(e.toString());
            }
        }
    }


    public String getResult()
    {
        return "<p style=\"color:green\">Part <br />" + result;

    }

//    public void closeDatabaseConnection(Connection con)
//            throws SQLException
//    {
//        try
//        {
//            if (con != null)
//            {
//                con.close();
//            }
//        }
//        catch (SQLException e)
//        {
//            Logger.getLogger(Part.class.getName()).log(Level.WARNING, null, e);
//            result = e.toString();
//            e.printStackTrace();
//            throw e;
//        }
//    }
//
//    public Connection getConnection()
//    {
//        Connection con = null;
//
//        try
//        {
//            con = getConnection("supplier-parts", "root", "", "localhost", "3306", "com.mysql.cj.jdbc.Driver", "jdbc:mysql");
//            System.out.println("abc");
//
//        }
//        catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e)
//        {
//
//            FacesMessage msg = new FacesMessage(
//                    FacesMessage.SEVERITY_ERROR,
//                    "suppliesr parts cannot be loaded.", "");
//            LOG.log(Level.SEVERE, msg + "\n" + e.toString());
//            FacesContext context = FacesContext.getCurrentInstance();
//            context.addMessage(null, msg);
//
//        }
//        return con;
//    }
//
//    public Connection getConnection(
//            String databaseName, String userName, String password, String ip, String port, String connector, String driver)
//            throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
//    {
//        Connection con = null;
//
//        try
//        {// Load Sun's jdbc driver
//            Class.forName(connector).newInstance();
//            System.out.println("Connector Driver loaded!");
//        }
//        catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) // driver not found
//        {
//            Logger.getLogger(Part.class.getName()).log(Level.SEVERE, null, e);
//
//            throw e;
//        }
//
//        String url = driver + "://"
//                + ip + ":"
//                + port + "/"
//                + databaseName
//                + "?allowPublicKeyRetrieval=true&useSSL=false";
//
//        try
//        {
//            con = DriverManager.getConnection(url, userName, password);
//            con.setReadOnly(false);
//        }
//        catch (SQLException e)
//        {
//            Logger.getLogger(Part.class.getName()).log(Level.SEVERE, null, e);
//            throw e;
//        }
//
//        Logger.getLogger(Part.class.getName()).log(Level.INFO, null, "Connection successfull.");
//
//        return con;
//    }
}
    
