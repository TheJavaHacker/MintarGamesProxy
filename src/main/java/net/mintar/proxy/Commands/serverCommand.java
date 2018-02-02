package net.mintar.proxy.Commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.mintar.proxy.utils.Ranks;
import net.mintar.proxy.utils.RanksManager;

public class serverCommand extends Command {


    public serverCommand() {
        super("server");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if(commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            if (RanksManager.getRank(player).getRankNumber() <= Ranks.PARTNER.getRankNumber()) {
                player.sendMessage(new TextComponent("§3§lPROXY §8» §bYou are currently connected to the " + player.getServer().toString() + " server."));
                return;
            }else{
                StringBuilder serverList = new StringBuilder();
                for(ServerInfo serverName : ProxyServer.getInstance().getServers().values()){
                    serverList.append(serverName.getName()).append(", ");
                }
                player.sendMessage(new TextComponent("§3§lPROXY §8» §bYou can connect to the following servers: " + serverList));
                return;
            }
        }else{
            commandSender.sendMessage(new TextComponent("§3§lPROXY §8» §cYou cannot do this command as the console."));
            return;
        }
    }
}
