package net.xmlizer.permutation;

import java.math.BigInteger;
import java.util.Iterator;

/**
 * Copyright (C) 2010 Bernhard Wagner
 * 
 * This file is part of permutation.
 * 
 * permutation is free software: you can redistribute it
 * and/or modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

public class PermutationHelper<T> implements Iterator<T[]>, Iterable<T[]> {
	private final PermutationGenerator pg;
	private final T[] result;
	private final T[] symbols;

	// declare the class instance
	private final Class<T> tClass;

	@SuppressWarnings("unchecked")
	// see http://code.stephenmorley.org/articles/java-generics-type-erasure/
	public PermutationHelper(final T[] theSymbols) {
		tClass = (Class<T>) theSymbols[0].getClass();
		symbols = theSymbols;
		pg = new PermutationGenerator(symbols.length);
		result = (T[]) java.lang.reflect.Array.newInstance(tClass,
				symbols.length);
	}

	public T[] getNext() {
		final int[] indices = pg.getNext();
		for (int i = 0; i < indices.length; i++) {
			result[i] = symbols[indices[i]];
		}
		return result;
	}

	public BigInteger getCount() {
		return pg.getTotal();
	}

	@Override
	public boolean hasNext() {
		return pg.hasMore();
	}

	@Override
	public T[] next() {
		return getNext();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("remove");
	}

	@Override
	public Iterator<T[]> iterator() {
		return this;
	}

	public static void main(final String[] args) {
		if (args.length == 0) {
			System.out.println("Usage: pass a list of strings as arguments");
			System.exit(1);
		}
		final PermutationHelper<String> ph = new PermutationHelper<String>(args);
		BigInteger i = BigInteger.ONE;
		System.out.print("Total: ");
		System.out.println(ph.getCount());
		final long len = ph.getCount().toString().length();
		System.out.println();
		while (ph.hasNext()) {
			final long spacer = len - i.toString().length();
			final StringBuilder sb = new StringBuilder();
			for (int j = 0; j < spacer; ++j) {
				sb.append(' ');
			}
			System.out.print(sb);
			System.out.print(i);
			i = i.add(BigInteger.ONE);
			System.out.print(" ");
			for (final String str : ph.getNext()) {
				System.out.print(str);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}
