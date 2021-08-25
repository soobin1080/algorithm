package deliveryhero_210619;

import java.util.HashMap;

public class task1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String S = "John Doe; Peter Benjamin Parker; Mary Jane Watson-Parker; John Elvis Doe; John Evan Doe; Jane Doe; Peter Brian Parker";
		String C = "example";
		String answer = "";
		String[] string = S.split("; ");
		HashMap<String, Integer> names = new HashMap<String, Integer>();
		for (int i = 0; i < string.length; i++) {
			String[] name = string[i].toLowerCase().split(" ");
			String firstName = name[0];
			String lastName = name[name.length - 1];
			lastName = lastName.replaceAll("-", "");
			if (lastName.length() > 7)
				lastName = lastName.substring(0, 8);

			String newName = firstName+" "+lastName;
			String originName=string[i];
			System.out.println(newName);
			String email = "";
			if (names.get(newName) == null) {
				names.put(newName, 1);
				if (i == string.length - 1)
					email = originName + " <" + firstName + "." + lastName + "@" + C + ".com>";
				else
					email = originName + " <" + firstName + "." + lastName + "@" + C + ".com>; ";
			} else {
				int value = names.get(newName) + 1;
				names.put(newName, value);
				if (i == string.length - 1)
					email = originName + " <" + firstName + "." + lastName +value+ "@" + C + ".com>";
				else
					email = originName + " <" + firstName + "." + lastName + value + "@" + C + ".com>; ";
			}
			answer+=email;
		}
		System.out.println(answer);
	}

	public String solution(String S, String C) {
		String answer = "";

		return answer;
	}
}
