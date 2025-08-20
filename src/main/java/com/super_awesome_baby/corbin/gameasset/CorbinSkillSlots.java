package com.super_awesome_baby.corbin.gameasset;

import yesman.epicfight.skill.SkillCategory;
import yesman.epicfight.skill.SkillSlot;

public enum CorbinSkillSlots implements SkillSlot {
    COMBAT_ARTS(CorbinSkillCategories.COMBAT_ARTS);

    final CorbinSkillCategories category;
    final int id;

    CorbinSkillSlots(CorbinSkillCategories category) {
        this.category = category;
        this.id = SkillSlot.ENUM_MANAGER.assign(this);
    }

    public SkillCategory category() {
        return this.category;
    }

    public int universalOrdinal() {
        return this.id;
    }
}
