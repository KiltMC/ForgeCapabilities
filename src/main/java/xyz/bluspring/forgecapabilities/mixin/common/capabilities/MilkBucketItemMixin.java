package xyz.bluspring.forgecapabilities.mixin.common.capabilities;

import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MilkBucketItem;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import xyz.bluspring.forgecapabilities.capabilities.ICapabilityProvider;
import xyz.bluspring.forgecapabilities.capabilities.impl.FluidBucketWrapperWithCapability;
import xyz.bluspring.forgecapabilities.extensions.capabilities.ItemCapabilityExtension;

@Mixin(MilkBucketItem.class)
public abstract class MilkBucketItemMixin implements ItemCapabilityExtension {
	@Override
	public ICapabilityProvider initCapabilities(ItemStack type, @Nullable CompoundTag tag) {
		return new FluidBucketWrapperWithCapability(ContainerItemContext.withConstant(type));
	}
}
