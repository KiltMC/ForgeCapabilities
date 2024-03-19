package xyz.bluspring.forgecapabilities.mixin.common.capabilities;

import io.github.fabricators_of_create.porting_lib.extensions.extensions.ItemStackExtensions;
import io.github.fabricators_of_create.porting_lib.util.LazyOptional;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.bluspring.forgecapabilities.capabilities.*;
import xyz.bluspring.forgecapabilities.extensions.capabilities.CapabilityProviderExtension;
import xyz.bluspring.forgecapabilities.extensions.capabilities.InitializableCapabilityExtension;
import xyz.bluspring.forgecapabilities.extensions.capabilities.ItemStackCapabilityProviderImpl;

import java.util.function.Supplier;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements CapabilityProviderExtension, ItemStackCapabilityProviderImpl, ItemStackExtensions, InitializableCapabilityExtension<ItemStack> {
	@Shadow
	public abstract Item getItem();

	@Unique private CompoundTag capNBT;

	@Unique private final CapabilityProviderWorkaround<ItemStack> workaround = new CapabilityProviderWorkaround<>(ItemStack.class, (ItemStack) (Object) this);

	@Override
	public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		return workaround.getCapability(cap, side);
	}

	@Override
	public @Nullable CapabilityDispatcher getCapabilities() {
		return workaround.invokeGetCapabilities();
	}

	@Override
	public CapabilityProviderWorkaround<ItemStack> port_lib$getWorkaround() {
		return workaround;
	}

	@Override
	public boolean areCapsCompatible(CapabilityProvider<ItemStack> other) {
		return workaround.areCapsCompatible(other);
	}

	@Override
	public boolean areCapsCompatible(@Nullable CapabilityDispatcher other) {
		return workaround.areCapsCompatible(other);
	}

	@Override
	public void invalidateCaps() {
		workaround.invalidateCaps();
	}

	@Override
	public void reviveCaps() {
		workaround.reviveCaps();
	}

	@Override
	public void gatherCapabilities(@Nullable Supplier<ICapabilityProvider> parent) {
		workaround.invokeGatherCapabilities(parent);
	}

	@Inject(method = "<init>(Lnet/minecraft/world/level/ItemLike;I)V", at = @At("TAIL"))
	private void port_lib$initCapabilities(ItemLike item, int count, CallbackInfo ci) {
		this.initCapabilities();
	}

	@Inject(method = "<init>(Lnet/minecraft/nbt/CompoundTag;)V", at = @At("TAIL"))
	private void port_lib$loadCapabilities(CompoundTag tag, CallbackInfo ci) {
		this.capNBT = tag.contains("ForgeCaps") ? tag.getCompound("ForgeCaps") : null;
		this.initCapabilities();
	}

	@Override
	public void initCapabilities() {
		this.gatherCapabilities(() -> {
			var item = this.getItem();

			if (item == null)
				return null;

			return item.initCapabilities((ItemStack) (Object) this, this.capNBT);
		});
		if (this.capNBT != null)
			this.deserializeCaps(this.capNBT);
	}
}
