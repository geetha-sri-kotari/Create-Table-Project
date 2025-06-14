import java.sql.*;  // importing sql package
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
//
// public class App {
//     public static String getDetails() {
//         String[] messages = {"Enter your id : ", "Enter your name : ", "Enter your age : "};
//         String[] results = new String[3];
//         String query = "insert into students values(";
//         for(int i=0; i<messages.length; i++){
//             results[i] = JOptionPane.showInputDialog(messages[i]);
//             query += results[i];
//         }
//         return query;
//     }
//     public static void main(String[] args) throws Exception {
//         String dbname = JOptionPane.showInputDialog("Enter a db name : ");
//         String url = "jdbc:mysql://localhost:3306/" + dbname;
//         String username = "root";
//         String password = "Saisunee@123";
//         // Establishing the connection
//         try {
//             Connection con = DriverManager.getConnection(url, username, password);
//             if(con != null) JOptionPane.showMessageDialog(null, "Successfully Connected");
//             // create a statement
//             Statement st = con.createStatement();
//             st.executeUpdate("insert into students values(5, 'Ram', 24)");
//         }
//         catch (SQLException e){
//             System.out.println(e.getMessage());
//        
//     }
// }


public class App {
    public static String getFieldDetails() {
        // enter fieldName, fieldType
        // "id int", "name varchar(20)", "cgpa float"
        String fieldName = JOptionPane.showInputDialog("Enter field name : ");
        String[] items = {"int", "float", "varchar"};
        JComboBox box = new JComboBox(items);
        JOptionPane.showMessageDialog(null, box);
        String fieldType = (String)box.getSelectedItem();
        if(fieldType.equals("varchar")) {
            String length = JOptionPane.showInputDialog("Enter maximum length of " + fieldName);
            fieldType += "(" + length + ")";
        }
        JOptionPane.showMessageDialog(null, fieldName + "\n" + fieldType);
        return fieldName + " " + fieldType;
    }
    public static String constructQuery(String tableName) {
        String query = "create table " + tableName + "(";
        while(true) {
            int ch = JOptionPane.showConfirmDialog(null, "Do you want to add a field?", "Choose", JOptionPane.YES_NO_OPTION);
            if(ch == 0) {
                query += getFieldDetails() + ",";
            }
            else {
                break;
            }
            
        }
        char[] temp = query.toCharArray();
        temp[temp.length - 1] = ')';
        query = new String(temp);
        JOptionPane.showMessageDialog(null, query);
        return query;
    }
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/babadb1";
        String username = "root";
        String password = "Saisunee@123";
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            if(con != null) System.out.println("Connected Successfully");
            Statement st = con.createStatement();
            // executeUpdate() is capable of handling insert, delete, update queries
            // and also DDL commands (like create and alter)
            //st.executeUpdate("delete from students where id=4");
            String tableName = JOptionPane.showInputDialog("Enter a table name to be created : ");
            String sqlQuery = constructQuery(tableName);
            st.executeUpdate(sqlQuery);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

// Aim : to provide an interface that let's the user create any table without actually opening MySql database
// Some GUI compoments :
// javax.swing.* -> package
// JOptionPane -> used to give pop-up messages
// JComboBox -> drop-down menu