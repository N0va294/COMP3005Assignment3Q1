//Student Name: Michael MacLean
//Student Number: 101262949

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    static Connection connection;
    //static variable used for connection to access from other static functions
    public static void getAllStudents(){
        //function that returns list of all students in the database
            try{
                Statement statement = connection.createStatement();
                statement.executeQuery("SELECT * FROM Students");
                ResultSet resultSet = statement.getResultSet();
                while(resultSet.next()){
                    System.out.print(resultSet.getString("student_id") + " \t");
                    System.out.print(resultSet.getString("first_name") + " \t");
                    System.out.print(resultSet.getString("last_name")+ " \t");
                    System.out.print(resultSet.getString("email")+ " \t");
                    System.out.println(resultSet.getString("enrollment_date"));
                    //loop continues through result set to fetch all strings. tab characters added for formating.
                }
            }
            catch (Exception e){System.out.println(e);}
    }
    public static void addStudent(String first_name, String last_name, String email, String enrollment_date){
        //adds a student to the database using and requiring their first name, last name, email, and enrollment date.
        try{
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO Students(first_name, last_name, email, enrollment_date) VALUES (%s, %s, %s, %s)", first_name, last_name, email, enrollment_date);
            //utilizes string formating so that its easy and comprehensive to create a string containing the unique values held by the variables inputted to the function.
            statement.executeUpdate(query);
            //updates database using unique query.
            System.out.println("Successfully added student.");
        }
        catch (Exception e){System.out.println(e);}
    }
    public static void updateStudentEmail(int student_id, String new_email){
        //updates an existing students email with a new email.
        try{
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE Students SET email = %s WHERE student_id = %d", new_email, student_id);
            //string formatting used to create string with inputted student id and new email.
            statement.executeUpdate(query);
            //updates database using unique query.
            System.out.println("Successfully updated student email.");
        }
        catch (Exception e){System.out.println(e);}
    }
    public static void deleteStudent(int student_id){
        //deletes an existing student from the database.
        try{
            Statement statement = connection.createStatement();
            String query = String.format("DELETE FROM Students WHERE student_id = %d", student_id);
            //string formatting used to create string with inputted student id.
            statement.executeUpdate(query);
            //updates database using unique query.
            System.out.println("Successfully removed student.");
        }
        catch (Exception e){System.out.println(e);}
    }
    public static void main(String[] args){
        //connects to database and runs test on all functions.
        String url = "jdbc:postgresql://localhost:5432/Assignment3";
        String user = "postgres";
        //username for server, change if needed.
        String password = "admin";
        //password for server, change if needed.
        
        //connects to database called "Assignment3" and uses your postgres servers username and password.

        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            //uses driver to coonect to database
            if(connection != null){
                System.out.println("Connected to the database");
                getAllStudents();
                //tests getAllStudents()
                addStudent("'test'", "'test'", "'test.test@example.com'", "'2025-11-06'");
                getAllStudents();
                //tests addStudent()
                updateStudentEmail(4, "'new.email.test@example.com'");
                getAllStudents();
                //tests updateStudentEmail()
                deleteStudent(4);
                getAllStudents();
                //tests deleteStudent()
            }
            else{
                System.out.println("Failed to connect to the database");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
