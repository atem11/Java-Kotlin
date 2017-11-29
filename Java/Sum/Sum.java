public class Sum{
	public static void main(String[] args) {
		int sum = 0;
		for (int i = 0; i < args.length; i++) {
			String[] test = args[i].split("\\p{javaWhitespace}");
			for (int j = 0; j < test.length; j++) {
				if (!test[j].isEmpty()) {
					sum += Integer.parseInt(test[j]);
				}
			}
		}
		System.out.println(sum);                         
	}
}                                   