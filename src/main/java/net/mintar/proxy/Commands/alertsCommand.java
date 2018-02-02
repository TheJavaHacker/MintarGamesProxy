package net.mintar.proxy.Commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.mintar.proxy.utils.Ranks;
import net.mintar.proxy.utils.RanksManager;

public class alertsCommand extends Command {


    public alertsCommand() {
        super("alert");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
     if(!(commandSender instanceof ProxiedPlayer)){
         commandSender.sendMessage(new TextComponent("§3§lProxy §8» §cYou cannot run this command from the console."));
         return;
     }else{
         ProxiedPlayer player = (ProxiedPlayer) commandSender;
         if(RanksManager.getRank(player).getRankNumber() >= Ranks.MANAGER.getRankNumber()){
             if(strings.length < 1){
                 player.sendMessage(new TextComponent("§3§lProxy §8» §cYou did not specify a message. That was silly."));
                 return;
             }else{
                 for(ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()){
                         StringBuilder builder = new StringBuilder();
                         for(int i = 0; i < strings.length; i++){
                             builder.append(strings[i]).append(" ");
                         }
                         staff.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&3&lALERT &8&l» " + player.getDisplayName() + " &8» &e" + builder)));
                         return;
                 }
             }
         }else{
             player.sendMessage(new TextComponent("§3§lProxy §8» §cYou do not have the minimum required rank to run this command."));
             return;
         }
     }
    }
}
