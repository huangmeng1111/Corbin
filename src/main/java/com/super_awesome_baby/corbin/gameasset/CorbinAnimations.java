package com.super_awesome_baby.corbin.gameasset;

import com.super_awesome_baby.corbin.Corbin;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.BasicAttackAnimation;
import yesman.epicfight.api.collider.Collider;
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
                        (new BasicAttackAnimation(0.1F, accessor, Armatures.BIPED,
                                new BasicAttackAnimation.Phase(0.0F, 0.86F, 1.0F, 1.0F, 1.0F, Armatures.BIPED.get().toolR, (Collider)null),
                                new BasicAttackAnimation.Phase(1.0F, 1.6F, 1.9F, 2.4F, Float.MAX_VALUE, Armatures.BIPED.get().toolR,null)))
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1F))


                );
    }
}
