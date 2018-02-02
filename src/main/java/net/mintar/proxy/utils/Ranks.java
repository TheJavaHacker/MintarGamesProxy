package net.mintar.proxy.utils;

import java.util.Arrays;
import java.util.Optional;

public enum Ranks {

    OWNER("Owner", "§4§lOWNER §4", 13),
    MANAGER("Manager", "§4§lMANAGER §4", 12),
    DEVELOPER("Developer", "§c§lDEV §c", 11),
    ADMIN("Admin", "§c§lADMIN §c", 10),
    MODERATOR("Mod", "§3§lMOD §3", 9),
    TRAINEE("Trainee", "§3§lTRAINEE §3", 8),

    PARTNER("Partner", "§6§lPARTNER §f", 7),
    WELL_KNOWN("VIP", "§6§lWELL KNOWN §f", 6),
    OVERLORD("Overlord", "§2OVERLORD §f", 5),
    WARRIOR("Warrior", "§dWARRIOR §f", 4),
    PALADIN("Paladin", "§aPALADIN §f", 3),
    DONATOR("Donator", "§eDONATOR §f", 2),

    DEFAULT("Default", "§7", 1);

    private String humanReadable, prefix;
    private int rankNumber;

    Ranks(String humanReadable, String prefix, int rankNumber){
        this.humanReadable = humanReadable;
        this.prefix = prefix;
        this.rankNumber = rankNumber;
    }

    public String getHumanReadable(){
        return this.humanReadable;
    }

    public int getRankNumber(){
        return this.rankNumber;
    }

    public String getPrefix(){
        return this.prefix;
    }


}
