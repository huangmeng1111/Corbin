package com.super_awesome_baby.corbin.client;

import com.yesman.epicskills.client.gui.screen.CategorySlotTexture;

public enum CorbinCategorySlotTextures implements CategorySlotTexture {
    COMBAT_ARTS(6, 6, 44, 44);
    private int offsetX;
    private int offsetY;
    private int texWidth;
    private int texHeight;
    private int universalOrder;

    private CorbinCategorySlotTextures(int offsetX, int offsetY, int texWidth, int texHeight) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.texWidth = texWidth;
        this.texHeight = texHeight;
        this.universalOrder = CategorySlotTexture.ENUM_MANAGER.assign(this);
    }

    public int offsetX() {
        return this.offsetX;
    }

    public int offsetY() {
        return this.offsetY;
    }

    public int texWidth() {
        return this.texWidth;
    }

    public int texHeight() {
        return this.texHeight;
    }

    public int universalOrdinal() {
        return this.universalOrder;
    }
}
