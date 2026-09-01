public class F5 {

    static String normalizeReference(String raw) {
        String ref = raw.trim();

        if (ref.length() < 3)
            return ref;

        return ref.substring(0, 3).toUpperCase()
                + ref.substring(3);
    }

    static String validateAndFormat(String reference) {

        if (reference.length() != 14)
            return "Invalid: wrong length";

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i)))
                return "Invalid: bank code must be 3 letters";
        }

        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i)))
                return "Invalid: body must contain only digits";
        }

        String bank = reference.substring(0, 3);

        String date = reference.substring(3, 5) + "/" +
                      reference.substring(5, 7) + "/" +
                      reference.substring(7, 9);

        String sequence = reference.substring(9, 14);

        StringBuilder result = new StringBuilder();

        result.append("[")
              .append(bank)
              .append("] DATE: ")
              .append(date)
              .append(" | SEQ: ")
              .append(sequence);

        return result.toString();
    }

    public static void main(String[] args) {
        String raw = " hdf03022600042 ";

        String reference = normalizeReference(raw);

        System.out.println(validateAndFormat(reference));
    }
}