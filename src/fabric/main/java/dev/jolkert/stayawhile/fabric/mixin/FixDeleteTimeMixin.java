package dev.jolkert.stayawhile.fabric.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.jolkert.stayawhile.DropType;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemEntity.class)
public class FixDeleteTimeMixin
{
	@Shadow
	private int age;

	@Definition(id = "age", field = "Lnet/minecraft/world/entity/item/ItemEntity;age:I")
	@Expression("this.age >= 6000")
	@ModifyExpressionValue(method = "tick", at = @At("MIXINEXTRAS:EXPRESSION"))
	boolean fixDeathTimeCheck(boolean original)
	{
		ItemEntity self = (ItemEntity) (Object) this;
		int maxAge = (DropType.from(self)).maxAge(self.level());

		return maxAge >= 0 && this.age >= maxAge;
	}
}
