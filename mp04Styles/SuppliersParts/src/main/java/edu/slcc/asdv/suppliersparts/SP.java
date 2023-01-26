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
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.Connections;

/**
 *
 * @author idont
 */
@Named(value = "sp")
@SessionScoped
public class SP implements Serializable {

    private static final Logger LOG = Logger.getLogger(SP.class.getName());

    String snumber = "";
    String pnumber = "";
    int qty = 0;

    String result = "";

    public SP()
    {

    }

    public String getSnumber()
    {
        return snumber;
    }

    public void setSnumber(String snumber)
    {
        this.snumber = snumber;
    }

    public String getPnumber()
    {
        return pnumber;
    }

    public void setPnumber(String pnumber)
    {
        this.pnumber = pnumber;
    }

    public int getQty()
    {
        return qty;
    }

    public void setQty(int qty)
    {
        this.qty = qty;
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
        qty = 0;
        snumber = "";
        pnumber = "";

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
        String sqlStr = "SELECT  *  FROM sp";
        try
        {
            //prepare statement
            ps = con.prepareStatement(sqlStr);
            //execute
            rs = ps.executeQuery();
            while (rs.next())
            {
                String sNumber = rs.getString(1) + " ";
                String pNumber = rs.getString(2) + " ";
                String qty = rs.getInt(3) + " ";
                table += sNumber + pNumber + qty + "</br>";
            }
        }
        catch (Exception ex)
        {
            Logger.getLogger(SP.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(SP.class.getName()).log(Level.WARNING, null, sqle);
                sqle.printStackTrace();
            }
        }
        result = table;
    }

    public void viewSP()
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
        String sqlStr = "SELECT *  FROM sp WHERE snumber=?";
        try
        {
            //prepare statement
            ps = con.prepareStatement(sqlStr);
            ps.setString(1, snumber);
            //execute
            rs = ps.executeQuery();
            if (rs.next())
            {
                this.snumber = rs.getString("snumber");
                ret += this.snumber + " ";
                this.pnumber = rs.getString("pnumber");
                ret += this.pnumber + " ";
                this.qty = rs.getInt("qty");
                ret += this.qty + " ";
                
            }
            else
            {
                ret = this.snumber + " doesn't exist.";
            }
        }
        catch (Exception ex)
        {
            Logger.getLogger(SP.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(SP.class.getName()).log(Level.WARNING, null, sqle);

                sqle.printStackTrace();
            }
        }
        this.result = ret;
    }

    public void updateSP()
    {
        Connection con = Connections.getConnection();
        if (con == null)
        {
            result = "cannot connect to database";
            return;
        }
        PreparedStatement updateSP = null;
        try
        {

            updateSP = con.prepareStatement(
                    "UPDATE sp SET snumber=?, pnumber=?, qty=? WHERE snumber=?");

            updateSP.setString(1, snumber);
            updateSP.setString(2, pnumber);
            updateSP.setInt(3, qty);
            updateSP.setString(4, snumber);
//            updateSP.setString(5, pnumber);
//            updateSP.setInt(6, qty);
            int updateCount = updateSP.executeUpdate();

            result = "number of rows affected: " + updateCount;

        }
        catch (Exception ex)
        {
            Logger.getLogger(SP.class.getName()).log(Level.SEVERE, null, ex);

            System.err.println(ex.toString());
        }
        finally
        {
            try
            {
                Connections.closeDatabaseConnection(con);
                // close the resources 
                if (updateSP != null)
                {
                    updateSP.close();
                }

            }
            catch (SQLException sqlee)
            {
                sqlee.printStackTrace();
            }
        }
    }

    public void deleteSP()
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

            String query = "DELETE FROM sp WHERE snumber=? ";
            ps = con.prepareStatement(query);
            ps.setString(1, snumber);
            rowsAffected = ps.executeUpdate();
            result = "number of rows affected: " + rowsAffected;

        }
        catch (Exception ex)
        {
            Logger.getLogger(SP.class.getName()).log(Level.SEVERE, null, ex);

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

    public void insertSP()
    {
        Connection con = Connections.getConnection();
        if (con == null)
        {
            result = "cannot connect to database";
            return;
        }
        PreparedStatement updateSP = null;
        try
        {
            updateSP = con.prepareStatement(
                    "INSERT INTO sp (snumber, pnumber, qty ) "
                    + "VALUES ( ?, ?, ? )");
            updateSP.setString(1, snumber);
            updateSP.setString(2, pnumber);
            updateSP.setInt(3, qty);

            int updateCount = updateSP.executeUpdate();

            result = "number of rows affected: " + updateCount;
        }
        catch (Exception ex)
        {
            Logger.getLogger(SP.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.toString());
            result = ex.toString();
        }
        finally
        {
            try
            {
                Connections.closeDatabaseConnection(con);
                // close the resources 
                if (updateSP != null)
                {
                    updateSP.close();
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
        return "<p style=\"color:green\">SP <br />" + result;

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
//            Logger.getLogger(SP.class.getName()).log(Level.WARNING, null, e);
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
//            Logger.getLogger(SP.class.getName()).log(Level.SEVERE, null, e);
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
//            Logger.getLogger(SP.class.getName()).log(Level.SEVERE, null, e);
//            throw e;
//        }
//
//        Logger.getLogger(SP.class.getName()).log(Level.INFO, null, "Connection successfull.");
//
//        return con;
//    }
}
    
