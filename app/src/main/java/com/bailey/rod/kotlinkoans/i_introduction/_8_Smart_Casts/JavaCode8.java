package com.bailey.rod.kotlinkoans.i_introduction._8_Smart_Casts;

import com.bailey.rod.kotlinkkoans.i_introduction._8_Smart_Casts.Expr;
import com.bailey.rod.kotlinkkoans.i_introduction._8_Smart_Casts.Num;
import com.bailey.rod.kotlinkkoans.i_introduction._8_Smart_Casts.Sum;
import com.bailey.rod.kotlinkoans.util.JavaCode;


public class JavaCode8 extends JavaCode {
	public int eval(Expr expr) {
		if (expr instanceof Num) {
			return ((Num) expr).getValue();
		}
		if (expr instanceof Sum) {
			Sum sum = (Sum) expr;
			return eval(sum.getLeft()) + eval(sum.getRight());
		}
		throw new IllegalArgumentException("Unknown expression");
	}
}
