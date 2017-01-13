package org.essembeh.rtfm.model.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.commons.collections.CollectionUtils;
import org.eclipse.emf.common.util.EMap;

public class EmfModelUtils {

	@SuppressWarnings("unchecked")
	public static <T> void collectionUpdate(Collection<T> mainCollection, Collection<T> newItems) {
		Collection<T> itemsToRemove = CollectionUtils.subtract(mainCollection, newItems);
		Collection<T> itemsToAdd = CollectionUtils.subtract(newItems, mainCollection);
		if (!itemsToRemove.isEmpty()) {
			mainCollection.removeAll(itemsToRemove);
		}
		if (!itemsToAdd.isEmpty()) {
			mainCollection.addAll(itemsToAdd);
		}
	}

	public static <K, V> void mapKeep(EMap<K, V> map, Set<K> keepKeys) {
		List<java.util.Map.Entry<K, V>> toRemove = new ArrayList<>();
		for (Entry<K, V> e : map.entrySet()) {
			if (!keepKeys.contains(e.getKey())) {
				toRemove.add(e);
			}
		}
		map.removeAll(toRemove);
	}

	public static <T> T configure(T in, Consumer<T> config) {
		config.accept(in);
		return in;
	}
}
