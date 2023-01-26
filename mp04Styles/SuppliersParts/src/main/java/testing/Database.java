
package testing;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Database implements Serializable
{

    String snumber = "";
    String sname = "";
    int status = 0;
    String birthday = "";
    String city = "";

    String result = "";

    public Database()
    {
        connection();
    }

    public String getSnumber()
    {
        return snumber;
    }

    public void setSnumber(String snumber)
    {
        this.snumber = snumber;
    }

    public String getSname()
    {
        return sname;
    }

    public void setSname(String sname)
    {
        this.sname = sname;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
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
        Connection con = connection();
        if (con == null)
          {
            result = "cannot connect to database" ;
            return null;
          }
        if (con != null)
          {
            return "<p style=\"color:green\">Connection succesfull! <br />";
          }
        else
          {
            connection();
            return "<p style=\"color:red\">Connection failed! <br />";
          }

    }

    private Connection connection() //throws InstantiationException, IllegalAccessException
    {

        String databaseName = "supplier-parts";
        String userName = "root";
        String password = "";
        String URL2 = "com.mysql.jdbc.Driver";
        Connection con = null;
        
        try
          {// Load Sun's jdbc driver
            Class.forName(URL2).newInstance();
            System.out.println("JDBC Driver loaded!");
          }
        catch (Exception e) // driver not found
          {
            System.err.println("Unable to load database driver");
            System.err.println("Details : " + e);
            return null;
          }
        
        String ip = "localhost"; //internet connection
        String url = "jdbc:mysql://" + ip + ":8889/" + databaseName;
        try
          {
            con = DriverManager.getConnection(url, userName, password);
            con.setReadOnly(false);
          }
        catch (Exception e)
          {
            System.err.println(" *** " + e.toString());
            return null;
          }
        System.out.println("+++  connection successfull");
        return con;
    }


    public void clear()
    {
        status = 0;
        snumber = "";
        sname = "";
        birthday = "";
        city = "";
        result = "";
    }

    public void listAll()
    {
        Connection con = connection();
        if (con == null)
          {
            result = "cannot connect to database" ;
            return ;
          }
        String table = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlStr = "SELECT  *  FROM supplier";
        try
          {
            //prepare statement
            ps = con.prepareStatement(sqlStr);
            //execute
            rs = ps.executeQuery();
            while (rs.next())
              {
                String sNumber = rs.getString(1) + " ";
                String sName = rs.getString(2) + " ";
                String status = rs.getInt(3) + " ";
                String bdate = rs.getDate(4) + " ";
                String city = rs.getString(5) + " ";
                table += sNumber + sName + bdate + status + city + "</br>";
              }
          }
        catch (Exception ex)
          {
            ex.printStackTrace();
          }
        finally
          {
            try
              {
                closeDatabaseConnection(  con);
                // close the resources 
                if (ps != null)
                  {
                    ps.close();
                  }
              }
            catch (SQLException sqle){ sqle.printStackTrace(); }
          }
        result = table;
    }

    public void viewSupplier()
    {
        Connection con = connection();
        if (con == null)
          {
            result = "cannot connect to database" ;
            return ;
          }
        String ret = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlStr = "SELECT *  FROM supplier WHERE snumber=?";
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
                this.sname = rs.getString("sname");
                ret += this.sname + " ";
                this.status = rs.getInt("status");
                ret += this.status + " ";
                this.city = rs.getString("city");
                ret += this.city + " ";
                
                Object bDate = rs.getObject("birthday");
                this.birthday = bDate.toString();
                ret += this.birthday;                 
              }
            else
                ret = this.snumber + " doesn't exist.";
           }
        catch (Exception ex){ex.printStackTrace();}
        finally
          {
            try
              {
                this.closeDatabaseConnection(con);
                if (ps != null)
                    ps.close();
              }
            catch (SQLException sqle){ sqle.printStackTrace();}
          }
        this.result = ret;
    }

    public void updateSupplier()
    {
        Connection con = connection();
        if (con == null)
          {
            result = "cannot connect to database" ;
            return ;
          }
        PreparedStatement updateSupplier = null;
        try
          {

            updateSupplier = con.prepareStatement(
                    "UPDATE supplier SET snumber=?, sname=?, status=?, birthday=?, city=? WHERE snumber=?");


            updateSupplier.setString(1, snumber);
            updateSupplier.setString(2, sname);
            updateSupplier.setInt(3, status);
             java.sql.Date  bd = stringDateToSqlDate(this.birthday);
            updateSupplier.setDate(4, bd );
            updateSupplier.setString(5, city);
            updateSupplier.setString(6, snumber);
            int updateCount = updateSupplier.executeUpdate();

            result = "number of rows affected: " + updateCount;

          }
        catch (Exception ex)
          {
            System.err.println(ex.toString());
          }
        finally
          {
            try
              {
                this.closeDatabaseConnection(con);
                // close the resources 
                if (updateSupplier != null)
                  {
                    updateSupplier.close();
                  }

              }
            catch (SQLException sqlee)
              {
                sqlee.printStackTrace();
              }
          }
    }

    public void deleteSupplier()
    {
        
        Connection con = connection();
        if (con == null)
          {
            result = "cannot connect to database" ;
            return ;
          }
        PreparedStatement ps = null;
        int rowsAffected = -1;

        try
          {
            String query = "DELETE FROM supplier WHERE snumber=? ";
            ps = con.prepareStatement(query);
            ps.setString(1, snumber);
            rowsAffected = ps.executeUpdate();
            result = "number of rows affected: " + rowsAffected;
          }
        catch (Exception ex)
          {
            System.err.println(ex.toString());
          }
        finally
          {
            try
              {
                this.closeDatabaseConnection(con);
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

    public void insertSupplier()
    {
       Connection con = connection();
        if (con == null)
          {
            result = "cannot connect to database" ;
            return ;
          }
        PreparedStatement updateSupplier = null;
        try
          {
            updateSupplier = con.prepareStatement(
                    "INSERT INTO supplier (snumber, sname, status, birthday, city ) "
                    + "VALUES ( ?, ?, ? , ? ,? )");
            updateSupplier.setString(1, snumber);
            updateSupplier.setString(2, sname);
            updateSupplier.setInt(3, status);
            java.sql.Date sqlDate = stringDateToSqlDate(birthday);
            updateSupplier.setDate(4, sqlDate);
            updateSupplier.setString(5, city);

            int updateCount = updateSupplier.executeUpdate();

            result = "number of rows affected: " + updateCount;
          }
        catch (Exception ex)
          {
            System.err.println(ex.toString());
            result = ex.toString();
          }
        finally
          {
            try
              {
                this.closeDatabaseConnection(con);
                // close the resources 
                if (updateSupplier != null)
                  {
                    updateSupplier.close();
                  }

              }
            catch (SQLException e)
              {
                System.err.println(e.toString());
                result = e.toString();
              }
          }
    }
private java.sql.Date stringDateToSqlDate(String sDate)
    {
        java.util.Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try
          {
            date = sdf.parse(sDate);
          }
        catch (ParseException e)
          {
            e.printStackTrace();
          }
        return new  java.sql.Date( date.getTime() );
        
    }

    public String getResult()
    {
        return "<p style=\"color:green\">Suppliers <br />" + result;

    }

    

    public void closeDatabaseConnection( Connection con)
    {
        try
          {
            if (con != null)
              {
                con.close();
              }
          }
        catch (SQLException e)
          {
            result = e.toString();
            e.printStackTrace();
          }
    }
    
    public static void main(String[] args)
    {
        Database db = new Database();
        db.connection();
    }
}
