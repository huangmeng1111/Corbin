package com.super_awesome_baby.corbin.gameasset;

import yesman.epicfight.skill.SkillCategory;

public enum CorbinSkillCategories implements SkillCategory {
    COMBAT_ARTS(true, true, true);

    boolean shouldSave;
    boolean shouldSyncronize;
    boolean modifiable;
    int id;

    CorbinSkillCategories(boolean shouldSave, boolean shouldSyncronizedAllPlayers, boolean modifiable) {
        this.shouldSave = shouldSave;
        this.shouldSyncronize = shouldSyncronizedAllPlayers;
        this.modifiable = modifiable;
        this.id = SkillCategory.ENUM_MANAGER.assign(this);
    }

    public boolean shouldSave() {
        return this.shouldSave;
    }

    public boolean shouldSynchronize() {
        return this.shouldSyncronize;
    }

    public boolean learnable() {
        return this.modifiable;
    }

    public int universalOrdinal() {
        return this.id;
    }
}
