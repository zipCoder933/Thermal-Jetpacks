//package jetpacks.datagen;
//
//import net.minecraft.core.HolderLookup;
//import net.minecraft.data.DataGenerator;
//import net.minecraftforge.common.data.BlockTagsProvider;
//import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
//import net.minecraftforge.common.data.ExistingFileHelper;
//import net.minecraftforge.data.event.GatherDataEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//
//import static org.zipcoder.cyclic.Cyclic.MOD_ID;
//
//@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
//public final class SJDataGenerator {
//
//    private SJDataGenerator() {
//    }
//
//    @SubscribeEvent
//    public static void gatherData(GatherDataEvent event) {
//        DataGenerator gen = event.getGenerator();
//        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
//
//        // Required for 1.20 data-gen
//        BlockTagsProvider dummyProvider = new BlockTagsProvider(gen.getPackOutput(), event.getLookupProvider(), MOD_ID, existingFileHelper) {
//            @Override
//            protected void addTags(HolderLookup.Provider provider) {}
//        };
//
//        // TODO: test the boolean
//        gen.addProvider(true, new SJItemModelProvider(gen, existingFileHelper));
//        gen.addProvider(true, dummyProvider);
//        gen.addProvider(true, new SJItemTagsProvider(gen.getPackOutput(), event.getLookupProvider(), dummyProvider.contentsGetter(), event.getExistingFileHelper()));
//        gen.addProvider(true, new SJRecipeProvider(gen));
//        gen.addProvider(true, new SJAdvancementProviderAdapter(gen.getPackOutput(), event.getLookupProvider()));
//
//        DatapackBuiltinEntriesProvider modRegistryProvider = new SJRegistryProvider(gen.getPackOutput(), event.getLookupProvider());
//        gen.addProvider(true, modRegistryProvider);
//    }
//}