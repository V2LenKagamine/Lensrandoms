package io.github.v2lenkagamine.client.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import io.github.v2lenkagamine.client.util.RenderUtil;
import io.github.v2lenkagamine.common.containers.GunSmithingTableContainer;
import io.github.v2lenkagamine.common.crafting.gunsmithingtable.GunSmithingIngredient;
import io.github.v2lenkagamine.common.crafting.gunsmithingtable.GunSmithingRecipe;
import io.github.v2lenkagamine.common.crafting.gunsmithingtable.GunSmithingRecipes;
import io.github.v2lenkagamine.common.networking.Networking;
import io.github.v2lenkagamine.common.networking.messages.MessageCraft;
import io.github.v2lenkagamine.common.tileentity.GunSmithingTableTE;
import io.github.v2lenkagamine.core.items.LensItems;
import io.github.v2lenkagamine.core.util.ItemUtil;
import io.github.v2lenkagamine.core.util.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
//This totally isnt *slightly* stolen from MrCrayfish's Gun Mod. Nope. Not at all. Not even inspired by it.
public class GunSmithingTableScreen extends AbstractContainerScreen<GunSmithingTableContainer>{
	
	private static final ResourceLocation GUI_BASE = new ResourceLocation("lensrandoms:textures/gui/gunsmithing.png");
    private static boolean showRemaining = false;

    private Tab currentTab;
    private List<Tab> tabs = new ArrayList<>();
    private List<MaterialItem> materials;
    private List<MaterialItem> filteredMaterials;
    private Inventory playerInventory;
    private GunSmithingTableTE table;
    private Button btnCraft;
    private CheckBox checkBoxMaterials;
    private ItemStack displayStack;
    
    
    public GunSmithingTableScreen(GunSmithingTableContainer container, Inventory playerInventory, Component title)
    {
        super(container, playerInventory, title);
        this.playerInventory = playerInventory;
        this.table = container.getTable();
        this.imageWidth = 275;
        this.imageHeight = 184;
        this.materials = new ArrayList<>();
        this.createTabs(GunSmithingRecipes.getAll(playerInventory.player.level));
        if(!this.tabs.isEmpty())
        {
            this.imageHeight += 28;
        }
    }

    private void createTabs(NonNullList<GunSmithingRecipe> recipes)
    {
        List<GunSmithingRecipe> weapons = new ArrayList<>();
        List<GunSmithingRecipe> ammo = new ArrayList<>();
        List<GunSmithingRecipe> misc = new ArrayList<>();

        for(GunSmithingRecipe recipe : recipes)
        {
            ItemStack output = recipe.getItem();
            if(ModTags.Items.GUNS.getValues().contains(output))
            {
                weapons.add(recipe);
            }
            else if(this.isAmmo(output))
            {
                ammo.add(recipe);
            }
            else
            {
                misc.add(recipe);
            }
        }

        if(!weapons.isEmpty())
        {
            ItemStack icon = new ItemStack(LensItems.GUN_LENS_REVOLVER.get());
            this.tabs.add(new Tab(icon, "weapons", weapons));
        }

        if(!ammo.isEmpty())
        {
            this.tabs.add(new Tab(new ItemStack(LensItems.SIMPLE_BULLET.get()), "ammo", ammo));
        }

        if(!misc.isEmpty())
        {
            this.tabs.add(new Tab(new ItemStack(LensItems.TACTICAL_POUCHES.get()), "misc", misc));
        }

        if(!this.tabs.isEmpty())
        {
            this.currentTab = this.tabs.get(0);
        }
    }

    private boolean isAmmo(ItemStack stack)
    {
        if(ModTags.Items.BULLETS.getValues().contains(stack))
        {
            return true;
        }
        return false;
    }

    @Override
    public void init()
    {
        super.init();
        if(!this.tabs.isEmpty())
        {
            this.topPos += 28;
        }
        this.addRenderableWidget(new Button(this.leftPos + 9, this.topPos + 17, 15, 14, new TextComponent("<"), button ->
        {
            int index = this.currentTab.getCurrentIndex();
            if(index - 1 < 0)
            {
                this.loadItem(this.currentTab.getRecipes().size() - 1);
            }
            else
            {
                this.loadItem(index - 1);
            }
        }));
        this.addRenderableWidget(new Button(this.leftPos + 153, this.topPos + 17, 15, 14, new TextComponent(">"), button ->
        {
            int index = this.currentTab.getCurrentIndex();
            if(index + 1 >= this.currentTab.getRecipes().size())
            {
                this.loadItem(0);
            }
            else
            {
                this.loadItem(index + 1);
            }
        }));
        this.btnCraft = this.addRenderableWidget(new Button(this.leftPos + 172, this.topPos + 16, 98, 20, new TranslatableComponent("lensrandoms.gui.craftgun"), button ->
        {
            int index = this.currentTab.getCurrentIndex();
            GunSmithingRecipe recipe = this.currentTab.getRecipes().get(index);
            ResourceLocation registryName = recipe.getId();
            Networking.sendToServer(new MessageCraft(registryName, this.table.getBlockPos()));
        }));
        this.btnCraft.active = false;
        this.checkBoxMaterials = this.addRenderableWidget(new CheckBox(this.leftPos + 172, this.topPos + 51, new TranslatableComponent("lensrandoms.gui.show_remaining")));
        this.checkBoxMaterials.setToggled(GunSmithingTableScreen.showRemaining);
        if(this.currentTab == null) {
        	this.currentTab = this.tabs.get(0);
        }
        this.loadItem(this.currentTab.getCurrentIndex());
    }

    @Override
    public void containerTick()
    {
        super.containerTick();

        for(MaterialItem material : this.materials)
        {
            material.tick();
        }

        boolean canCraft = true;
        for(MaterialItem material : this.materials)
        {
            if(!material.isEnabled())
            {
                canCraft = false;
                break;
            }
        }

        this.btnCraft.active = canCraft;
    }

    

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton)
    {
        boolean result = super.mouseClicked(mouseX, mouseY, mouseButton);
        GunSmithingTableScreen.showRemaining = this.checkBoxMaterials.isToggled();

        for(int i = 0; i < this.tabs.size(); i++)
        {
            if(RenderUtil.isMouseWithin((int) mouseX, (int) mouseY, this.leftPos + 28 * i, this.topPos - 28, 28, 28))
            {
                this.currentTab = this.tabs.get(i);
                this.loadItem(this.currentTab.getCurrentIndex());
                this.minecraft.getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                return true;
            }
        }

        return result;
    }

    private void loadItem(int index)
    {
        GunSmithingRecipe recipe = this.currentTab.getRecipes().get(index);
        this.displayStack = recipe.getItem().copy();

        this.materials.clear();

        List<GunSmithingIngredient> ingredients = recipe.getMaterials();
        if(ingredients != null)
        {
            for(GunSmithingIngredient ingredient : ingredients)
            {
                MaterialItem item = new MaterialItem(ingredient);
                item.updateEnabledState();
                this.materials.add(item);
            }

            this.currentTab.setCurrentIndex(index);
        }
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(poseStack, mouseX, mouseY);

        int startX = this.leftPos;
        int startY = this.topPos;

        for(int i = 0; i < this.tabs.size(); i++)
        {
            if(RenderUtil.isMouseWithin(mouseX, mouseY, startX + 28 * i, startY - 28, 28, 28))
            {
                this.renderTooltip(poseStack, new TranslatableComponent(this.tabs.get(i).getTabKey()), mouseX, mouseY);
                return;
            }
        }

        for(int i = 0; i < this.filteredMaterials.size(); i++)
        {
            int itemX = startX + 172;
            int itemY = startY + i * 19 + 63;
            if(RenderUtil.isMouseWithin(mouseX, mouseY, itemX, itemY, 80, 19))
            {
                MaterialItem materialItem = this.filteredMaterials.get(i);
                if(materialItem != MaterialItem.EMPTY)
                {
                    this.renderTooltip(poseStack, materialItem.getDisplayStack(), mouseX, mouseY);
                    return;
                }
            }
        }

        /*
        if(RenderUtil.isMouseWithin(mouseX, mouseY, startX + 8, startY + 38, 48, 24))
        {
            this.renderTooltip(poseStack, this.displayStack, mouseX, mouseY);
        }
        */
    }

    @Override
    protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY)
    {
        int offset = this.tabs.isEmpty() ? 0 : 28;
        this.font.draw(poseStack, this.title, (float)this.titleLabelX, (float)this.titleLabelY - 28 + offset, 4210752);
        this.font.draw(poseStack, this.playerInventory.getDisplayName(), (float)this.inventoryLabelX, (float)this.inventoryLabelY - 9 + offset, 4210752);
    }

    @SuppressWarnings("resource")
	@Override
    protected void renderBg(PoseStack poseStack, float partialTicks, int mouseX, int mouseY)
    {
        /* Fixes partial ticks to use percentage from 0 to 1 */
        partialTicks = Minecraft.getInstance().getFrameTime();

        int startX = this.leftPos;
        int startY = this.topPos;

        RenderSystem.enableBlend();

        /* Draw unselected tabs */
        for(int i = 0; i < this.tabs.size(); i++)
        {
            Tab tab = this.tabs.get(i);
            if(tab != this.currentTab)
            {
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.setShaderTexture(0, GUI_BASE);
                this.blit(poseStack, startX + 28 * i, startY - 28, 80, 184, 28, 32);
                Minecraft.getInstance().getItemRenderer().renderAndDecorateItem(tab.getIcon(), startX + 28 * i + 6, startY - 28 + 8);
                Minecraft.getInstance().getItemRenderer().renderGuiItemDecorations(this.font, tab.getIcon(), startX + 28 * i + 6, startY - 28 + 8, null);
            }
        }

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_BASE);
        this.blit(poseStack, startX, startY, 0, 0, 173, 184);
        blit(poseStack, startX + 173, startY, 78, 184, 173, 0, 1, 184, 256, 256);
        this.blit(poseStack, startX + 251, startY, 174, 0, 24, 184);
        this.blit(poseStack, startX + 172, startY + 16, 198, 0, 20, 20);

        /* Draw selected tab */
        if(this.currentTab != null)
        {
            int i = this.tabs.indexOf(this.currentTab);
            int u = i == 0 ? 80 : 108;
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, GUI_BASE);
            this.blit(poseStack, startX + 28 * i, startY - 28, u, 214, 28, 32);
            Minecraft.getInstance().getItemRenderer().renderAndDecorateItem(this.currentTab.getIcon(), startX + 28 * i + 6, startY - 28 + 8);
            Minecraft.getInstance().getItemRenderer().renderGuiItemDecorations(this.font, this.currentTab.getIcon(), startX + 28 * i + 6, startY - 28 + 8, null);
        }

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_BASE);

        if(this.table.getItem(0).isEmpty())
        {
            this.blit(poseStack, startX + 174, startY + 18, 165, 199, 16, 16);
        }
    	ItemStack currentItem = new ItemStack(LensItems.SIMPLE_BULLET.get());
        if(this.displayStack != null) {
        	currentItem = this.displayStack;
        }

        StringBuilder builder = new StringBuilder(currentItem.getHoverName().getString());
        if(currentItem.getCount() > 1)
        {
            builder.append(ChatFormatting.GOLD);
            builder.append(ChatFormatting.BOLD);
            builder.append("x");
            builder.append(currentItem.getCount());
        }	
        drawCenteredString(poseStack, this.font, builder.toString(), startX + 88, startY + 20, Color.WHITE.getRGB());
        Minecraft.getInstance().getItemRenderer().renderAndDecorateItem(currentItem, startX + 44, startY + 53);
        //As far as I can tell, this is for rendering an items actual MODEL, which my guns dont have, but Im keeping around incase I end up adding them.
        /*
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        RenderUtil.scissor(startX + 8, startY + 17, 160, 70);
        
        PoseStack modelViewStack = RenderSystem.getModelViewStack();
        modelViewStack.pushPose();
        {
            modelViewStack.translate(startX + 88, startY + 60, 100);
            modelViewStack.scale(50F, -50F, 50F);
            modelViewStack.mulPose(Vector3f.XP.rotationDegrees(5F));
          //  modelViewStack.mulPose(Vector3f.YP.rotationDegrees(Minecraft.getInstance().player.tickCount + partialTicks));
            RenderSystem.applyModelViewMatrix();
            MultiBufferSource.BufferSource buffer = this.minecraft.renderBuffers().bufferSource();
           // Minecraft.getInstance().getItemRenderer().render(currentItem, ItemTransforms.TransformType.FIXED, false, poseStack, buffer, 15728880, OverlayTexture.NO_OVERLAY, RenderUtil.getModel(currentItem));
            
            buffer.endBatch();
        }
        modelViewStack.popPose();
        RenderSystem.applyModelViewMatrix();

        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        	*/
        this.filteredMaterials = this.getMaterials();
        for(int i = 0; i < this.filteredMaterials.size(); i++)
        {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, GUI_BASE);

            MaterialItem materialItem = this.filteredMaterials.get(i);
            ItemStack stack = materialItem.getDisplayStack();
            if(!stack.isEmpty())
            {
                Lighting.setupForFlatItems();
                if(materialItem.isEnabled())
                {
                    this.blit(poseStack, startX + 172, startY + i * 19 + 63, 0, 184, 80, 19);
                }
                else
                {
                    this.blit(poseStack, startX + 172, startY + i * 19 + 63, 0, 222, 80, 19);
                }

                String name = stack.getHoverName().getString();
                if(this.font.width(name) > 55)
                {
                    name = this.font.plainSubstrByWidth(name, 50).trim() + "...";
                }
                this.font.draw(poseStack, name, startX + 172 + 22, startY + i * 19 + 6 + 63, Color.WHITE.getRGB());

                Minecraft.getInstance().getItemRenderer().renderAndDecorateItem(stack, startX + 172 + 2, startY + i * 19 + 1 + 63);

                if(this.checkBoxMaterials.isToggled())
                {
                    int count = ItemUtil.findItemStackContainer(Minecraft.getInstance().player, stack);
                    stack = stack.copy();
                    stack.setCount(stack.getCount() - count);
                }

                Minecraft.getInstance().getItemRenderer().renderGuiItemDecorations(this.font, stack, startX + 172 + 2, startY + i * 19 + 1 + 63, null);
            }
        }
    }

    private List<MaterialItem> getMaterials()
    {
        List<MaterialItem> materials = NonNullList.withSize(6, MaterialItem.EMPTY);
        List<MaterialItem> filteredMaterials = this.materials.stream().filter(materialItem -> this.checkBoxMaterials.isToggled() ? !materialItem.isEnabled() : materialItem != MaterialItem.EMPTY).collect(Collectors.toList());
        for(int i = 0; i < filteredMaterials.size() && i < materials.size(); i++)
        {
            materials.set(i, filteredMaterials.get(i));
        }
        return materials;
    }

    public List<Tab> getTabs()
    {
        return ImmutableList.copyOf(this.tabs);
    }

    public static class MaterialItem
    {
        public static final MaterialItem EMPTY = new MaterialItem();

        private long lastTime = System.currentTimeMillis();
        private int displayIndex;
        private boolean enabled = false;
        private GunSmithingIngredient ingredient;
        private final List<ItemStack> displayStacks = new ArrayList<>();

        private MaterialItem() {}

        private MaterialItem(GunSmithingIngredient ingredient)
        {
            this.ingredient = ingredient;
            Stream.of(ingredient.getItems()).forEach(stack -> {
                ItemStack displayStack = stack.copy();
                displayStack.setCount(ingredient.getCount());
                this.displayStacks.add(displayStack);
            });
        }

        public GunSmithingIngredient getIngredient()
        {
            return this.ingredient;
        }

        public void tick()
        {
            if(this.ingredient == null)
                return;

            this.updateEnabledState();
            long currentTime = System.currentTimeMillis();
            if(currentTime - this.lastTime >= 1000)
            {
                this.displayIndex = (this.displayIndex + 1) % this.displayStacks.size();
                this.lastTime = currentTime;
            }
        }

        public ItemStack getDisplayStack()
        {
            return this.ingredient != null ? this.displayStacks.get(this.displayIndex) : ItemStack.EMPTY;
        }

        @SuppressWarnings("resource")
		public void updateEnabledState()
        {
            this.enabled = ItemUtil.findGSIContainer(Minecraft.getInstance().player, this.ingredient);
        }

        public boolean isEnabled()
        {
            return this.ingredient == null || this.enabled;
        }
    }

    private static class Tab
    {
        private final ItemStack icon;
        private final String id;
        private final List<GunSmithingRecipe> items;
        private int currentIndex;

        public Tab(ItemStack icon, String id, List<GunSmithingRecipe> items)
        {
            this.icon = icon;
            this.id = id;
            this.items = items;
        }

        public ItemStack getIcon()
        {
            return this.icon;
        }

        public String getTabKey()
        {
            return "lensrandoms.gui.gunsmithing.tab." + this.id;
        }

        public void setCurrentIndex(int currentIndex)
        {
            this.currentIndex = currentIndex;
        }

        public int getCurrentIndex()
        {
            return this.currentIndex;
        }

        public List<GunSmithingRecipe> getRecipes()
        {
            return this.items;
        }
    }
}

