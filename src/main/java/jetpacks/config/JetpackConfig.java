package jetpacks.config;

import net.minecraftforge.common.ForgeConfigSpec;

import static jetpacks.ThermalJetpacks.MOD_ID;


public class JetpackConfig {

    public static void createJetpackConfig(ForgeConfigSpec.Builder builder) {

        for (JetpackDataHolder pack : JetpackDataHolder.DEFAULTS.values()) {
            builder.comment(pack.mod).push(pack.mod);
            builder.comment(pack.name).push(pack.name);

            pack._energyCapacity = builder
                    .comment("The maximum amount of energy that this Jetpack/Fluxpack can hold.")
                    .translation("config."+MOD_ID+".tuning.energyCapacity")
                    .defineInRange("energyCapacity", pack.energyCapacity, 0, Integer.MAX_VALUE);

            pack._energyUsage = builder
                    .comment("The amount of energy that this Jetpack/Fluxpack uses every tick, when being used.")
                    .translation("config."+MOD_ID+".tuning.energyUsage")
                    .defineInRange("energyUsage", pack.energyUsage, 0, Integer.MAX_VALUE);

            pack._energyPerTickIn = builder
                    .comment("The amount of energy that can be inserted into this Jetpack/Fluxpack per tick from external sources.")
                    .translation("config."+MOD_ID+".tuning.energyPerTickIn")
                    .defineInRange("energyPerTickIn", pack.energyPerTickIn, 0, Integer.MAX_VALUE);

            pack._energyPerTickOut = builder
                    .comment("The amount of energy that can be extracted from this Jetpack/Fluxpack per tick by external sources. Also determines how quickly Jetpacks/Fluxpacks can charge other items.")
                    .translation("config."+MOD_ID+".tuning.energyPerTickOut")
                    .defineInRange("energyPerTickOut", pack.energyPerTickOut, 0, Integer.MAX_VALUE);

            pack._armorReduction = builder
                    .comment("How well this Jetpack/Fluxpack can protect the user from damage, if armored. The higher the value, the stronger the armor will be.")
                    .translation("config."+MOD_ID+".tuning.armorReduction")
                    .defineInRange("armorReduction", pack.armorReduction, 0, Integer.MAX_VALUE);

            pack._armorEnergyPerHit = builder
                    .comment("How much energy is lost from this Jetpack/Fluxpack when the user is hit, if armored.")
                    .translation("config."+MOD_ID+".tuning.armorEnergyPerHit")
                    .defineInRange("armorEnergyPerHit", pack.armorEnergyPerHit, 0, Integer.MAX_VALUE);

            pack._enchantability = builder
                    .comment("The enchantability of this Jetpack/Fluxpack. If set to 0, no enchantments can be applied.")
                    .translation("config."+MOD_ID+".tuning.enchantability")
                    .defineInRange("enchantability", pack.enchantability, 0, Integer.MAX_VALUE);

            pack._speedVertical = builder
                    .comment("The maximum vertical speed of this Jetpack when flying.")
                    .translation("config."+MOD_ID+".tuning.speedVertical")
                    .defineInRange("speedVertical", pack.speedVertical, 0, Double.MAX_VALUE);

            pack._accelVertical = builder
                    .comment("The vertical acceleration of this Jetpack when flying, every tick, this amount of vertical speed will be added until maximum speed is reached.")
                    .translation("config."+MOD_ID+".tuning.accelVertical")
                    .defineInRange("accelVertical", pack.accelVertical, 0, Double.MAX_VALUE);

            pack._speedVerticalHover = builder
                    .comment("The maximum vertical speed of this Jetpack when flying in Hover Mode.")
                    .translation("config."+MOD_ID+".tuning.speedVerticalHover")
                    .defineInRange("speedVerticalHover", pack.speedVerticalHover, 0, Double.MAX_VALUE);

            pack._speedVerticalHoverSlow = builder
                    .comment("The maximum vertical speed of this Jetpack when slowly descending in Hover Mode.")
                    .translation("config."+MOD_ID+".tuning.speedVerticalHoverSlow")
                    .defineInRange("speedVerticalHoverSlow", pack.speedVerticalHoverSlow, 0, Double.MAX_VALUE);

            pack._speedSideways = builder
                    .comment("The speed of this Jetpack when flying sideways. This is mostly noticeable in Hover Mode.")
                    .translation("config."+MOD_ID+".tuning.speedSideways")
                    .defineInRange("speedSideways", pack.speedSideways, 0, Double.MAX_VALUE);

            pack._sprintSpeedModifier = builder
                    .comment("How much faster this Jetpack will fly forward when sprinting. Setting this to 1.0 will make sprinting have no effect apart from the added speed from vanilla.")
                    .translation("config."+MOD_ID+".tuning.sprintSpeedModifier")
                    .defineInRange("sprintSpeedModifier", pack.sprintSpeedModifier, 0, Double.MAX_VALUE);

            pack._sprintEnergyModifier = builder
                    .comment("How much more energy this Jetpack will use when sprinting. Setting this to 1.0 will make sprinting have no effect on energy usage.")
                    .translation("config."+MOD_ID+".tuning.sprintEnergyModifier")
                    .defineInRange("sprintEnergyModifier", pack.sprintEnergyModifier, 0, Double.MAX_VALUE);

            pack._hoverMode = builder
                    .comment("When enabled, this Jetpack will be able to activate Hover Mode.")
                    .translation("config."+MOD_ID+".tuning.hoverMode")
                    .define("hoverMode", pack.hoverMode);

            pack._emergencyHoverMode = builder
                    .comment("When enabled, this Jetpack will be able to activate Hover Mode automatically when the wearer is about to die from a fall.")
                    .translation("config."+MOD_ID+".tuning.emergencyHoverMode")
                    .define("emergencyHoverMode", pack.emergencyHoverMode);

            pack._chargerMode = builder
                    .comment("When enabled, this Jetpack will be able to activate Charger Mode.")
                    .translation("config."+MOD_ID+".tuning.chargerMode")
                    .define("chargerMode", pack.chargerMode);

            builder.pop(2);
        }
    }
}
