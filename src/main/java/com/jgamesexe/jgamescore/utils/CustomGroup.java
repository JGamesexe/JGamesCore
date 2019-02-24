package com.jgamesexe.jgamescore.utils;

import com.jgamesexe.jgamescore.JGamesCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.ArrayList;
import java.util.List;

public class CustomGroup {

    private String pexGroup;
    private String tabGroup;
    private String corNick;
    private String prefix = "§4§lTilT";
    private String tabPrefix;

    static {
        if (Bukkit.getPluginManager().getPlugin("PermissionsEx") == null) JGamesCore.log("PermissionsEx NOT FOUND, CustomGroup will not work");
    }

    public CustomGroup(String pexGroup, String tabGroup, String corNick, String tabPrefix) {
        setValues(pexGroup, tabGroup, corNick, tabPrefix);
    }

    public CustomGroup(String ymlLine) {
        String[] split = ymlLine.split("(?=᎒)");
        for (byte i = 0; i < 4; i++)
            split[i] = split[i].replace("᎒", "");
        setValues(split[0], split[1], split[2], split[3]);
    }

    private void setValues(String pexGroup, String tabGroup, String corNick, String tabPrefix) {
        this.pexGroup = pexGroup;
        if (tabGroup.length() > 5) tabGroup = tabGroup.substring(0, 5);
        this.tabGroup = tabGroup;
        this.corNick = corNick;
        PermissionGroup iPexGroup = PermissionsEx.getPermissionManager().getGroup(pexGroup);
        if (iPexGroup != null) prefix = ChatColor.translateAlternateColorCodes('&', iPexGroup.getPrefix());
        this.tabPrefix = tabPrefix.replace("%same%", prefix);
    }

    @Override
    public String toString() {
        return pexGroup + "᎒" + tabGroup + "᎒" + corNick + "᎒" + tabPrefix;
    }

    public String getPexGroup() {
        return pexGroup;
    }

    public String getTabGroup() {
        return tabGroup;
    }

    public String getCorNick() {
        return corNick;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getTabPrefix() {
        return tabPrefix;
    }

    public static List<CustomGroup> toGroups(List<String> sGroups) {
        List<CustomGroup> groups = new ArrayList<>();
        for (String sGroup : sGroups)
            groups.add(new CustomGroup(sGroup));
        return groups;
    }

    public static List<String> toSGroups(List<CustomGroup> groups) {
        List<String> sGroups = new ArrayList<>();
        for (CustomGroup group : groups)
            sGroups.add(group.toString());
        return sGroups;
    }

}
