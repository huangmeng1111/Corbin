package com.super_awesome_baby.corbin.skill.passive;

import com.super_awesome_baby.corbin.gameasset.CorbinAnimations;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.network.EpicFightNetworkManager;
import yesman.epicfight.network.client.CPSkillRequest;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillSlot;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener.EventType;

import java.util.UUID;

public class SekiroSkill extends PassiveSkill {
    private static final UUID EVENT_UUID = UUID.fromString("a416c93a-42cb-11eb-b378-0242ac170003");
    private boolean wasRightMouseDown;
    private boolean wasLeftMouseDown;
    private boolean hasTriggeredThisPress;

    public static Builder createSekiroBuilder() {
        return (new Builder())
                .setCategory(SkillCategories.PASSIVE)
                .setActivateType(ActivateType.ONE_SHOT);
    }

    public static class Builder extends SkillBuilder<SekiroSkill> {}

    public SekiroSkill(Builder builder) {
        super(builder);
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);

        PlayerEventListener listener = container.getExecutor().getEventListener();

        if (container.getExecutor().isLogicalClient()) {
            listener.addEventListener(EventType.CLIENT_ITEM_USE_EVENT, EVENT_UUID, (event) -> {
                handleKeyInput(container);
            });
        }
    }

    @Override
    public void onRemoved(SkillContainer container) {
        container.getExecutor().getEventListener().removeListener(EventType.CLIENT_ITEM_USE_EVENT, EVENT_UUID);
    }

    @Override
    public void updateContainer(SkillContainer container) {
        super.updateContainer(container);

        if (container.getExecutor().isLogicalClient()) {
            handleKeyInput(container);
        }
    }

    private void handleKeyInput(SkillContainer container) {
        long windowHandle = Minecraft.getInstance().getWindow().getWindow();
        boolean isRightMouseDown = GLFW.glfwGetMouseButton(windowHandle, GLFW.GLFW_MOUSE_BUTTON_RIGHT) == GLFW.GLFW_PRESS;
        boolean isLeftMouseDown = GLFW.glfwGetMouseButton(windowHandle, GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS;

        if (isRightMouseDown && !wasRightMouseDown) {
            wasRightMouseDown = true;
            hasTriggeredThisPress = false;
        }

        if (wasRightMouseDown && isLeftMouseDown && !wasLeftMouseDown && !hasTriggeredThisPress) {
            CPSkillRequest packet = new CPSkillRequest(
                    SkillSlot.ENUM_MANAGER.get(container.getSlot().universalOrdinal()),
                    CPSkillRequest.WorkType.CAST
            );
            EpicFightNetworkManager.sendToServer(packet);
            hasTriggeredThisPress = true;
        }

        if (!isRightMouseDown && wasRightMouseDown) {
            wasRightMouseDown = false;
            hasTriggeredThisPress = false;
        }

        wasLeftMouseDown = isLeftMouseDown;
    }

    @Override
    public void executeOnServer(SkillContainer container, FriendlyByteBuf args) {
        container.getServerExecutor().playAnimationSynchronized(CorbinAnimations.TACHI_WHIRLWIND_SLASH, 0.1F);
    }

    @Override
    public boolean shouldDeactivateAutomatically(PlayerPatch<?> executer) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean shouldDraw(SkillContainer container) {
        return false;
    }
}
