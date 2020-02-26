package main.java.classes;

    public class Department{

        //attributes
        private int deptID;
        private String deptName;

        //member functions
        public void setDeptID(int value){
            this.deptID = value;
        }

        public void setDeptName(String value){
            this.deptName = value;
        }

        public int getDeptID(){
            return deptID;
        }

        public String getDeptName(){
            return deptName;
        }
    }

