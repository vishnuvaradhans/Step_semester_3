class CampusPin {
    public static String banner = "Campus";
    public String name = "General Pin";
    public String getDetails() { return "Details: " + name; }
}

class HostelPin extends CampusPin {
    public static String banner = "Hostel";
    public String name = "Hostel Pin";
    @Override
    public String getDetails() { return "Details: " + name; }
}

public class CampusPins {
    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        CampusPin p = new HostelPin();
        System.out.println("banner: " + p.banner + " (decided by reference type)");        // Campus
        System.out.println("name: " + p.name + " (decided by reference type)");            // General Pin
        System.out.println(p.getDetails() + " (decided by object type)");                  // Details: Hostel Pin
        // banner: static, hidden not overridden -> compile time (reference type)
        // name: field, hidden not overridden    -> compile time (reference type)
        // getDetails(): overridden instance method -> run time (dynamic dispatch on actual object)
    }
}