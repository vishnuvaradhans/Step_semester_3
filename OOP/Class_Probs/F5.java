import java.util.Arrays;

class DischargeSummary {

    private final String patientId;
    private final String[] medicationCodes;

    public DischargeSummary(String patientId,
                            String[] medicationCodes) {

        if (patientId == null)
            throw new IllegalArgumentException();

        if (medicationCodes == null)
            throw new IllegalArgumentException();

        for (String code : medicationCodes) {

            if (!isValidMedicationCode(code))
                throw new IllegalArgumentException(
                    "Invalid medication code"
                );
        }

        this.patientId = patientId;
        this.medicationCodes = medicationCodes.clone();
    }

    private static boolean isValidMedicationCode(String code) {

        if (code == null || code.length() != 5)
            return false;

        return code.charAt(0) == 'M' &&
               code.charAt(1) == 'E' &&
               code.charAt(2) == 'D' &&
               code.charAt(3) == '-' &&
               Character.isUpperCase(code.charAt(4));
    }

    public String getPatientId() {
        return patientId;
    }

    public String[] getMedicationCodes() {
        return medicationCodes.clone();
    }

    DischargeSummary withCorrectedMedication(
            int index,
            String newCode) {

        if (index < 0 || index >= medicationCodes.length)
            throw new IllegalArgumentException();

        if (!isValidMedicationCode(newCode))
            throw new IllegalArgumentException();

        String[] newCodes = medicationCodes.clone();

        newCodes[index] = newCode;

        return new DischargeSummary(
            patientId,
            newCodes
        );
    }
}

class CriticalCareDischargeSummary
        extends DischargeSummary {

    private final int icuDays;

    public CriticalCareDischargeSummary(
            String patientId,
            String[] medicationCodes,
            int icuDays) {

        super(patientId, medicationCodes);
        this.icuDays = icuDays;
    }

    public int getIcuDays() {
        return icuDays;
    }
}

public class F5 {

    static String systemName;

    static {
        systemName = "MediTrack Nightly Ledger";
    }

    static String processNightlyBatch(
            DischargeSummary[] summaries) {

        int processed = 0;
        int nullSkipped = 0;
        int critical = 0;
        int routine = 0;

        for (DischargeSummary summary : summaries) {

            if (summary == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (summary instanceof CriticalCareDischargeSummary)
                critical++;
            else
                routine++;
        }

        return processed + " processed | " +
               nullSkipped + " null skipped | " +
               critical + " critical-care | " +
               routine + " routine";
    }

    public static void main(String[] args) {

        DischargeSummary d =
            new DischargeSummary(
                "MT2026-0142",
                new String[]{"MED-A", "MED-B"}
            );

        String[] codes = d.getMedicationCodes();

        codes[0] = "TAMPERED";

        System.out.println(
            d.getMedicationCodes()[0]
        );

        DischargeSummary[] summaries = {

            new CriticalCareDischargeSummary(
                "MT001",
                new String[]{"MED-X"},
                4
            ),

            null,

            new DischargeSummary(
                "MT002",
                new String[]{"MED-Y"}
            )
        };

        System.out.println(
            processNightlyBatch(summaries)
        );
    }
}