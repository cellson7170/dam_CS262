package main.java.dam;

//import sql libraries and package classes
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import main.java.classes.*;

public class DamPerson{
    private Database db;

    public DamPerson(Database db){
        this.db = db;
    }

    public Person selectPersonByID(String value){
        String sql = "SELECT * "
                        +" FROM "
                            + "Person.Person"
                        + " WHERE "
                            + "rowguid='"
                            + value
                            + "'";
        try(Connection connection = db.connection(); 
        Statement st = db.connection().createStatement();
        ){
            //execute my sql
            ResultSet rs = st.executeQuery(sql);

            //create person
            Person p = new Person();

            while(rs.next()){
                p.setFirstName(rs.getString("firstName"));
                p.setLastName(rs.getString("lastName"));
            }

            return p;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}