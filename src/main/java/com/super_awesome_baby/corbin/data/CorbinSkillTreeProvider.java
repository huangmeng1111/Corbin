package com.super_awesome_baby.corbin.data;

import com.super_awesome_baby.corbin.Corbin;
import com.super_awesome_baby.corbin.gameasset.CorbinSkills;
import com.yesman.epicskills.common.data.SkillTreeProvider;
import net.minecraft.data.PackOutput;

import java.util.function.Consumer;

public class CorbinSkillTreeProvider extends SkillTreeProvider {
    public CorbinSkillTreeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildSkillTreePages(Consumer<SkillTreePageBuilder> writer) {
        writer.accept(
                newPage(Corbin.MOD_ID, "corbin_skills")
                        .menuBarColor(37, 27, 18)
                        .newNode(CorbinSkills.TACHI_WHIRLWIND_SLASH)
                        .position(20, 30)
                        .abilityPointsRequirement(5)
                        .done()

                        .newNode(CorbinSkills.ICHIMONJI)
                        .addParent(CorbinSkills.TACHI_WHIRLWIND_SLASH)
                        .position(80, 30)
                        .abilityPointsRequirement(5)
                        .done()

        );
    }
}