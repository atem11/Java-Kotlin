public class SumLongHex {
	public static void main (String[] args) {
		long sum = 0;
		for (int i = 0; i < args.length; i++) {
			args[i] = args[i].toLowerCase();
			int k = 0;
			int l, r;
			char ch;
			String s;
			while (k < args[i].length()) {
				ch = args[i].charAt(k);
				if (Character.isWhitespace(ch)) {
					k++;
				} else {
					l = k;
					k++;
					while (k < args[i].length()) {
						ch = args[i].charAt(k);
						if (!Character.isWhitespace(ch)) {
							k++;
						} else {
							break;
						} 
					}
					r = k;
					s = args[i].substring(l, r);
					if (s.startsWith("0x")) {
						s = s.substring(2, s.length());
						sum += Long.parseUnsignedLong(s, 16);
					} else {
						sum += Long.parseLong(s);
					}
				}
			}
		}
		System.out.println(sum);
	}
}