class AccessRuleEngine {

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

    static String summarizeBatch(String[][] attempts) {
        int allowed = 0;
        int denied = 0;

        for (String[] attempt : attempts) {
            String result = classifyAccess(attempt[0], attempt[1]);

            if (result.equals("ALLOWED"))
                allowed++;
            else
                denied++;
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }
}

class PatientRecord {

    private String patientId;
    String wardCode;
    protected double vitalsScore;
    public String facilityName;

    public PatientRecord(String patientId,
                         String wardCode,
                         double vitalsScore,
                         String facilityName) {

        if (patientId == null ||
            patientId.trim().isEmpty() ||
            patientId.trim().length() < 4) {

            throw new IllegalArgumentException("Invalid patient ID");
        }

        this.patientId = patientId.trim();
        this.wardCode = wardCode;
        this.vitalsScore = vitalsScore;
        this.facilityName = facilityName;
    }
}

public class F1 {
    public static void main(String[] args) {

        System.out.println(
            AccessRuleEngine.classifyAccess(
                "private", "SAME_CLASS"
            )
        );

        System.out.println(
            AccessRuleEngine.classifyAccess(
                "default", "DIFFERENT_PACKAGE"
            )
        );

        String[][] attempts = {
            {"protected", "SAME_PACKAGE"},
            {"protected", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
            AccessRuleEngine.summarizeBatch(attempts)
        );
    }
}