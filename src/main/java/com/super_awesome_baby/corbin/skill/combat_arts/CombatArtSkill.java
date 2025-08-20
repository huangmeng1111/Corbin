package com.super_awesome_baby.corbin.skill.combat_arts;

import com.super_awesome_baby.corbin.gameasset.CorbinSkillCategories;
import com.super_awesome_baby.corbin.gameasset.CorbinSkillKey;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import org.lwjgl.glfw.GLFW;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.client.input.EpicFightKeyMappings;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCategory;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class CombatArtSkill extends Skill {
    private static final UUID EVENT_UUID = UUID.fromString("a416c93a-42cb-11eb-b378-0242ac171233");

    protected List<AnimationManager.AnimationAccessor<? extends StaticAnimation>> comboAnimations = new ArrayList<>();


    public static SkillBuilder<CombatArtSkill> createCombatArtBuilder() {
        return new SkillBuilder<CombatArtSkill>().setCategory(CorbinSkillCategories.COMBAT_ARTS).setActivateType(ActivateType.ONE_SHOT).setResource(Resource.NONE);
    }

    public CombatArtSkill(SkillBuilder<? extends Skill> builder) {
        super(builder);
        setComboAnimations();
    }

    //自定义流派招式连段列表
    public abstract void setComboAnimations();

    @Override
    public void onInitiate(SkillContainer container) {
        container.getExecutor().getEventListener().addEventListener(PlayerEventListener.EventType.BASIC_ATTACK_EVENT,EVENT_UUID,event -> {
            //检查流派技能连段计数器
            int comboIndex = container.getDataManager().getDataValue(CorbinSkillKey.COMBAT_ART_INDEX.get());

            if(comboIndex > 0 && comboIndex < comboAnimations.size()){
                //拦截普攻，替换为流派招式
                event.setCanceled(true);
                container.getExecutor().playAnimationSynchronized(comboAnimations.get(comboIndex),0F);
                container.getDataManager().setDataSync(CorbinSkillKey.COMBAT_ART_INDEX.get(),++comboIndex);
            }

            //全部执行完毕，结束流派，重置计数器
            if(comboIndex == comboAnimations.size()){
                container.getDataManager().setDataSync(CorbinSkillKey.COMBAT_ART_INDEX.get(),CorbinSkillKey.COMBAT_ART_INDEX.get().defaultValue());
            }
        });


        container.getExecutor().getEventListener().addEventListener(PlayerEventListener.EventType.ACTION_EVENT_SERVER,EVENT_UUID,event -> {
            int comboIndex = container.getDataManager().getDataValue(CorbinSkillKey.COMBAT_ART_INDEX.get());
            AnimationManager.AnimationAccessor<? extends StaticAnimation> currentAnimation = event.getAnimation();
            //被其他动作打断
            if(comboIndex > 0 && !comboAnimations.contains(currentAnimation)){
                container.getDataManager().setDataSync(CorbinSkillKey.COMBAT_ART_INDEX.get(),CorbinSkillKey.COMBAT_ART_INDEX.get().defaultValue());
            }
        });
    }

    @Override
    public void onRemoved(SkillContainer container) {
        container.getExecutor().getEventListener().removeListener(PlayerEventListener.EventType.BASIC_ATTACK_EVENT,EVENT_UUID);
        container.getExecutor().getEventListener().removeListener(PlayerEventListener.EventType.ACTION_EVENT_SERVER,EVENT_UUID);
    }

    @Override
    public boolean isExecutableState(PlayerPatch<?> executor) {
        WeaponCategory categories = executor.getAdvancedHoldingItemCapability(InteractionHand.MAIN_HAND).getWeaponCategory();
        boolean hold_weapon = (categories != CapabilityItem.WeaponCategories.NOT_WEAPON && categories != CapabilityItem.WeaponCategories.FIST);
        return hold_weapon && !executor.getOriginal().isSpectator() && !executor.isInAir() && executor.getEntityState().canUseSkill();
    }

    @Override
    public void executeOnServer(SkillContainer container, FriendlyByteBuf args) {

    }

    @Override
    public void updateContainer(SkillContainer container) {
        super.updateContainer(container);


        if(container.getExecutor().isLogicalClient()) {
            PlayerPatch<?> playerPatch = container.getExecutor();

            //神秘左键检测失败，用GLFW查
            long windowHandle = Minecraft.getInstance().getWindow().getWindow();
            //之前是否按下
            boolean lastM1Down = container.getDataManager().getDataValue(CorbinSkillKey.LAST_ATTACK_CLICKED.get());
            //当前是否按下
            boolean isM1Down = GLFW.glfwGetMouseButton(windowHandle, GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS;

            //检测右键
            boolean isM2Down = EpicFightKeyMappings.GUARD.isDown();

            //判断按键顺序 先右键，再左键
            boolean canActive = isM2Down && !lastM1Down && isM1Down;

            //同步左键状态
            container.getDataManager().setDataSync(CorbinSkillKey.LAST_ATTACK_CLICKED.get(),isM1Down);

            //是否开启过技能（如果开启过需要松开按键才能重置，避免自动执行）
            boolean skillUsed = container.getDataManager().getDataValue(CorbinSkillKey.COMBAT_ART_START.get());

            //连段计数器
            int comboIndex = container.getDataManager().getDataValue(CorbinSkillKey.COMBAT_ART_INDEX.get());

            //执行攻击第一段
            if(canActive && isExecutableState(playerPatch) && comboIndex == 0 && !skillUsed) {
                //标记为已开启
                container.getDataManager().setDataSync(CorbinSkillKey.COMBAT_ART_START.get(),true);
                //执行技能
                container.getExecutor().playAnimationSynchronized(comboAnimations.get(comboIndex),0F);
                container.getDataManager().setDataSync(CorbinSkillKey.COMBAT_ART_INDEX.get(),++comboIndex);
                //全部执行完毕，结束流派，重置计数器
                if(comboAnimations.size() == 1){
                    container.getDataManager().setDataSync(CorbinSkillKey.COMBAT_ART_INDEX.get(),CorbinSkillKey.COMBAT_ART_INDEX.get().defaultValue());
                }
            }

            //结束时重置状态
            if(skillUsed && comboIndex == 0 && !isM2Down) {
                container.getDataManager().setDataSync(CorbinSkillKey.COMBAT_ART_START.get(),false);
            }
        }

        //一段时间没动作时，重置连段
        if (!container.getExecutor().isLogicalClient() && container.getExecutor().getTickSinceLastAction() > 16) {
            if(container.getDataManager().getDataValue(CorbinSkillKey.COMBAT_ART_INDEX.get()) > 0){
                container.getDataManager().setDataSync(CorbinSkillKey.COMBAT_ART_INDEX.get(),CorbinSkillKey.COMBAT_ART_INDEX.get().defaultValue());
            }
        }
    }
}
