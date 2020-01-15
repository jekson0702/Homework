import java.sql.*;

public class MailData {
    private String login;
    private String password;
    private String addresses;
    private String messageText;

    public String getPassword() {
        return password;
    }

    public String getAddresses() {
        return addresses;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getLogin() {
        return login;
    }

    public void setMailDataFromDataBase() {
        String url = "jdbc:mysql://localhost:3306/user?useSSL=false&useUnicode=true&serverTimezone=UTC";
        String user = "root";
        String password = "123456";
        String query = "SELECT * FROM mail.maildata;";
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                this.login = rs.getString(1);
                this.password = rs.getString(2);
                this.addresses = rs.getString(3);
                this.messageText = rs.getString(4);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}