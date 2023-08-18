package com.commons.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GenericBuilder<T> {
	private final Supplier<T> supplier;
	private List<Consumer<T>> list;

	private GenericBuilder(Supplier<T> supplier) {
		list = new ArrayList<>();
		this.supplier = supplier;
	}

	public static <T> GenericBuilder<T> of(Supplier<T> supplier) {
		return new GenericBuilder<T>(supplier);
	}

	public <U> GenericBuilder<T> with(BiConsumer<T, U> biConsumer, U value) {
		Consumer<T> consumer = instance -> biConsumer.accept(instance, value);
		list.add(consumer);
		return this;
	}

	public T build() {
		T value = supplier.get();
		list.forEach(modifier -> modifier.accept(value));
		list.clear();
		return value;
	}
}
