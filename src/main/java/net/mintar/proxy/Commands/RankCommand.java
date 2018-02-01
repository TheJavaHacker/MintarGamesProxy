package net.mintar.proxy.Commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.mintar.proxy.utils.Ranks;
import net.mintar.proxy.utils.RanksManager;

public class RankCommand extends Command {


    public RankCommand() {
        super("setrank", "commands.ranks");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if(!(commandSender instanceof ProxiedPlayer)){
            if(strings.length == 0 || strings.length > 2 || strings.length == 1){
                commandSender.sendMessage(new TextComponent("§3§lRANKS §8» §cYou are not using the correct parameters for this command."));
                commandSender.sendMessage(new TextComponent("§3§lRANKS §8» §cUsage: /setrank <player> <rank>"));
                return;
            }else{
                ProxiedPlayer target = ProxyServer.getInstance().getPlayer(strings[0]);
                if(target == null){
                    commandSender.sendMessage(new TextComponent("§3§lRANKS §8» §cThat commandSender cannot be found."));
                    return;
                }else{
                    Ranks ranks = Ranks.valueOf(strings[1]);
                    if(ranks == null){
                        commandSender.sendMessage(new TextComponent("§3§lRANKS §8» §cError. That rank cannot be found."));
                        StringBuilder builder = new StringBuilder();
                        for(Ranks rank : Ranks.values()){
                            builder.append(rank.getHumanReadable()).append(", ");
                        }
                        commandSender.sendMessage(new TextComponent("§3§lRANKS §8» §c" + builder));
                        return;
                    }else{
                        System.out.println("Entered else loop");
                        RanksManager.setRank(target, ranks);
                        System.out.println("Set Rank via Method");
                        for(ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()){
                            if(staff.hasPermission("core.staffmessages")){
                                staff.sendMessage(new TextComponent("§3§lRANKS §8» §bCONSOLE §7 has set §e" + target.getName() + "§7 to the " + ranks.getPrefix() + "§7rank."));
                                System.out.println("Sent Staff Messages");
                            }
                        }
                        target.sendMessage(new TextComponent("§3§lRANKS §8» §bCONSOLE §7 has set your rank to " + ranks.getPrefix() + "."));
                        System.out.println("Sent Target their message");
                        return;
                    }
                }
            }
        }else{
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            if(strings.length == 0 || strings.length > 2 || strings.length == 1){
                player.sendMessage(new TextComponent("§3§lRANKS §8» §cYou are not using the correct parameters for this command."));
                player.sendMessage(new TextComponent("§3§lRANKS §8» §cUsage: /setrank <player> <rank>"));
                return;
            }else{
                ProxiedPlayer target = ProxyServer.getInstance().getPlayer(strings[0]);
                if(target == null){
                    player.sendMessage(new TextComponent("§3§lRANKS §8» §cThat player cannot be found."));
                    return;
                }else{
                    Ranks ranks = Ranks.valueOf(strings[1]);
                    if(ranks == null){
                        player.sendMessage(new TextComponent("§3§lRANKS §8» §cThat rank cannot be found."));
                        StringBuilder builder = new StringBuilder();
                        for(Ranks rank : Ranks.values()){
                            builder.append(rank.getHumanReadable()).append(", ");
                        }
                        player.sendMessage(new TextComponent("§3§lRANKS §8» §c" + builder));
                        return;
                    }else{
                        System.out.println("Entered else loop");
                        RanksManager.setRank(target, ranks);
                        System.out.println("Set Rank via Method");
                        for(ProxiedPlayer staff : ProxyServer.getInstance().getPlayers()){
                            if(staff.hasPermission("core.staffmessages")){
                                staff.sendMessage(new TextComponent("§3§lRANKS §8» §c" + player.getDisplayName() + "§7 has set §e" + target.getName() + "§7 to the " + ranks.getPrefix() + "§7rank."));
                                System.out.println("Sent Staff Messages");
                            }
                        }
                        target.sendMessage(new TextComponent("§3§lRANKS §8» §c" + player.getDisplayName() + "§7 has set your rank to " + ranks.getPrefix() + "."));
                        System.out.println("Sent Target their message");
                        return;
                    }
                }
            }
        }
    }
}
