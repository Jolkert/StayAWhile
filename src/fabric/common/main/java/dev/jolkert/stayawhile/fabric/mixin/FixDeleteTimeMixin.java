package dev.jolkert.stayawhile.fabric.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.jolkert.stayawhile.DropType;
import dev.jolkert.stayawhile.StayAWhile;
import net.minecraft.server.level.ServerLevel;
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
		// in theory, the call to `this.level.isClientSide()` before the age check expression *should* make the cast
		// here perfectly safe. unsure if thats true or not? but at least it should be -morgan 2026-08-05
		int maxAge = StayAWhile.maxItemAge(DropType.from(self), (ServerLevel) self.level());

		return maxAge >= 0 && this.age >= maxAge;
	}
}
