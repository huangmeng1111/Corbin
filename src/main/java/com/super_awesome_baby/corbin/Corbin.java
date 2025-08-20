package com.super_awesome_baby.corbin;

import com.mojang.logging.LogUtils;
import com.super_awesome_baby.corbin.gameasset.CorbinSkillCategories;
import com.super_awesome_baby.corbin.gameasset.CorbinSkillKey;
import com.super_awesome_baby.corbin.gameasset.CorbinSkillSlots;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.slf4j.Logger;

@Mod(Corbin.MOD_ID)
public class Corbin {
    public static final String MOD_ID = "corbin";
    public static final Logger LOGGER = LogUtils.getLogger();
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
            .named(ResourceLocation.fromNamespaceAndPath(MOD_ID, "main"))
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .simpleChannel();

    public Corbin(FMLJavaModLoadingContext context){
        IEventBus modEventBus = context.getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::onCommonSetup);
        registerPackets();
        //数据键
        CorbinSkillKey.DATA_KEYS.register(modEventBus);
        //技能槽位
        CorbinSkillSlots.ENUM_MANAGER.registerEnumCls(MOD_ID,CorbinSkillSlots.class);
        //技能类别
        CorbinSkillCategories.ENUM_MANAGER.registerEnumCls(MOD_ID,CorbinSkillCategories.class);
    }

    @SubscribeEvent
    public void onCommonSetup(FMLCommonSetupEvent event) {

    }


    private void registerPackets() {
        int packetId = 0;
    }
}
