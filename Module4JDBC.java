import java.sql.*;
import java.util.Scanner;

public class Module4JDBC {
    public static void main (String[] args) throws ClassNotFoundException, SQLException {
        Scanner input = new Scanner (System.in);
        System.out.println("What would you like to see out of the three inventorys: classic cars, ships," +
                "or motorcyles?");
        String vehicle = input.nextLine();

        //Use a try/catch block to catch any database exceptions
        try {
            //Create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

            //Create a new SQL statement using the connection
            Statement stmt = con.createStatement();

            //Execute the SQL statement to retrieve a result set of records from the database.
            ResultSet rs = stmt.executeQuery("select * from products where productline = 'Classic Cars'");
            // This SQL query will retrieve all the records in the products table

            //Use a while loop to literate through the result set of records
            while (rs.next()) {
                //print out the data in each of the column in the current record
                //The first column contains an String, so we are calling the getString method
                System.out.println(rs.getString(1));

            } //end while loop

            //Insert a record in the database
           // stmt.executeUpdate("INSERT INTO PRODUCTS \n" +
                 //   "(productCode, productName, productLine, productScale, productVendor, productDescription, quantityInStock, buyPrice, MSRP)\n" +
                 //   "VALUES \n" +
                 //   "(\"S72_3213\",\"USS Monitor\", \"Ships\", \"1:72\", \"Second Gear Diecast\", \"Ironclad warship with her steam engine and revolving turret\", 780, 35.00, 55.00);\n");


            rs = stmt.executeQuery("select * from products where productname = 'USS Monitor'");
            while (rs.next()){
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            } // end while loop

            stmt.executeUpdate("UPDATE products SET productScale = '1:10' WHERE productCode = 'S72_3213'");

            rs = stmt.executeQuery("select * from products where productname = 'USS Monitor'");
            while (rs.next()){
                System.out.println(rs.getString(4) + " " + rs.getString(2));
            } // end while loop

            //Delete a record from the database
           // stmt.executeUpdate("DELETE FROM Products WHERE productCode = 'S72_3213'");

            //Close the connection to the database
             con.close();

        }catch(Exception e){

            System.out.println(e);

        } //end try catch

        try{
            //Create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/classicmodels", "root", "admin");


            //Create a new SQL statement using the connection
            Statement stmt = con.createStatement();

            //Prompt user to enter product code
          System.out.println("Enter your product code");
            String userProductCode = input.nextLine();


            //Execute the SQL statement to retrieve a result set of records from the database.
            ResultSet rs = stmt.executeQuery("select * from products where productCode = '" + userProductCode +"'");

            while (rs.next()){
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            } // end while loop

            con.close();
        } catch(Exception e){

            System.out.println(e);

            // try catch
        }
    } //end main method
} //end class
