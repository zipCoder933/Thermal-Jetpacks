package jetpacks.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SJAdvancementProviderAdapter extends AdvancementProvider {

    public SJAdvancementProviderAdapter(PackOutput out, CompletableFuture<HolderLookup.Provider> tagLookup) {
        super(out, tagLookup, List.of(new SJAdvancementProvider()));
    }
}