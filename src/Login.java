import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login {

    private JTextField inputUser;
    private JButton okButton;
    private JLabel Password;
    private JCheckBox seePaswordCheckBox;
    private JPanel rootPanel;
    private JPasswordField inputPassword;
    static int user=0;
    static String pass="";
    public Login() {
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //conexion a la base
                Connection bd=ConectarBD();
                Consulta(bd);
                if

            }
        });
    }

    public static void Consulta(Connection con){

        String query="SELECT ID_EST, Password_EST FROM ESTUDIANTES";
        Statement statement;
        ResultSet resultSet;
        try {
            statement=con.createStatement();
            resultSet=statement.executeQuery(query);
            while (resultSet.next()){
                user=resultSet.getInt("ID_EST");
                pass=resultSet.getString("Password_EST");
                System.out.println("ID: "+user+"\nPassword: "+pass);
            }
        }catch (SQLException a){
            throw new RuntimeException();
        }
    }

    public static Connection ConectarBD(){
        //objeto tipo conexion
        Connection conexion;
        String db_url="jdbc:mysql://localhost/informacion";
        String user="root";
        String pass = "root_bas3";

        try {
            conexion= DriverManager.getConnection(db_url,user,pass);
            System.out.println("Conexion exitosa");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return conexion;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500,500);
    }
}
