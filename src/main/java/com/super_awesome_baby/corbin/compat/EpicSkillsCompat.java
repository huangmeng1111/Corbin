package com.super_awesome_baby.corbin.compat;

import com.super_awesome_baby.corbin.Corbin;
import com.super_awesome_baby.corbin.client.CorbinCategorySlotTextures;
import com.yesman.epicskills.client.gui.screen.CategorySlotTexture;

public class EpicSkillsCompat {
    public static void registerCategorySlotTexture() {
        CategorySlotTexture.ENUM_MANAGER.registerEnumCls(Corbin.MOD_ID, CorbinCategorySlotTextures.class);
    }
}
