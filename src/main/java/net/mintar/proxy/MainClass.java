package net.mintar.proxy;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.mintar.proxy.Commands.*;
import net.mintar.proxy.Listeners.UtilListeners;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainClass extends Plugin {

    public static Plugin plugin;
    public static Connection connection;
    private static String host, user, pass, dbname;
    private static int port;

    public static ArrayList<ProxiedPlayer> Owners = new ArrayList<>();
    public static ArrayList<ProxiedPlayer> Managers = new ArrayList<>();
    public static ArrayList<ProxiedPlayer> Developers = new ArrayList<>();
    public static ArrayList<ProxiedPlayer> Administrators = new ArrayList<>();
    public static ArrayList<ProxiedPlayer> Moderators = new ArrayList<>();
    public static ArrayList<ProxiedPlayer> Trainees = new ArrayList<>();
    public static ArrayList<ProxiedPlayer> staffListVanish = new ArrayList<>();


    @Override
    public void onEnable(){
        getProxy().getPluginManager().registerCommand(this, new staffChatCommand());
        getProxy().getPluginManager().registerCommand(this, new RankCommand());
        getProxy().getPluginManager().registerListener(this, new UtilListeners());
        getProxy().getPluginManager().registerCommand(this, new staffListCommand());
        getProxy().getPluginManager().registerCommand(this, new gVanishCommand());
        getProxy().getPluginManager().registerCommand(this, new gMessageCommand());
        plugin = this;

        host = "uberstudioshd.com";
        user = "uberstu1_root";
        pass = "Meow9292CatsCatsMEOWMEoWM30WWW3eow";
        dbname = "uberstu1_system";
        port = 3306;

        try {
            openConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        /*try {
            PreparedStatement sql = connection.prepareStatement("CREATE TABLE IF NOT EXISTS player_data(Name TEXT NOT NULL, pUUID VARCHAR(36) NOT NULL, Rank TEXT NOT NULL, Points DOUBLE NOT NULL DEFAULT 0, Levels INT NOT NULL DEFAULT 1);");

            sql.execute();
            sql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    public static void openConnection() throws SQLException, ClassNotFoundException{
        if(connection != null && connection.isClosed()){
            return;
        }

        synchronized (plugin){
            if(connection != null && connection.isClosed()){
                return;
            }

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dbname + "?autoReconnect=true", user, pass);
        }
    }


}
