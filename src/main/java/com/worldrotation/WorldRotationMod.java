package com.worldrotation;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(
    modid = WorldRotationMod.MODID,
    name = "World Rotation 90",
    version = "1.0",
    clientSideOnly = true,
    acceptedMinecraftVersions = "[1.12.2]"
)
public class WorldRotationMod {
    public static final String MODID = "worldrotation";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ClientHandler());
    }
}
