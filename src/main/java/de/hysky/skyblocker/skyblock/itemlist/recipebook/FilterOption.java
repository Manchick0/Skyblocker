package de.hysky.skyblocker.skyblock.itemlist.recipebook;

import net.minecraft.util.Identifier;

import java.util.function.Predicate;
import java.util.function.Supplier;

public enum FilterOption implements Supplier<Identifier>, Predicate<String> {

	ALL(query -> true, Identifier.of("skyblocker", "textures/gui/filter/all.png")),
	ENTITIES(query -> query.endsWith("(monster)") || query.endsWith("(miniboss)") || query.endsWith("(boss)")
			|| query.endsWith("(animal)") || query.endsWith("(pest)") || query.endsWith("(sea creature)"),
			Identifier.of("skyblocker", "textures/gui/filter/entities.png")),
	NPCS(query -> query.endsWith("(npc)") || query.endsWith("(rift npc)"), Identifier.of("skyblocker", "textures/gui/filter/npcs.png")),
	MAYORS(query -> query.endsWith("(mayor)") || query.endsWith("(retired mayor)"), Identifier.of("skyblocker", "textures/gui/filter/mayors.png")),

	// Basically a negation on everything else.
	ITEMS(query -> !ENTITIES.test(query) && !NPCS.test(query) && !MAYORS.test(query),
			Identifier.of("skyblocker", "textures/gui/filter/items.png"));

	final Predicate<String> matchingPredicate;
	final Identifier texture;

	FilterOption(Predicate<String> matchingPredicate, Identifier texture) {
		this.matchingPredicate = matchingPredicate;
		this.texture = texture;
	}

	public boolean test(String query) {
		return matchingPredicate.test(query);
	}

	@Override
	public Identifier get() {
		return texture;
	}
}
