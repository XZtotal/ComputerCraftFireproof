package me.xztotal.ccfireproof.mixin;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
    
    @Inject(at = @At("HEAD"), method = "fireImmune()Z", cancellable = true)
    private void fireImmune(CallbackInfoReturnable<Boolean> cir) {
        ItemEntity item = (ItemEntity) (Object) this;
        ItemStack stack = item.getItem();
        ResourceLocation sl = ForgeRegistries.ITEMS.getKey(stack.getItem());
        if(sl != null){
            String s = sl.getNamespace() + ":" + sl.getPath();
            if(s.equals("computercraft:turtle_advanced") || s.equals("computercraft:computer_advanced") || s.equals("computercraft:pocket_computer_advanced") || s.equals("computercraft:disk")){
                cir.setReturnValue(true);
            }
        }
    }
}
