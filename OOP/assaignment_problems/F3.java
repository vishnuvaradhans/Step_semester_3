class ParkingSlot {
    String slotNo;
    int capacity;
    int occupiedCount;

    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
            System.out.println(
                vehicleNo + " allotted to slot " + slotNo
            );
        }
    }
}

public class F3 {

    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {

        for (ParkingSlot s : slots) {
            if (s.occupiedCount < s.capacity)
                return s;
        }

        return null;
    }

    static void safeAllot(ParkingSlot[] slots, String vehicleNo) {

        ParkingSlot slot = findAvailableSlot(slots);

        if (slot != null)
            slot.allot(vehicleNo);
        else
            System.out.println(
                "No slots available for " + vehicleNo
            );
    }

    public static void main(String[] args) {

        ParkingSlot[] available = {
            new ParkingSlot("A1", 4, 3),
            new ParkingSlot("A2", 5, 5)
        };

        safeAllot(available, "TN09AB1234");

        ParkingSlot[] full = {
            new ParkingSlot("A1", 4, 4),
            new ParkingSlot("A2", 5, 5)
        };

        safeAllot(full, "TN09AB1234");

        // The array stores references to ParkingSlot objects.
        // Passing the array does not create copies of the slot objects.
    }
}