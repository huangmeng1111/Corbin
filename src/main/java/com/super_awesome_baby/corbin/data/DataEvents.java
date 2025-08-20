package com.super_awesome_baby.corbin.data;


import com.super_awesome_baby.corbin.Corbin;
import com.yesman.epicskills.common.data.SkillTreeProvider;
import net.minecraft.data.DataProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Corbin.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataEvents {
    @SubscribeEvent
    public static void cob$gatherData(GatherDataEvent event) {
        event.getGenerator().addProvider(true, (DataProvider.Factory<SkillTreeProvider>) CorbinSkillTreeProvider::new);
    }
}