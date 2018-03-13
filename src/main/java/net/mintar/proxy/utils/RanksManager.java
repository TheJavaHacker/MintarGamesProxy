package net.mintar.proxy.utils;

import net.md_5.bungee.api.chat.TextComponent;
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
            PreparedStatement sql = MainClass.connection.prepareStatement("SELECT rank FROM Core_Users WHERE uuid = ?;");
            sql.setString(1, player.getUniqueId().toString());

            ResultSet set = sql.executeQuery();
            while(set.next()){
                rank.put(player.getName(), Ranks.valueOf(set.getString("rank")));
            }
            sql.close();
        }catch(SQLException e){
            player.disconnect(new TextComponent("§3§lPROXY §8» §cYour account could not be reached. Please rejoin."));
            e.printStackTrace();
        }
        Ranks r = rank.get(player.getName());
        player.setDisplayName(r.getPrefix() + player.getName());
        return r;

    }

    public static void setRank(ProxiedPlayer player, Ranks ranks){
        rank.put(player.getName(), ranks);

        try{
            PreparedStatement sql = MainClass.connection.prepareStatement("UPDATE Core_Users SET rank = ? WHERE uuid = ?;");
            sql.setString(1, ranks.toString());
            sql.setString(2, player.getUniqueId().toString());

            sql.execute();
            sql.close();
        }catch(SQLException e){
            e.printStackTrace();
        }

        player.disconnect(new TextComponent("§3§lPROXY §8» §cYour rank has been updated. Please rejoin."));
        player.setDisplayName(ranks.getPrefix() + player.getName());
    }

}
