package com.clonelol.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Stats {

    private int hp;
    private int hpPerLevel;
    private int mp;
    private int mpPerLevel;
    private int moveSpeed;
    private int armor;
    private int armorPerLevel;
    private int spellBlock;
    private int spellBlockPerLevel;
    private int attackRange;
    private int hpRegen;
    private int hpRegenPerLevel;
    private int mpRegen;
    private int mpRegenPerLevel;
    private int crit;
    private int critPerLevel;
    private int attackDamage;
    private int attackDamagePerLevel;
    private int attackSpeed;
    private int attackSpeedPerLevel;
}
