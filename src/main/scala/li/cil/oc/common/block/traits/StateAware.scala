package li.cil.oc.common.block.traits

import li.cil.oc.api.internal
import net.minecraft.block.Block
import net.minecraft.world.World

trait StateAware extends Block {
  override def hasComparatorInputOverride = true

  override def getComparatorInputOverride(world: World, x: Int, y: Int, z: Int, side: Int) =
    world.getTileEntity(x, y, z) match {
      case stateful: internal.StateAware =>
        if (stateful.getCurrentState.contains(internal.StateAware.State.IsWorking)) 15
        else if (stateful.getCurrentState.contains(internal.StateAware.State.CanWork)) 10
        else 0
      case _ => 0
    }
}
