package net.mintar.proxy.Commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.mintar.proxy.MainClass;
import net.mintar.proxy.utils.Ranks;

public class staffListCommand extends Command {

    public staffListCommand() {
        super("list");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        StringBuilder ownerBuilder = new StringBuilder();
        StringBuilder managerBuilder = new StringBuilder();
        StringBuilder devBuilder = new StringBuilder();
        StringBuilder adminBuilder = new StringBuilder();
        StringBuilder modBuilder = new StringBuilder();
        StringBuilder traineeBuilder = new StringBuilder();
        StringBuilder VIPBuilder = new StringBuilder();
        StringBuilder PartnerBuilder = new StringBuilder();

        for(ProxiedPlayer owners : MainClass.Owners){
            ownerBuilder.append(owners).append(", ");
        }
        for(ProxiedPlayer managers : MainClass.Managers){
            managerBuilder.append(managers).append(", ");
        }
        for(ProxiedPlayer admins : MainClass.Administrators){
            adminBuilder.append(admins).append(", ");
        }
        for(ProxiedPlayer mods : MainClass.Moderators){
            modBuilder.append(mods).append(", ");
        }
        for(ProxiedPlayer devs : MainClass.Developers){
            devBuilder.append(devs).append(", ");
        }
        for(ProxiedPlayer trainees : MainClass.Trainees){
            traineeBuilder.append(trainees).append(", ");
        }
        for(ProxiedPlayer VIPS : MainClass.VIP){
            VIPBuilder.append(VIPS).append(", ");
        }
        for(ProxiedPlayer Partners : MainClass.Partner){
            PartnerBuilder.append(Partners).append(", ");
        }

        commandSender.sendMessage(new TextComponent("§8§m-----------§r §3This is the Global Player list. §8§m------------§r"));
        commandSender.sendMessage(new TextComponent(Ranks.FOUNDER.getPrefix() + " §8(§a" + MainClass.Owners.size() +"§8) §8» §a" + ownerBuilder));
        commandSender.sendMessage(new TextComponent(Ranks.MANAGER.getPrefix() + " §8(§a" + MainClass.Managers.size() +"§8) §8» §a" + managerBuilder));
        commandSender.sendMessage(new TextComponent(Ranks.DEVELOPER.getPrefix() + " §8(§a" + MainClass.Developers.size() + "§8) §8» §a" + devBuilder));
        commandSender.sendMessage(new TextComponent(Ranks.ADMIN.getPrefix() + " §8(§a"+ MainClass.Administrators.size() + "§8) §8» §a" + adminBuilder));
        commandSender.sendMessage(new TextComponent(Ranks.MODERATOR.getPrefix() + " §8(§a"+ MainClass.Moderators.size() +"§8) §8» §a" + modBuilder));
        commandSender.sendMessage(new TextComponent(Ranks.TRIAL.getPrefix() + " §8(§a"+MainClass.Trainees.size()+"§8) §8» §a" + traineeBuilder));
        commandSender.sendMessage(new TextComponent(Ranks.WELL_KNOWN.getPrefix() + "§8(§a" + MainClass.VIP.size() + "§8) » §a" + VIPBuilder));
        commandSender.sendMessage(new TextComponent(Ranks.PARTNER.getPrefix() + "§8(§a" + MainClass.Partner.size() + "§8) » §a" + PartnerBuilder));
        commandSender.sendMessage(new TextComponent("§8§m-----------§r §3There are §b" + ProxyServer.getInstance().getOnlineCount() + "§3 players online. §8§m-----------§r"));

    }
}
