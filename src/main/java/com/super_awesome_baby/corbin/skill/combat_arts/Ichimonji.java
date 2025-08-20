package com.super_awesome_baby.corbin.skill.combat_arts;

import com.super_awesome_baby.corbin.gameasset.animation.CorbinAnimations;
import yesman.epicfight.skill.SkillBuilder;

public class Ichimonji extends CombatArtSkill {
    public Ichimonji(SkillBuilder<? extends CombatArtSkill> builder) {
        super(builder);
    }

    public void setComboAnimations() {
        this.comboAnimations.clear();
        this.comboAnimations.add(CorbinAnimations.ICHIMONJI_FIRST);
        this.comboAnimations.add(CorbinAnimations.ICHIMONJI_SECOND);
    }
}