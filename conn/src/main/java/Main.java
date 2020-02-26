package main.java;
import main.java.dam.*;
import main.java.classes.*;
class Main{


    public static void main(final String[] args){
        final Database db = new Database(
            "server.berzirkhosting.com",
            "CGCC",
            1444,
            false,
            "AdventureWorks",
            "jdoe",
            "Password1234!"
            );
       // System.out.println(db.getConnectionURL());
        //db.connection();
        final Person p = new DamPerson(db).selectPersonByID("E8673E92-31F6-42F4-A65C-A595D3F899F6");
        final Department d = new DamDepartment(db).selectDepartmentByID(5);

        System.out.println(p.getFullName());
        System.out.println(d.getDeptName());
    }
}
