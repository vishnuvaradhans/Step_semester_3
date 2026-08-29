class BrokenLibraryMember {
    static String name;
    static String memberId;
    static int booksIssued;

    BrokenLibraryMember(String n, String id, int books) {
        name = n;
        memberId = id;
        booksIssued = books;
    }

    // name, memberId and booksIssued should not be static
    // because each member must have their own values.
}

class LibraryMember {
    String name;
    String memberId;
    int booksIssued;

    static String libraryName = "Central Library";
    static int memberCount = 0;

    LibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;

        memberCount++;
        this.memberId = "LM-" + (1000 + memberCount);
    }

    void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    static void printTotalMembers() {
        System.out.println("Total members: " + memberCount);
    }
}

public class F4 {
    public static void main(String[] args) {

        System.out.println("Broken version:");

        BrokenLibraryMember b1 =
            new BrokenLibraryMember("Aditi", "LM-1001", 2);

        BrokenLibraryMember b2 =
            new BrokenLibraryMember("Rohan", "LM-1002", 3);

        System.out.println(b1.name);
        System.out.println(b2.name);

        System.out.println("\nFixed version:");

        LibraryMember m1 = new LibraryMember("Aditi", 2);
        LibraryMember m2 = new LibraryMember("Rohan", 3);

        m1.printMemberCard();
        m2.printMemberCard();

        LibraryMember.printTotalMembers();
    }
}