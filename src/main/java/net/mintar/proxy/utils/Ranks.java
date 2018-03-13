package net.mintar.proxy.utils;

public enum Ranks {

    FOUNDER("Owner", "§4§lOWNER §4", 14),
    MANAGER("Manager", "§4§lMANAGER §4", 13),
    DEVELOPER("Developer", "§c§lDEV §c", 12),
    ADMIN("Admin", "§c§lADMIN §c", 11),
    MODERATOR("Mod", "§3§lMOD §3", 10),
    TRIAL("Trainee", "§3§lTRAINEE §3", 9),

    YOUTUBER("YouTuber", "§6§lYT §6", 8),
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
