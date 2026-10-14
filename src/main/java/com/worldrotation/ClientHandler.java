package com.worldrotation;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class ClientHandler {
    /** World rotation on/off (on by default, so the world is rotated right from launch). */
    public static boolean enabled = true;
    /** false = world turned 90 degrees to the left, true = to the right. */
    public static boolean flipped = false;

    private final KeyBinding toggleKey =
        new KeyBinding("key.worldrotation.toggle", Keyboard.KEY_X, "key.categories.worldrotation");
    private final KeyBinding flipKey =
        new KeyBinding("key.worldrotation.flip", Keyboard.KEY_Z, "key.categories.worldrotation");

    public ClientHandler() {
        ClientRegistry.registerKeyBinding(toggleKey);
        ClientRegistry.registerKeyBinding(flipKey);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        while (toggleKey.isPressed()) {
            enabled = !enabled;
        }
        while (flipKey.isPressed()) {
            flipped = !flipped;
        }
    }

    /** Rolls the camera by 90 degrees: the whole 3D world looks turned, gravity is untouched. */
    @SubscribeEvent
    public void onCameraSetup(EntityViewRenderEvent.CameraSetup event) {
        if (enabled) {
            event.setRoll(event.getRoll() + (flipped ? -90.0F : 90.0F));
        }
    }

    /** Installs the mouse helper that matches the mouse to the rotated picture. */
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        Minecraft mc = Minecraft.getMinecraft();
        if (!(mc.mouseHelper instanceof RotatedMouseHelper)) {
            mc.mouseHelper = new RotatedMouseHelper();
        }
    }
}
