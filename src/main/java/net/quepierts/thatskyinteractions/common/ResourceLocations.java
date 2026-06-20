package net.quepierts.thatskyinteractions.common;

import net.minecraft.resources.Identifier;
import net.quepierts.thatskyinteractions.ThatSkyInteractions;

public final class ResourceLocations {
    public static final String MOD_ID = ThatSkyInteractions.MOD_ID;

    public static Identifier of(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    public static Identifier mc(String path) {
        return Identifier.withDefaultNamespace(path);
    }
}
