package net.mintar.proxy.Commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.mintar.proxy.utils.Ranks;
import net.mintar.proxy.utils.RanksManager;

public class sendAllCommand extends Command {


    public sendAllCommand() {
        super("sendall");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if(!(commandSender instanceof ProxiedPlayer)){
            commandSender.sendMessage(new TextComponent("§3§lProxy §8» §cYou cannot do this from the console. Sorry."));
            return;
        }else{
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            if(RanksManager.getRank(player).getRankNumber() >= Ranks.ADMIN.getRankNumber()){
                if(strings.length < 1){
                    player.sendMessage(new TextComponent("§3§lProxy §8» §cYou didn't specify a player or a server. Cancelling."));
                    return;
                }else if(strings.length == 1){
                    ServerInfo targetServer = ProxyServer.getInstance().getServers().get(strings[0]);
                    if(targetServer == null){
                        player.sendMessage(new TextComponent("§3§lProxy §8» §cYou didn't specify a server. Cancelling."));
                        return;
                    }else{
                        for(ProxiedPlayer target : ProxyServer.getInstance().getPlayers()){
                            target.connect(targetServer);
                            target.sendMessage(new TextComponent("§3§lProxy §8» §3You were sent to the " + strings[1] + " server by " + player.getDisplayName()));
                        }
                        player.sendMessage(new TextComponent("§3§lProxy §8» §bALL PLAYERS §awere successfully connected to " + strings[1]));
                        return;
                    }
                }else{
                    player.sendMessage(new TextComponent("§3§lProxy §8» §cYou've provided too many arguments. Cancelling."));
                    return;
                }
            }else{
                player.sendMessage(new TextComponent("§3§lProxy §8» §cYou do not have the minimum required rank to run this command. Sorry."));
            }
        }
    }
}
