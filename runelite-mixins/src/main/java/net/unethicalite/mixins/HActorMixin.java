package net.unethicalite.mixins;

import net.unethicalite.api.util.Randomizer;
import net.runelite.api.Point;
import net.runelite.api.mixins.Inject;
import net.runelite.api.mixins.Mixin;
import net.runelite.api.mixins.Shadow;
import net.runelite.rs.api.RSActor;
import net.runelite.rs.api.RSClient;

@Mixin(RSActor.class)
public abstract class HActorMixin implements RSActor
{

	@Shadow("client")
	private static RSClient client;

	@Inject
	public Point getClickPoint()
	{
		return Randomizer.getRandomPointIn(getConvexHull().getBounds());
	}
}
