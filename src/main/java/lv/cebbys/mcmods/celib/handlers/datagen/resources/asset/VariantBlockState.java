package lv.cebbys.mcmods.celib.handlers.datagen.resources.asset;

import lv.cebbys.mcmods.celib.loggers.CelibLogger;

import java.util.HashMap;
import java.util.Map;

public class VariantBlockState extends BlockState<VariantBlockState> {

    private final Map<String, VariantBuilder> variants = new HashMap<>();

    public VariantBuilder variant(String key) {
        final VariantBuilder builder = new VariantBuilder();
        this.variants.put(key, builder);
        return builder;
    }

    public VariantBuilder variant() {
        return this.variant("");
    }

    public VariantBuilder variant(String key, String value) {
        return this.variant(key + "=" + value);
    }

    public VariantBuilder variant(VariantKey key) {
        if (key != null) {
            return this.variant(key.toString());
        }
        CelibLogger.error("Tried to append variant key which was null");
        return this.variant();
    }

    public VariantBuilder variant(VariantKey... keys) {
        StringBuilder combinedKey = new StringBuilder();
        boolean first = true;
        for (VariantKey key : keys) {
            if (key == null) {
                CelibLogger.error("Tried to append variant key which was null");
            } else {
                if (first) {
                    first = false;
                } else {
                    combinedKey.append(",");
                }
                combinedKey.append(key);
            }
        }
        return this.variant(combinedKey.toString());
    }

    @Override
    protected Class<VariantBlockState> builderClass() {
        return VariantBlockState.class;
    }
}
