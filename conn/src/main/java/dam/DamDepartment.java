package main.java.dam;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import main.java.classes.*;

public class DamDepartment{
    private Database db;

	public DamDepartment(Database db) {
        this.db = db;
    }

    public Department selectDepartmentByID(int value){
        String sql = "SELECT * "
                        +" FROM "
                            + "HumanResources.Department"
                        + " WHERE "
                            + "DepartmentID='"
                            + value
                            + "'";
        try(Connection connection = db.connection(); 
        Statement st = db.connection().createStatement();
        ){
            //execute my sql
            ResultSet rs = st.executeQuery(sql);

            //create department
            Department d = new Department();

            while(rs.next()){
                d.setDeptID(rs.getInt("DepartmentID"));
                d.setDeptName(rs.getString("Name"));
            }

            return d;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}