package com.debanshu777.daoImpl;

public class Utility {
	
	public static int languageId(String var) {
		switch(var) {
		case "Italian":
			return 2;
		case "Japanese":
			return 3;
		case "Mandarin":
			return 4;
		case "French":
			return 5;
		case "German":
			return 6;
		case "Mongolian":
			return 7;
		default:
			return 1;
		}
	}
	public static String getlanguage(byte n) {
		switch(n) {
		case 2:
			return "Italian";
		case 3:
			return "Japanese";
		case 4:
			return "Mandarin";
		case 5:
			return "French";
		case 6:
			return "German";
		case 7:
			return "Mongolian";
		default:
			return "English";
		}
	}

}
