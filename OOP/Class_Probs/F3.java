public class F3 {
    static String validateFileExtension(String filename) {
        int position = filename.lastIndexOf('.');

        if (position == -1)
            return "Rejected - invalid file type";

        String extension = filename.substring(position + 1);

        if (extension.equalsIgnoreCase("pdf") ||
            extension.equalsIgnoreCase("docx") ||
            extension.equalsIgnoreCase("zip"))
            return "Accepted";

        return "Rejected - invalid file type";
    }

    public static void main(String[] args) {
        System.out.println(
            validateFileExtension("Assignment1.PDF")
        );
    }
}