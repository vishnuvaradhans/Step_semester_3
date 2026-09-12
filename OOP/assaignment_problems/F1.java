class AccessChecker {

    static String classifyAccess(String fieldModifier, String accessorContext) {

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

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {

        String[] modifiers = {
            "private", "default", "protected", "public"
        };

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < modifiers.length; i++) {

            int allowed = 0;
            int denied = 0;

            for (String[] attempt : attempts) {

                if (attempt[0].equals(modifiers[i])) {

                    if (classifyAccess(
                            attempt[0],
                            attempt[1]).equals("ALLOWED"))
                        allowed++;
                    else
                        denied++;
                }
            }

            if (i > 0)
                result.append(" | ");

            result.append(modifiers[i])
                  .append(": ")
                  .append(allowed)
                  .append(" allowed / ")
                  .append(denied)
                  .append(" denied");
        }

        return result.toString();
    }
}

class LibraryMember {

    private String membershipId;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMember(String membershipId,
                         String branchCode,
                         double finesOwed,
                         String displayName) {

        if (membershipId == null ||
            membershipId.trim().isEmpty() ||
            membershipId.trim().length() < 4) {

            throw new IllegalArgumentException(
                "Invalid membership ID"
            );
        }

        this.membershipId = membershipId.trim();
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}

public class F1 {

    public static void main(String[] args) {

        System.out.println(
            AccessChecker.classifyAccess(
                "private",
                "SAME_CLASS"
            )
        );

        System.out.println(
            AccessChecker.classifyAccess(
                "protected",
                "DIFFERENT_PACKAGE"
            )
        );

        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
            AccessChecker.summarizeByModifier(attempts)
        );
    }
}