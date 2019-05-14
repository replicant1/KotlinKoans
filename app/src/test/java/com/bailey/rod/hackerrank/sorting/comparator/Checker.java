package com.bailey.rod.hackerrank.sorting.comparator;

import java.util.Comparator;

class Player {
	public int score;
	public String name;
}

/**
 * In short, when sorting in ascending order, a comparator function returns:
 * -1 if a < b
 * 0 if a == b
 * 1 if a > b
 */
class Checker implements Comparator<Player> {
	@Override
	public int compare(Player a, Player b) {
		if (a.score > b.score) {
			return -1;
		} else if (a.score < b.score) {
			return 1;
		} else {
			return a.name.compareTo(b.name);
		}
	}
}
