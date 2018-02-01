package net.mintar.proxy.Commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class staffChatCommand extends Command {

    public staffChatCommand(){
        super("sc", "commands.sc", "staffchat");
    }

    public void execute(CommandSender sender, String[] args){
        if(sender instanceof ProxiedPlayer){
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if(args.length < 1){
                player.sendMessage(new TextComponent("§3§lProxyCore §8» §cYou're missing any kind of message."));
                return;
            }else{
                for(ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()){
                    if(staff.hasPermission("commands.sc")){
                        StringBuilder builder = new StringBuilder();
                        for(int i = 0; i < args.length; i++){
                            builder.append(args[i]).append(" ");
                        }
                        staff.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&8&l[&c&lSTAFF&8&l] " + player.getDisplayName() + " &8» &e" + builder)));
                        return;
                    }
                }
                return;
            }
        }else{
            for(ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()){
                if(staff.hasPermission("commands.sc")){
                    StringBuilder builder = new StringBuilder();
                    for(int i = 0; i < args.length; i++){
                        builder.append(args[i]).append(" ");
                    }
                    staff.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&8&l[&c&lSTAFF&8&l] CONSOLE &8» &e" + builder)));
                    return;
                }
            }
            return;
        }
    }

}
