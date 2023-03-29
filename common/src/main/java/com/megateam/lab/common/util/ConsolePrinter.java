package com.megateam.lab.common.util;

import java.util.List;

public class ConsolePrinter implements Printer {
	@Override
	public void print(String printable) {
		System.out.print(printable);
	}

	@Override
	public void println(String printable) {
		System.out.println(printable);
	}

	@Override
	public void printf(String printable, Object... args) {
		System.out.printf(printable, args);
	}

	@Override
	public void printList(List<?> list)
	{
		if (list.size() == 0) {
			println("Empty list");
			return;
		}

		System.out.println("Elements: ");
		int idx = 1;

		for (Object obj : list)
		{
			println(idx++ + ")");
			println(obj.toString());
		}
	}
}
