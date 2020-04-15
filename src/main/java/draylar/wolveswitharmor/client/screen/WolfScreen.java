package draylar.wolveswitharmor.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import draylar.wolveswitharmor.WolvesWithArmor;
import draylar.wolveswitharmor.container.WolfContainer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.ContainerScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.container.HorseContainer;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class WolfScreen extends ContainerScreen<WolfContainer> {

    private static final Identifier TEXTURE = WolvesWithArmor.id("textures/gui/container/wolf.png");
    private final WolfEntity entity;
    private float mouseX;
    private float mouseY;

    public WolfScreen(WolfContainer container, PlayerInventory inventory, WolfEntity entity) {
        super(container, inventory, entity.getDisplayName());
        this.entity = entity;
        this.passEvents = false;
    }

    @Override
    protected void drawForeground(int mouseX, int mouseY) {
        this.font.draw(this.title.asFormattedString(), 8.0F, 6.0F, 4210752);
        this.font.draw(this.playerInventory.getDisplayName().asFormattedString(), 8.0F, (float)(this.containerHeight - 96 + 2), 4210752);
    }

    @Override
    protected void drawBackground(float delta, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        int i = (this.width - this.containerWidth) / 2;
        int j = (this.height - this.containerHeight) / 2;

        this.blit(i, j, 0, 0, this.containerWidth, this.containerHeight);
        this.blit(i + 7, j + 35, 0, this.containerHeight + 54, 18, 18);


        InventoryScreen.drawEntity(i + 51, j + 60, 17, (float)(i + 51) - this.mouseX, (float)(j + 75 - 50) - this.mouseY, this.entity);
    }

    public void render(int mouseX, int mouseY, float delta) {
        this.renderBackground();
        this.mouseX = (float)mouseX;
        this.mouseY = (float)mouseY;
        super.render(mouseX, mouseY, delta);
        this.drawMouseoverTooltip(mouseX, mouseY);
    }
}