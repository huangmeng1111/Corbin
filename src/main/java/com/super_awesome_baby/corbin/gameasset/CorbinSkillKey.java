package com.super_awesome_baby.corbin.gameasset;

import com.super_awesome_baby.corbin.Corbin;
import com.super_awesome_baby.corbin.skill.combat_arts.CombatArtSkill;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import yesman.epicfight.api.utils.PacketBufferCodec;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.skill.SkillDataKey;

public class CorbinSkillKey {
    public static final DeferredRegister<SkillDataKey<?>> DATA_KEYS =
            DeferredRegister.create(ResourceLocation.fromNamespaceAndPath(EpicFightMod.MODID, "skill_data_keys"), Corbin.MOD_ID);

    public static final RegistryObject<SkillDataKey<Integer>> COMBAT_ART_INDEX =
            DATA_KEYS.register("combat_art_index", () ->SkillDataKey.createSkillDataKey
                    (PacketBufferCodec.INTEGER,0,false,CombatArtSkill.class));

    public static final RegistryObject<SkillDataKey<Boolean>> COMBAT_ART_START =
            DATA_KEYS.register("combat_art_start", () ->SkillDataKey.createSkillDataKey
                    (PacketBufferCodec.BOOLEAN,false,false,CombatArtSkill.class));

    public static final RegistryObject<SkillDataKey<Boolean>> LAST_ATTACK_CLICKED =
            DATA_KEYS.register("last_attack_clicked", () ->SkillDataKey.createSkillDataKey
                    (PacketBufferCodec.BOOLEAN,false,false,CombatArtSkill.class));
}
