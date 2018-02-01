package net.mintar.proxy.Commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.mintar.proxy.MainClass;
import net.mintar.proxy.utils.Ranks;
import net.mintar.proxy.utils.RanksManager;

public class gVanishCommand extends Command {
    public gVanishCommand() {
        super("gvanish", "commands.gvanish");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if(commandSender instanceof ProxiedPlayer){
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            if(MainClass.staffListVanish.contains(player)){
                if(RanksManager.getRank(player) == Ranks.OWNER){
                    MainClass.Owners.add(player);
                }else if(RanksManager.getRank(player) == Ranks.MANAGER){
                    MainClass.Managers.add(player);
                }else if(RanksManager.getRank(player) == Ranks.DEVELOPER){
                    MainClass.Developers.add(player);
                }else if(RanksManager.getRank(player) == Ranks.ADMIN){
                    MainClass.Administrators.add(player);
                }else if(RanksManager.getRank(player) == Ranks.MODERATOR){
                    MainClass.Moderators.add(player);
                }else if(RanksManager.getRank(player) == Ranks.TRAINEE){
                    MainClass.Trainees.add(player);
                }
                MainClass.staffListVanish.remove(player);
            }else{
                if(MainClass.Owners.contains(player)){
                    MainClass.Owners.remove(player);
                }else if(MainClass.Managers.contains(player)){
                    MainClass.Managers.remove(player);
                }else if(MainClass.Administrators.contains(player)){
                    MainClass.Administrators.remove(player);
                }else if(MainClass.Developers.contains(player)){
                    MainClass.Developers.remove(player);
                }else if(MainClass.Moderators.contains(player)){
                    MainClass.Moderators.remove(player);
                }else if(MainClass.Trainees.contains(player)){
                    MainClass.Trainees.remove(player);
                }
                MainClass.staffListVanish.add(player);
            }
        }else{
            commandSender.sendMessage(new TextComponent("You are not a player, thus are not able to run this command."));
        }
    }
}
