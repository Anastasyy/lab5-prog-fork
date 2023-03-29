package com.megateam.lab.common.data.util;

public class TicketIdGenerator {
	public static Integer generateNewId() {
		return ((int) System.currentTimeMillis());
	}
}
