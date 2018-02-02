package net.mintar.proxy.Commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.mintar.proxy.utils.Ranks;
import net.mintar.proxy.utils.RanksManager;

public class sendCommand extends Command {


    public sendCommand() {
        super("send");
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
                } else if(strings.length > 1 || strings.length < 2){
                    ProxiedPlayer target = ProxyServer.getInstance().getPlayer(strings[0]);
                    if(target == null){
                        player.sendMessage(new TextComponent("§3§lProxy §8» §cThe player specified could not be found. Cancelling."));
                        return;
                    }else{
                        player.sendMessage(new TextComponent("§3§lProxy §8» §cYou didn't specify a server. Cancelling."));
                        return;
                    }
                }else if(strings.length == 2){
                    ProxiedPlayer target = ProxyServer.getInstance().getPlayer(strings[0]);
                    ServerInfo targetServer = ProxyServer.getInstance().getServers().get(strings[1]);
                    if(target == null){
                        player.sendMessage(new TextComponent("§3§lProxy §8» §cThe player specified could not be found. Cancelling."));
                        return;
                    }else if(targetServer == null){
                        player.sendMessage(new TextComponent("§3§lProxy §8» §cYou didn't specify a server. Cancelling."));
                        return;
                    }else{
                        target.connect(targetServer);
                        player.sendMessage(new TextComponent("§3§lProxy §8» " + target.getDisplayName() +" §awas successfully connected to " + strings[1]));
                        target.sendMessage(new TextComponent("§3§lProxy §8» §3You were sent to the " + strings[1] + " server by " + player.getDisplayName()));
                        return;
                    }
                }else{
                    player.sendMessage(new TextComponent("§3§lProxy §8» §cYou've provided too many arguments. Cancelling."));
                    return;
                }
            }else{
                player.sendMessage(new TextComponent("§3§lRANKS §8»§cYou do not have the minimum required rank to run this command."));
                return;
            }
        }
    }
}
