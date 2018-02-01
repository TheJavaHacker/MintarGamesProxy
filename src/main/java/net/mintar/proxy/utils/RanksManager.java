package net.mintar.proxy.utils;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.mintar.proxy.MainClass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class RanksManager {

    public static HashMap<String, Ranks> rank = new HashMap<String, Ranks>();

    public static Ranks getRank(ProxiedPlayer player){


        try{
            PreparedStatement sql = MainClass.connection.prepareStatement("SELECT rank FROM Core_Users WHERE username = ?;");
            sql.setString(1, player.getName());

            ResultSet set = sql.executeQuery();
            while(set.next()){
                rank.put(player.getName(), Ranks.valueOf(set.getString("rank")));
            }
            sql.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        Ranks r = rank.get(player.getName());
        player.setDisplayName(r.getPrefix() + player.getName());
        return r;

    }

    public static void setRank(ProxiedPlayer player, Ranks ranks){
        rank.put(player.getName(), ranks);

        try{
            PreparedStatement sql = MainClass.connection.prepareStatement("UPDATE Core_Users SET rank = ? WHERE username = ?;");
            sql.setString(1, ranks.toString());
            sql.setString(2, player.getName());

            sql.execute();
            sql.close();
        }catch(SQLException e){
            e.printStackTrace();
        }

        player.setDisplayName(ranks.getPrefix() + player.getName());
    }

}
