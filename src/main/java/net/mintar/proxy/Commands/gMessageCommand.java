package net.mintar.proxy.Commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class gMessageCommand extends Command {

    public gMessageCommand() {
        super("message", null, "msg", "gmsg");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if(commandSender instanceof ProxiedPlayer){
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            if(strings.length < 2){
                player.sendMessage(new TextComponent("§3§lProxy §8» §cYou are missing some parameters."));
            }else if(strings.length == 1){
                player.sendMessage(new TextComponent("§3§lProxy §8» §cYou didn't provide a message."));
            }else{
                ProxiedPlayer target = ProxyServer.getInstance().getPlayer(strings[0]);
                StringBuilder builder = new StringBuilder();
                for(int i = 1; i < strings.length; i++){
                    builder.append(strings[i]).append(" ");
                }
                player.sendMessage(new TextComponent("§8[§3PM TO§8] " + target.getDisplayName() + " §8» §7" + builder));
                target.sendMessage(new TextComponent("§8[§3PM FROM§8] " + player.getDisplayName() + " §8» §7" + builder));
            }
        }else{
            ProxiedPlayer target = ProxyServer.getInstance().getPlayer(strings[0]);
            StringBuilder builder = new StringBuilder();
            for(int i = 1; i < strings.length; i++){
                builder.append(strings[i]).append(" ");
            }
            target.sendMessage(new TextComponent("§8[§3PM FROM§8] §bCONSOLE §8» §7" + builder));
        }
    }
}
