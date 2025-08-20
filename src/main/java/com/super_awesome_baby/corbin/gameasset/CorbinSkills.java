package com.super_awesome_baby.corbin.gameasset;

import com.super_awesome_baby.corbin.Corbin;
import com.super_awesome_baby.corbin.skill.combat_arts.CombatArtSkill;
import com.super_awesome_baby.corbin.skill.combat_arts.Ichimonji;
import com.super_awesome_baby.corbin.skill.combat_arts.TachiWhirlwindSlash;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.world.item.EpicFightCreativeTabs;

@Mod.EventBusSubscriber(modid = Corbin.MOD_ID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class CorbinSkills {
    public static Skill ICHIMONJI;
    public static Skill TACHI_WHIRLWIND_SLASH;

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void buildSkillEvent(SkillBuildEvent build){
        SkillBuildEvent.ModRegistryWorker modRegistry = build.createRegistryWorker(Corbin.MOD_ID);

        ICHIMONJI = modRegistry.build("ichimonji",
                Ichimonji::new, CombatArtSkill.createCombatArtBuilder().setCreativeTab(EpicFightCreativeTabs.ITEMS.get()));

        TACHI_WHIRLWIND_SLASH = modRegistry.build("tachi_whirlwind_slash",
                TachiWhirlwindSlash::new, CombatArtSkill.createCombatArtBuilder().setCreativeTab(EpicFightCreativeTabs.ITEMS.get()));

    }
    private CorbinSkills() {}
}
