package com.super_awesome_baby.corbin.gameasset;

import com.super_awesome_baby.corbin.Corbin;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.BasicAttackAnimation;
import yesman.epicfight.gameasset.Armatures;


@Mod.EventBusSubscriber(modid = Corbin.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class CorbinAnimations {

    public static AnimationManager.AnimationAccessor<BasicAttackAnimation> TACHI_WHIRLWIND_SLASH;

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerAnimations(AnimationManager.AnimationRegistryEvent event) {
        event.newBuilder(Corbin.MODID, CorbinAnimations::build);
    }
    public static void build (AnimationManager.AnimationBuilder builder){
        TACHI_WHIRLWIND_SLASH =
                builder.nextAccessor("biped/combat/tachi_whirlwind_slash", (accessor) ->
                        (new BasicAttackAnimation(0.1F, 0.45F, 0.75F, 1.2F, null,
                        Armatures.BIPED.get().toolR, accessor, Armatures.BIPED))
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1F))

                );
    }
}
