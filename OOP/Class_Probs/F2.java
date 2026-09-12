class AccessRuleEngine {

    static String classifyAccess(String fieldModifier,
                                 String accessorContext) {

        if (accessorContext.equals("SAME_CLASS"))
            return "ALLOWED";

        if (accessorContext.equals("SAME_PACKAGE")) {
            if (fieldModifier.equals("private"))
                return "DENIED";

            return "ALLOWED";
        }

        if (accessorContext.equals("DIFFERENT_PACKAGE")) {
            if (fieldModifier.equals("public"))
                return "ALLOWED";

            return "DENIED";
        }

        if (accessorContext.equals(
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {

            if (fieldModifier.equals("public") ||
                fieldModifier.equals("protected"))
                return "ALLOWED";

            return "DENIED";
        }

        if (accessorContext.equals(
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")) {

            if (fieldModifier.equals("public"))
                return "ALLOWED";

            return "DENIED";
        }

        return "DENIED";
    }

    static String describeContext(String accessorContext) {

        String[] words = accessorContext.toLowerCase().split("_");

        StringBuilder result = new StringBuilder();

        for (String word : words) {

            result.append(
                Character.toUpperCase(word.charAt(0))
            );

            result.append(word.substring(1));
            result.append(" ");
        }

        return result.toString().trim();
    }
}

public class F2 {
    public static void main(String[] args) {

        System.out.println(
            AccessRuleEngine.classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
            )
        );

        System.out.println(
            AccessRuleEngine.classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
            )
        );

        System.out.println(
            AccessRuleEngine.describeContext(
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
            )
        );
    }
}