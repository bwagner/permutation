package net.xmlizer.permutation;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

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

public class PermutationTest {

	@Test
	public void testPermute() {
		final String[] elements = new String[] { "a", "b", "c", };
		final String[][] expected = new String[][] { elements,
				{ "a", "c", "b" }, { "b", "a", "c" }, { "b", "c", "a" },
				{ "c", "a", "b" }, { "c", "b", "a" },

		};
		final PermutationHelper<String> x = new PermutationHelper<String>(
				elements);
		int i = 0;
		for (final String[] result : x) {
			assertArrayEquals(expected[i], result);
			++i;
		}
	}

}
