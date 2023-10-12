package net.unethicalite.api.movement.pathfinder.model;

import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;
import net.unethicalite.api.entities.Players;
import net.unethicalite.api.movement.Movement;

import java.util.*;
import java.util.function.Predicate;

public enum MiningLocation {
    VARROCK_WEST_MINE(new WorldArea(3172, 3365, 12, 15, 0),
            new String[]{"3xClay", "8xTin", "3xIron", "3xSilver"}),
    VARROCK_EAST_MINE(new WorldArea(3281, 3361, 10, 9, 0),
            new String[]{"9xCopper", "6xTin", "4xIron"}),
    MINING_GUILD_BASEMENT(new WorldArea(3027, 9732, 28, 19, 0),
            new String[]{"9xCopper", "6xTin", "4xIron"}),
    MEMBERS_MINING_GUILD_BASEMENT(new WorldArea(3015, 9713, 42, 14, 0),
            new String[]{"9xCopper", "6xTin", "4xIron"}),
    ;

	private final WorldArea area;
    private final LinkedHashMap<String, Integer> rocks;

	MiningLocation(WorldArea area, String[] rocks)
	{
		this.area = area;
        this.rocks = new LinkedHashMap<>();
        for ( var rock : rocks) {
            this.rocks.put(rock.split("x")[1], Integer.parseInt(rock.split("x")[0]));
        }
	}

	public WorldArea getArea()
	{
		return area;
	}

	public static MiningLocation getNearest()
	{
		return Arrays.stream(values())
				.min(Comparator.comparingInt(x -> x.getArea().distanceTo2D(Players.getLocal().getWorldLocation())))
				.orElse(null);
	}
    public static MiningLocation getNearest(Predicate<? super MiningLocation> predicate)
    {
        return Arrays.stream(values())
                .filter(predicate)
                .min(Comparator.comparingInt(x -> Movement.calculateDistance(x.getArea())))
                .orElse(null);
    }

    public static MiningLocation getNearestPath()
    {
        return Arrays.stream(values())
                .min(Comparator.comparingInt(x -> Movement.calculateDistance(x.getArea())))
                .orElse(null);
    }
    public WorldPoint getCenter()
    {
        return new WorldPoint(this.area.getX()+(this.area.getWidth()/2), this.area.getY()+(this.area.getHeight()/2), this.getArea().getPlane());
    }

    public Integer getRockCount(String rock) {
        return rocks.get(rock);
    }

    public LinkedHashMap<String, Integer> getRocks() {
        return rocks;
    }

    public boolean hasRock(String rock) {
        return rocks.containsKey(rock);
    }
}
