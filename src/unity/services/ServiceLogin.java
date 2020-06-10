package unity.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import unity.utils.MyConnection;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.DriverManager;
import unity.entities.Utilisateur;

public class ServiceLogin {

   private static Connection connexion = null;

    public static Connection creationConnexion() {
        String dbName = "solidarite";
        String userName = "root";
        String password = "";
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connexion = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connexion;
    }
    
    public static int Inscription(Utilisateur utilisateur) {
        // Define the BCrypt workload to use when generating password hashes. 10-31 is a valid value en regarde le cryptage de fosuserbundle il utilise $2y$13$.
        int workload = 13;
        int status = 0;
        String sql = "INSERT INTO users(username,password,email,roles,enabled,username_canonical,email_canonical) VALUES(?,?,?,?,?,?,?)";

        try {
            Connection connexion = ServiceLogin.creationConnexion();
            PreparedStatement preparedStatement = (PreparedStatement) connexion.prepareStatement(sql);
            preparedStatement.setString(1, utilisateur.getNom_Utilisateur());
            String mdp = BCrypt.hashpw(utilisateur.getMotDePasse_Utilisateur(), BCrypt.gensalt(workload));
            preparedStatement.setString(2, mdp.replaceFirst("2a", "2y"));
            preparedStatement.setString(3, utilisateur.getEmail());
            preparedStatement.setString(4, "a:0:{}");
            preparedStatement.setInt(5, 1);
            preparedStatement.setString(6, utilisateur.getNom_Utilisateur());
            preparedStatement.setString(7, utilisateur.getEmail());
            status = preparedStatement.executeUpdate();
            connexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static boolean testMotDePasse(String motDePasseGUI, String motDePasseBD) {
        boolean password_verified = false;

        if (null == motDePasseBD) {
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }

        // en remplaçant 2y par 2a le cryptage on obtient le cryptage par defaut pour que la methode checkpw puisse comparer
        password_verified = BCrypt.checkpw(motDePasseGUI, motDePasseBD.replaceFirst("2y", "2a"));

        return (password_verified);
    }

    public static List<Utilisateur> getTtUtilisateur() {
        List<Utilisateur> list = new ArrayList<Utilisateur>();
        try {
            String sql = "select * from users  ";
            Connection connexion = ServiceLogin.creationConnexion();
            PreparedStatement preparedStatement = (PreparedStatement) connexion.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setId_Utilisateur(resultSet.getInt("id"));
                utilisateur.setNom_Utilisateur(resultSet.getString("username"));
                utilisateur.setMotDePasse_Utilisateur(resultSet.getString("password"));

                list.add(utilisateur);
            }
            connexion.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;

    }
    
     public static List<Utilisateur> getTtUtilisRef() {
        List<Utilisateur> list = new ArrayList<Utilisateur>();
        try {
            String sql = "select * from users where roles LIKE 'a:0:{}'";;
            Connection connexion = ServiceLogin.creationConnexion();
            PreparedStatement preparedStatement = (PreparedStatement) connexion.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setId_Utilisateur(resultSet.getInt("id"));
                utilisateur.setNom_Utilisateur(resultSet.getString("username"));
                utilisateur.setMotDePasse_Utilisateur(resultSet.getString("password"));

                list.add(utilisateur);
            }
            connexion.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;

    }
    

    public static Utilisateur getUtilisateur(String nomUtilisateur) {
        Utilisateur utilisateur = null;
        try {
            String sql = "select * from users where username = ?";
            Connection connexion = ServiceLogin.creationConnexion();
            PreparedStatement preparedStatement = (PreparedStatement) connexion.prepareStatement(sql);
            preparedStatement.setString(1, nomUtilisateur);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                utilisateur = new Utilisateur();

                utilisateur.setId_Utilisateur(resultSet.getInt("id"));
                utilisateur.setNom_Utilisateur(resultSet.getString("username"));
                utilisateur.setEmail(resultSet.getString("email"));
                utilisateur.setRole_Utilisateur(resultSet.getString("roles"));
            }

            connexion.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return utilisateur;

    }
}
