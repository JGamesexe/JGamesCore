package com.jgamesexe.jgamescore.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Item {

    private Material material;
    private short durability = 0;
    private int amount = 1;
    private String display = null;
    private ArrayList<String> lore = new ArrayList<>();
    private HashMap<Enchantment, Integer> enchants = new HashMap<>();
    private HashSet<ItemFlag> flags = new HashSet<>();

    public Item(Material material, String display, String... lore) {
        this(material, display);
        this.lore = new ArrayList<>(Arrays.asList(lore));
    }

    public Item(Material material, String display) {
        this(material);
        setDisplay(display);
    }

    public Item(Material material, short durability, String display, String... lore) {
        this(material, durability, display);
        this.lore = new ArrayList<>(Arrays.asList(lore));
    }

    public Item(Material material, short durability, String display) {
        this(material, durability);
        setDisplay(display);
    }

    public Item(Material material, short durability) {
        this(material);
        setDurability(durability);
    }

    public Item(Material material) {
        setMaterial(material);
    }

    public Item(ItemStack itemStack) {
        material = itemStack.getType();
        durability = itemStack.getDurability();
        amount = itemStack.getAmount();

        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) return;
        if (meta.getDisplayName() != null) display = meta.getDisplayName();
        if (meta.getLore() != null && meta.getLore().size() > 0) lore = new ArrayList<>(meta.getLore());
        if (meta.getEnchants() != null && meta.getEnchants().size() > 0) enchants = new HashMap<>(meta.getEnchants());
        if (meta.getItemFlags() != null && meta.getItemFlags().size() > 0) flags = new HashSet<>(meta.getItemFlags());
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return this.material;
    }

    public void setDurability(short durability) {
        this.durability = durability;
    }

    public short getDurability() {
        return this.durability;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }

    public void addLore(String... lore) {
        this.lore.addAll(Arrays.asList(lore));
    }

    public void insertLore(String lore, int where) {
        this.lore.add(where, lore);
    }

    public void remLore(int where) {
        if (where >= lore.size()) return;
        this.lore.remove(where);
    }

    public String getLore(int where) {
        if (where >= lore.size()) return null;
        return this.lore.get(where);
    }

    public void addEnchant(Enchantment ench, int value) {
        this.enchants.put(ench, value);
    }

    public void remEnchant(Enchantment ench) {
        this.enchants.remove(ench);
    }

    public Map.Entry<Enchantment, Integer> getEnchant(Enchantment ench) {
        if (!enchants.containsKey(ench)) return null;
        return new AbstractMap.SimpleEntry<>(ench, enchants.get(ench));
    }

    public int getEnchantLevel(Enchantment ench) {
        if (!enchants.containsKey(ench)) return 0;
        return enchants.get(ench);
    }

    public void addFlag(ItemFlag flag) {
        this.flags.add(flag);
    }

    public void remFlag(ItemFlag flag) {
        this.flags.remove(flag);
    }

    public boolean hasFlag(ItemFlag flag) {
        return flags.contains(flag);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof ItemStack) obj = new Item((ItemStack) obj);
        if (!(obj instanceof Item)) return false;
        Item item = (Item) obj;
        if (item.material != material) return false;
        if (item.durability != durability) return false;
        if (item.display.equals(display)) return false;
        if (item.lore.equals(lore)) return false;
        if (item.enchants.equals(enchants)) return false;
        if (item.flags.equals(flags)) return false;
        return true;
    }

    public ItemStack build() {
        ItemStack item = new ItemStack(material);

        if (durability != 0) item.setDurability(durability);
        if (enchants.size() != 0) item.addUnsafeEnchantments(enchants);
        if (display != null || lore.size() != 0 || flags.size() != 0) {
            ItemMeta meta = item.getItemMeta();
            if (display != null) meta.setDisplayName(display);
            if (lore.size() != 0) meta.setLore(lore);
            if (flags.size() != 0) for (ItemFlag itemFlag : flags) meta.addItemFlags(itemFlag);
            item.setItemMeta(meta);
        }
        return item;
    }

}
