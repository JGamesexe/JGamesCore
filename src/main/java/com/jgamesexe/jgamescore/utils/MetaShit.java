/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jgamesexe.jgamescore.utils;

import com.jgamesexe.jgamescore.JGamesCore;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.Plugin;

public class MetaShit implements MetadataValue {

    public static Object getMetaObject(String meta, Metadatable e) {
        if (!e.hasMetadata(meta)) return null;
        return e.getMetadata(meta).get(0).value();
    }

    public static void setMetaObject(String meta, Metadatable e, Object value, Plugin own) {
        if (e.hasMetadata(meta))
            e.removeMetadata(meta, own);
        e.setMetadata(meta, new FixedMetadataValue(own, value));
    }

    public static void removeMetaObject(String meta, Metadatable e, Plugin dono) {
        e.removeMetadata(meta, dono);
    }

    private Object shit = null;

    public MetaShit(Object o) {
        shit = o;
    }

    @Override
    public Object value() {
        return shit;
    }

    @Override
    public int asInt() {
        return 0;
    }

    @Override
    public float asFloat() {
        return 0;
    }

    @Override
    public double asDouble() {
        return shit instanceof Double ? (Double) shit : 0;
    }

    @Override
    public long asLong() {
        return 0;
    }

    @Override
    public short asShort() {
        return 0;
    }

    @Override
    public byte asByte() {
        return 0;
    }

    @Override
    public boolean asBoolean() {
        return false;
    }

    @Override
    public String asString() {
        return null;
    }

    @Override
    public Plugin getOwningPlugin() {
        return JGamesCore.core;
    }

    @Override
    public void invalidate() {

    }

}
