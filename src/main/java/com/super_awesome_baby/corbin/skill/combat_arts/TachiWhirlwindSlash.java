package com.super_awesome_baby.corbin.skill.combat_arts;

import com.super_awesome_baby.corbin.gameasset.animation.CorbinAnimations;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillBuilder;

public class TachiWhirlwindSlash extends CombatArtSkill {
    public TachiWhirlwindSlash(SkillBuilder<? extends Skill> builder) {
        super(builder);
    }

    @Override
    public void setComboAnimations() {
        comboAnimations.clear();
        comboAnimations.add(CorbinAnimations.TACHI_WHIRLWIND_SLASH);
    }
}
