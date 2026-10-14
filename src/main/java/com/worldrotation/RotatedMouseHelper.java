package com.worldrotation;

import net.minecraft.util.MouseHelper;

/** Swaps the mouse axes while the world is rotated, so the mouse moves the view the way it looks on screen. */
public class RotatedMouseHelper extends MouseHelper {
    @Override
    public void mouseXYChange() {
        super.mouseXYChange();
        if (ClientHandler.enabled) {
            int s = ClientHandler.flipped ? -1 : 1;
            int dx = this.deltaX;
            int dy = this.deltaY;
            this.deltaX = s * dy;
            this.deltaY = -s * dx;
        }
    }
}
