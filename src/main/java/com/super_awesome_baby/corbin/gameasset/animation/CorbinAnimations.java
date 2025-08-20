package com.super_awesome_baby.corbin.gameasset.animation;

import com.super_awesome_baby.corbin.Corbin;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.BasicAttackAnimation;
import yesman.epicfight.api.animation.types.EntityState;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;


@Mod.EventBusSubscriber(modid = Corbin.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class CorbinAnimations {

    public static AnimationManager.AnimationAccessor<AttackAnimation> TACHI_WHIRLWIND_SLASH;
    public static AnimationManager.AnimationAccessor<AttackAnimation> ICHIMONJI_FIRST;
    public static AnimationManager.AnimationAccessor<AttackAnimation> ICHIMONJI_SECOND;

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerAnimations(AnimationManager.AnimationRegistryEvent event) {
        event.newBuilder(Corbin.MOD_ID, CorbinAnimations::build);
    }

    public static void build (AnimationManager.AnimationBuilder builder){

        AnimationProperty.PlaybackSpeedModifier CONSTANT_ONE =
                (self, entitypatch, speed, prevElapsedTime, elapsedTime) -> 1.0F;

        TACHI_WHIRLWIND_SLASH =
                builder.nextAccessor("biped/skill/tachi_whirlwind_slash", (accessor) ->
                        (new AttackAnimation(0.1F, accessor, Armatures.BIPED,
                                new BasicAttackAnimation.Phase(0.0F, 0.6F, 1F, 1F, 1F, Armatures.BIPED.get().toolR, null),
                                new BasicAttackAnimation.Phase(1F, 1.3F, 1.45F, 1.6F, Float.MAX_VALUE, Armatures.BIPED.get().toolR,null)))
                        .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_ONE)
                );


        AnimationProperty.PlaybackSpeedModifier CONSTANT_ICHIMONJI =
                (self, entitypatch, speed, prevElapsedTime, elapsedTime) -> 1.1F;

        ICHIMONJI_FIRST =
                builder.nextAccessor("biped/skill/ichimonji_first", (accessor) ->
                        new AttackAnimation(0.15F,1.05F,1.05F,1.35F,2.35F,
                                InteractionHand.MAIN_HAND,null, Armatures.BIPED.get().toolR, accessor,Armatures.BIPED)
                                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_SHARP.get())
                                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_ICHIMONJI)
                                .newTimePair(0.0F,1.35F).addStateRemoveOld(EntityState.CAN_BASIC_ATTACK,false));

        ICHIMONJI_SECOND =
                builder.nextAccessor("biped/skill/ichimonji_second", (accessor) ->
                        new AttackAnimation(0.05F,0.15F,0.15F,0.35F,1.35F,
                                InteractionHand.MAIN_HAND,null, Armatures.BIPED.get().toolR, accessor,Armatures.BIPED)
                                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_SHARP.get())
                                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, CONSTANT_ICHIMONJI)
                                .newTimePair(0.0F,0.35F).addStateRemoveOld(EntityState.CAN_BASIC_ATTACK,false));

    }
}
