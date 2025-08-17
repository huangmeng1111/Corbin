package com.super_awesome_baby.corbin.gameasset;

import com.super_awesome_baby.corbin.Corbin;
import com.super_awesome_baby.corbin.skill.passive.SekiroSkill;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.skill.Skill;

@Mod.EventBusSubscriber(modid = Corbin.MODID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class CorbinSkills {
    public static Skill SEKIROSKILL;

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void buildSkillEvent(SkillBuildEvent build){
        SkillBuildEvent.ModRegistryWorker modRegistry = build.createRegistryWorker(Corbin.MODID);

        SEKIROSKILL = modRegistry.build("sekiro", SekiroSkill::new, SekiroSkill.createSekiroBuilder()
                .setActivateType(Skill.ActivateType.ONE_SHOT)
        );
    }
    private CorbinSkills() {}
}
