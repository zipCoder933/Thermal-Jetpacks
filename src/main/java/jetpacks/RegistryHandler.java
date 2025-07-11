package jetpacks;

import jetpacks.enchantment.EnchantmentFuelEfficiency;
import jetpacks.item.*;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static jetpacks.ThermalJetpacks.MOD_ID;

public class RegistryHandler {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MOD_ID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MOD_ID);
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MOD_ID);
    public static final DeferredRegister<MenuType<?>> MENU = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MOD_ID);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        RECIPE_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        PARTICLE_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        MENU.register(FMLJavaModLoadingContext.get().getModEventBus());
        SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Particles:
//    public static final RegistryObject<SimpleParticleType> RAINBOW_PARTICLE = PARTICLE_TYPES.register("rainbow_particle", () -> new SimpleParticleType(false));
    // Recipes:
//    public static final RegistryObject<RecipeSerializer<JetpackCustomRecipe>> JETPACK_CUSTOM_RECIPE = RECIPE_SERIALIZERS.register("jetpack_custom_recipe",() -> new SimpleCraftingRecipeSerializer<>((location, category) -> new JetpackCustomRecipe(location)));

    // Enchantments:
    public static final EnchantmentCategory JETPACK_ENCHANTMENT_TYPE = EnchantmentCategory.create("JETPACK", (item -> item instanceof JetpackItem));
    public static final RegistryObject<EnchantmentFuelEfficiency> FUEL_EFFICIENCY = ENCHANTMENTS.register("fuel_efficiency", EnchantmentFuelEfficiency::new);

    // Items:
//    public static final RegistryObject<SJItem> PARTICLE_BLEND = ITEMS.register("particle_blend", SJItem::new);
//    public static final RegistryObject<SJItem> PARTICLE_NONE = ITEMS.register("particle_none", SJItem::new);
//    public static final RegistryObject<SJItem> PARTICLE_FLAME = ITEMS.register("particle_flame", SJItem::new);
//    public static final RegistryObject<SJItem> PARTICLE_SMOKE = ITEMS.register("particle_smoke", SJItem::new);
//    public static final RegistryObject<SJItem> PARTICLE_RAINBOW = ITEMS.register("particle_rainbow", SJItem::new);
//    public static final RegistryObject<SJItem> PARTICLE_SOUL = ITEMS.register("particle_soul", SJItem::new);
//    public static final RegistryObject<SJItem> PARTICLE_SNOW = ITEMS.register("particle_snow", SJItem::new);

    public static final RegistryObject<PilotGogglesItem> PILOT_GOGGLES_GOLD = ITEMS.register("pilot_goggles_gold", () -> new PilotGogglesItem("gold"));
    public static final RegistryObject<PilotGogglesItem> PILOT_GOGGLES_IRON = ITEMS.register("pilot_goggles_iron", () -> new PilotGogglesItem("iron"));

    public static final RegistryObject<SJItem> LEATHER_STRAP = ITEMS.register("leather_strap", SJItem::new);
    public static final RegistryObject<SJItem> CRYOGENIC_CRYSTAL = ITEMS.register("cryogenic_crystal", SJItem::new);
    public static final RegistryObject<SJItem> UNIT_CRYOGENIC = ITEMS.register("unit_cryogenic", SJItem::new);
    public static final RegistryObject<SJItem> UNIT_GLOWSTONE_EMPTY = ITEMS.register("unit_glowstone_empty", SJItem::new);
    public static final RegistryObject<SJItem> UNIT_GLOWSTONE = ITEMS.register("unit_glowstone", SJItem::new);

    public static final RegistryObject<SJItem> THRUSTER_TE1 = ITEMS.register("thruster_te1", SJItem::new);
    public static final RegistryObject<SJItem> THRUSTER_TE2 = ITEMS.register("thruster_te2", SJItem::new);
    public static final RegistryObject<SJItem> THRUSTER_TE3 = ITEMS.register("thruster_te3", SJItem::new);
    public static final RegistryObject<SJItem> THRUSTER_TE4 = ITEMS.register("thruster_te4", SJItem::new);
    public static final RegistryObject<SJItem> THRUSTER_TE5 = ITEMS.register("thruster_te5", SJItem::new);
    public static final RegistryObject<SJItem> FLUX_CHESTPLATE = ITEMS.register("flux_chestplate", SJItem::new);

    public static final RegistryObject<JetpackItem> JETPACK_TE1 = ITEMS.register("jetpack_te1", () -> new JetpackItem(JetpackType.TE1, false));
    public static final RegistryObject<JetpackItem> JETPACK_TE2 = ITEMS.register("jetpack_te2", () -> new JetpackItem(JetpackType.TE2, false));
    public static final RegistryObject<JetpackItem> JETPACK_TE3 = ITEMS.register("jetpack_te3", () -> new JetpackItem(JetpackType.TE3, false));
    public static final RegistryObject<JetpackItem> JETPACK_TE4 = ITEMS.register("jetpack_te4", () -> new JetpackItem(JetpackType.TE4, false));
    //    public static final RegistryObject<JetpackItem> JETPACK_TE5 = ITEMS.register("jetpack_te5", () -> new JetpackItem(JetpackType.TE5, JetpackArmorMaterial.JETPLATE, false));
    public static final RegistryObject<JetpackItem> JETPACK_TE5_ARMORED = ITEMS.register("jetpack_te5_enderium", () -> new JetpackItem(JetpackType.TE5_ARMORED, JetpackArmorMaterial.JETPLATE, false));
    //    public static final RegistryObject<JetpackItem> JETPACK_TE1_ARMORED = ITEMS.register("jetpack_te1_armored", () -> new JetpackItem(JetpackType.TE1_ARMORED));
    //    public static final RegistryObject<JetpackItem> JETPACK_TE2_ARMORED = ITEMS.register("jetpack_te2_armored", () -> new JetpackItem(JetpackType.TE2_ARMORED));
    //    public static final RegistryObject<JetpackItem> JETPACK_TE3_ARMORED = ITEMS.register("jetpack_te3_armored", () -> new JetpackItem(JetpackType.TE3_ARMORED));
    //    public static final RegistryObject<JetpackItem> JETPACK_TE4_ARMORED = ITEMS.register("jetpack_te4_armored", () -> new JetpackItem(JetpackType.TE4_ARMORED));


    public static final RegistryObject<JetpackItem> JETPACK_CREATIVE = ITEMS.register("jetpack_creative", () -> new JetpackItem(JetpackType.CREATIVE, true));
    public static final RegistryObject<JetpackItem> JETPACK_CREATIVE_ARMORED = ITEMS.register("jetpack_creative_armored", () -> new JetpackItem(JetpackType.CREATIVE_ARMORED, true));


//    public static final RegistryObject<SJItem> ARMORPLATING_TE1 = ITEMS.register("armorplating_te1", SJItem::new);
//    public static final RegistryObject<SJItem> ARMORPLATING_TE2 = ITEMS.register("armorplating_te2", SJItem::new);
//    public static final RegistryObject<SJItem> ARMORPLATING_TE3 = ITEMS.register("armorplating_te3", SJItem::new);
//    public static final RegistryObject<SJItem> ARMORPLATING_TE4 = ITEMS.register("armorplating_te4", SJItem::new);
//    public static final RegistryObject<SJItem> ARMORPLATING_TE5 = ITEMS.register("armorplating_te5", SJItem::new);
//    public static final RegistryObject<SJItem> ARMORPLATING_TE5_ENDERIUM = ITEMS.register("armorplating_te5_enderium", SJItem::new);


//    public static final RegistryObject<JetpackItem> JETPACK_VANILLA1 = ITEMS.register("jetpack_vanilla1", () -> new JetpackItem(JetpackType.VANILLA1));
//    public static final RegistryObject<JetpackItem> JETPACK_VANILLA1_ARMORED = ITEMS.register("jetpack_vanilla1_armored", () -> new JetpackItem(JetpackType.VANILLA1_ARMORED));
//    public static final RegistryObject<JetpackItem> JETPACK_VANILLA2 = ITEMS.register("jetpack_vanilla2", () -> new JetpackItem(JetpackType.VANILLA2));
//    public static final RegistryObject<JetpackItem> JETPACK_VANILLA2_ARMORED = ITEMS.register("jetpack_vanilla2_armored", () -> new JetpackItem(JetpackType.VANILLA2_ARMORED));
//    public static final RegistryObject<JetpackItem> JETPACK_VANILLA3 = ITEMS.register("jetpack_vanilla3", () -> new JetpackItem(JetpackType.VANILLA3));
//    public static final RegistryObject<JetpackItem> JETPACK_VANILLA3_ARMORED = ITEMS.register("jetpack_vanilla3_armored", () -> new JetpackItem(JetpackType.VANILLA3_ARMORED));
//    public static final RegistryObject<JetpackItem> JETPACK_VANILLA4 = ITEMS.register("jetpack_vanilla4", () -> new JetpackItem(JetpackType.VANILLA4));
//    public static final RegistryObject<JetpackItem> JETPACK_VANILLA4_ARMORED = ITEMS.register("jetpack_vanilla4_armored", () -> new JetpackItem(JetpackType.VANILLA4_ARMORED));
//    public static final RegistryObject<SJItem> THRUSTER_VANILLA1 = ITEMS.register("thruster_vanilla1", SJItem::new);
//    public static final RegistryObject<SJItem> THRUSTER_VANILLA2 = ITEMS.register("thruster_vanilla2", SJItem::new);
//    public static final RegistryObject<SJItem> THRUSTER_VANILLA3 = ITEMS.register("thruster_vanilla3", SJItem::new);
//    public static final RegistryObject<SJItem> THRUSTER_VANILLA4 = ITEMS.register("thruster_vanilla4", SJItem::new);

//    public static final RegistryObject<JetpackItem> JETPACK_MEK1 = ITEMS.register("jetpack_mek1", () -> new JetpackItem(JetpackType.MEK1));
//    public static final RegistryObject<JetpackItem> JETPACK_MEK1_ARMORED = ITEMS.register("jetpack_mek1_armored", () -> new JetpackItem(JetpackType.MEK1_ARMORED));
//    public static final RegistryObject<JetpackItem> JETPACK_MEK2 = ITEMS.register("jetpack_mek2", () -> new JetpackItem(JetpackType.MEK2));
//    public static final RegistryObject<JetpackItem> JETPACK_MEK2_ARMORED = ITEMS.register("jetpack_mek2_armored", () -> new JetpackItem(JetpackType.MEK2_ARMORED));
//    public static final RegistryObject<JetpackItem> JETPACK_MEK3 = ITEMS.register("jetpack_mek3", () -> new JetpackItem(JetpackType.MEK3));
//    public static final RegistryObject<JetpackItem> JETPACK_MEK3_ARMORED = ITEMS.register("jetpack_mek3_armored", () -> new JetpackItem(JetpackType.MEK3_ARMORED));
//    public static final RegistryObject<JetpackItem> JETPACK_MEK4 = ITEMS.register("jetpack_mek4", () -> new JetpackItem(JetpackType.MEK4));
//    public static final RegistryObject<JetpackItem> JETPACK_MEK4_ARMORED = ITEMS.register("jetpack_mek4_armored", () -> new JetpackItem(JetpackType.MEK4_ARMORED));
//    public static final RegistryObject<SJItem> ARMORPLATING_MEK1 = ITEMS.register("armorplating_mek1", SJItem::new);
//    public static final RegistryObject<SJItem> ARMORPLATING_MEK2 = ITEMS.register("armorplating_mek2", SJItem::new);
//    public static final RegistryObject<SJItem> ARMORPLATING_MEK3 = ITEMS.register("armorplating_mek3", SJItem::new);
//    public static final RegistryObject<SJItem> ARMORPLATING_MEK4 = ITEMS.register("armorplating_mek4", SJItem::new);
//    public static final RegistryObject<SJItem> THRUSTER_MEK1 = ITEMS.register("thruster_mek1", SJItem::new);
//    public static final RegistryObject<SJItem> THRUSTER_MEK2 = ITEMS.register("thruster_mek2", SJItem::new);
//    public static final RegistryObject<SJItem> THRUSTER_MEK3 = ITEMS.register("thruster_mek3", SJItem::new);
//    public static final RegistryObject<SJItem> THRUSTER_MEK4 = ITEMS.register("thruster_mek4", SJItem::new);


//    public static final RegistryObject<JetpackItem> JETPACK_IE1 = ITEMS.register("jetpack_ie1", () -> new JetpackItem(JetpackType.IE1));
//    public static final RegistryObject<JetpackItem> JETPACK_IE1_ARMORED = ITEMS.register("jetpack_ie1_armored", () -> new JetpackItem(JetpackType.IE1_ARMORED));
//    public static final RegistryObject<JetpackItem> JETPACK_IE2 = ITEMS.register("jetpack_ie2", () -> new JetpackItem(JetpackType.IE2));
//    public static final RegistryObject<JetpackItem> JETPACK_IE2_ARMORED = ITEMS.register("jetpack_ie2_armored", () -> new JetpackItem(JetpackType.IE2_ARMORED));
//    public static final RegistryObject<JetpackItem> JETPACK_IE3 = ITEMS.register("jetpack_ie3", () -> new JetpackItem(JetpackType.IE3));
//    public static final RegistryObject<JetpackItem> JETPACK_IE3_ARMORED = ITEMS.register("jetpack_ie3_armored", () -> new JetpackItem(JetpackType.IE3_ARMORED));
//    public static final RegistryObject<SJItem> ARMORPLATING_IE1 = ITEMS.register("armorplating_ie1", SJItem::new);
//    public static final RegistryObject<SJItem> ARMORPLATING_IE2 = ITEMS.register("armorplating_ie2", SJItem::new);
//    public static final RegistryObject<SJItem> ARMORPLATING_IE3 = ITEMS.register("armorplating_ie3", SJItem::new);
//    public static final RegistryObject<SJItem> THRUSTER_IE1 = ITEMS.register("thruster_ie1", SJItem::new);
//    public static final RegistryObject<SJItem> THRUSTER_IE2 = ITEMS.register("thruster_ie2", SJItem::new);
//    public static final RegistryObject<SJItem> THRUSTER_IE3 = ITEMS.register("thruster_ie3", SJItem::new);


    // Sound Events
    public static final RegistryObject<SoundEvent> JETPACK_SOUND = SOUNDS.register("jetpack", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "jetpack")));
    //    public static final RegistryObject<SoundEvent> JETPACK_OTHER_SOUND = SOUNDS.register("jetpack_other", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "jetpack_other")));
    public static final RegistryObject<SoundEvent> ROCKET_SOUND = SOUNDS.register("rocket", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MOD_ID, "rocket")));
}
