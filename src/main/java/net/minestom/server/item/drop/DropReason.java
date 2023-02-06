package net.minestom.server.item.drop;

import net.minestom.server.inventory.AbstractInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class DropReason {

    /**
     * Create a new {@link Inventory} drop reason from the given inventory.
     *
     * @param inventory the inventory the item dropped from
     * @return the inventory drop reason
     */
    public static Inventory fromInventory(@NotNull AbstractInventory inventory) {
        return new Inventory(inventory);
    }

    /**
     * Create a new {@link InventoryClose} drop reason.
     *
     * @return the inventory close drop reason
     */
    public static InventoryClose fromInventoryClose() {
        return new InventoryClose();
    }

    /**
     * Create a new {@link Hotbar} drop reason from the given hotbar slot.
     * The slot should be within the bounds [0, 8].
     *
     * @param slot the hotbar slot the item dropped from
     * @return the hotbar drop reason
     */
    public static Hotbar fromHotbar(@Range(from = 0, to = 8) int slot) {
        return new Hotbar(slot);
    }

    /**
     * Drop reason used when an item is dropped due the inventory closing.
     * This happens when the player has an item on their cursor and the inventory closes.
     */
    public static class InventoryClose extends DropReason {
    }

    /**
     * Drop reason used when an item is dropped directly from the player hotbar.
     * This happens when the player uses the drop key (default Q for 1 and CTRL+Q for the stack)
     * with no inventory open.
     */
    public static class Hotbar extends DropReason {

        private final int slot;

        public Hotbar(int slot) {
            this.slot = slot;
        }

        /**
         * Get the hotbar slot the item was dropped from.
         *
         * @return the slot number
         */
        public int getSlot() {
            return slot;
        }

    }

    /**
     * Drop reason use when an item is dropped from an inventory.
     * This happens when the player uses the drop key (default Q for 1 and CTRL+Q for the stack)
     * while hovering the cursor on an item inside an open inventory or alternatively when
     * the player clicks (default left/right click) outside the inventory with an item on their cursor.
     */
    public static class Inventory extends DropReason {

        private final AbstractInventory inventory;

        public Inventory(@NotNull AbstractInventory inventory) {
            this.inventory = inventory;
        }

        /**
         * Get the inventory the item was dropped from.
         *
         * @return the inventory
         */
        public @NotNull AbstractInventory getInventory() {
            return inventory;
        }

    }

}
