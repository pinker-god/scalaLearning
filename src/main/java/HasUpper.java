import java.util.Scanner;

/**
 * @author pinker on 2018/1/9
 */
public class HasUpper {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String name;
        for (int i = 0; i < 5; i++) {
            name = in.nextLine();
            System.out.println(name);
            boolean isUpper = exists(name, Character::isUpperCase);
            System.out.println(isUpper);
        }
    }

    public static boolean exists(String str, CharacterProperty property) {
        boolean result = false;
        char[] arr = str.toCharArray();
        for (char ch : arr) {
            if (property.hasProperty(ch)) {
                result = true;
                break;
            }
        }
        return result;
    }
}

@FunctionalInterface
interface CharacterProperty {
    boolean hasProperty(char ch);
}
