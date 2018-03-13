package net.mintar.proxy.Commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.mintar.proxy.utils.Ranks;
import net.mintar.proxy.utils.RanksManager;

public class staffChatCommand extends Command {

    public staffChatCommand(){
        super("sc");
    }

    public void execute(CommandSender sender, String[] args){
        if(sender instanceof ProxiedPlayer){
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if(RanksManager.getRank(player).getRankNumber() >= Ranks.TRIAL.getRankNumber()){
                if(args.length < 1){
                    player.sendMessage(new TextComponent("§3§lProxyCore §8» §cYou're missing any kind of message."));
                    return;
                }else{
                    for(ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()){
                        if(RanksManager.getRank(staff).getRankNumber() >= Ranks.TRIAL.getRankNumber()){
                            StringBuilder builder = new StringBuilder();
                            for(int i = 0; i < args.length; i++){
                                builder.append(args[i]).append(" ");
                            }
                            staff.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&3&lSTAFF &8&l» " + player.getDisplayName() + " &8» &e" + builder)));
                            return;
                        }
                    }
                    return;
                }
            }else{
                player.sendMessage(new TextComponent("§3§lRANKS §8»§cYou do not have the minimum required rank to run this command."));
                return;
            }
        }else{
            for(ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()){
                if(RanksManager.getRank(staff).getRankNumber() >= Ranks.TRIAL.getRankNumber()){
                    StringBuilder builder = new StringBuilder();
                    for(int i = 0; i < args.length; i++){
                        builder.append(args[i]).append(" ");
                    }
                    staff.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&3&lSTAFF &8&l» CONSOLE &8» &e" + builder)));
                    return;
                }
            }
            return;
        }
    }

}
