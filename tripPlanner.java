import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

// Base Class Bill
class Bill {
    protected float totalBill;

    public Bill() {
        totalBill = 0;
    }
}

// Travel Class inheriting Bill
class Travel extends Bill {
    protected int dCity, aCity, mode;
    protected int disChoice;  // discount category (Student/Business/Tourist)
    protected float travelBill, returnTravelBill = 0;

    Scanner sc = new Scanner(System.in);

    // Select Departure and Arrival Cities
    public void place() {
        while (true) {
            System.out.println("\nDestinations:\n1)Delhi\n2)Mumbai\n3)Chennai");
            System.out.print("Enter Departure City: ");
            dCity = sc.nextInt();
            System.out.print("Enter Arrival City: ");
            aCity = sc.nextInt();

            try {
                if (aCity == dCity) {
                    throw new Exception("Arrival and Departure cannot be the same!");
                }
            } catch (Exception e) {
                System.out.println("\n\t\t!!! Exception Occurred !!!");
                System.out.println(e.getMessage() + " You will get a round trip option later.");
                continue;
            }
            break;
        }
    }

    // Select Mode of Transport
    public void mean() {
        System.out.println("\nMode Of Transport:\n1)Airplane\n2)Train\n3)Bus");
        System.out.print("Enter Choice: ");
        mode = sc.nextInt();

        if (aCity == 1) {
            travelBill = (mode == 1) ? 5000 : (mode == 2) ? 4000 : 3000;
        } else if (aCity == 2) {
            travelBill = (mode == 1) ? 6000 : (mode == 2) ? 5500 : 2500;
        } else if (aCity == 3) {
            travelBill = (mode == 1) ? 4000 : (mode == 2) ? 2500 : 2000;
        }
        System.out.println("\nCost = " + travelBill + "/-");
    }

    // Apply Discount on Travel
    public void travelInvoice() {
        while (true) {
            System.out.println("\n\nAre you a: \n1)Student (15% off)\n2)Business (20% off)\n3)Tourist (No discount)");
            disChoice = sc.nextInt();

            if (disChoice == 1) {
                System.out.println("\nYou are eligible for 15% Student Discount.");
                travelBill -= (travelBill * 0.15);
                break;
            } else if (disChoice == 2) {
                System.out.println("\nYou are eligible for 20% Corporate Discount.");
                travelBill -= (travelBill * 0.20);
                break;
            } else if (disChoice == 3) {
                System.out.println("\nSorry! No discount available for Tourists.");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        totalBill += travelBill;
    }

    // Return Trip Invoice
    public void returnInvoice() {
        if (dCity == 1) {
            returnTravelBill = (mode == 1) ? 3500 : (mode == 2) ? 2800 : 2100;
        } else if (dCity == 2) {
            returnTravelBill = (mode == 1) ? 4200 : (mode == 2) ? 3850 : 1750;
        } else if (dCity == 3) {
            returnTravelBill = (mode == 1) ? 2800 : (mode == 2) ? 1750 : 1400;
        }

        // ✅ Apply 30% round-trip discount
        System.out.println("\nOriginal Return Ticket Cost: " + returnTravelBill);
        returnTravelBill -= (returnTravelBill * 0.30);
        System.out.println("After 30% Round Trip Discount: " + returnTravelBill);

        totalBill += returnTravelBill;
    }
}

// Hotel Class inheriting Travel
class Hotel extends Travel {
    private float hotelBill = 0;

    public void hotelInvoice() {
        int hotelChoice, stay;
        float basePrice = 0;

        System.out.println("\nHotels in Your Arrival City:");
        if (aCity == 1) {
            System.out.println("1) JW Marriott (1500)\n2) Le Meridian (1250)\n3) Hyatt (1000)");
        } else if (aCity == 2) {
            System.out.println("1) Taj Hotel (2500)\n2) Oberoy (2000)\n3) Hotel Diamond (1800)");
        } else if (aCity == 3) {
            System.out.println("1) Double Tree Hilton (1850)\n2) Keys (1500)\n3) Regency (1300)");
        }

        System.out.print("Enter Hotel Choice: ");
        hotelChoice = sc.nextInt();
        System.out.print("Enter Number of Days: ");
        stay = sc.nextInt();

        switch (hotelChoice) {
            case 1 -> basePrice = (aCity == 1) ? 1500 : (aCity == 2) ? 2500 : 1850;
            case 2 -> basePrice = (aCity == 1) ? 1250 : (aCity == 2) ? 2000 : 1500;
            case 3 -> basePrice = (aCity == 1) ? 1000 : (aCity == 2) ? 1800 : 1300;
            default -> {
                System.out.println("Wrong Choice!");
                return;
            }
        }

        hotelBill = basePrice * stay;
        System.out.println("\nBase Hotel Bill: " + hotelBill);

        if (disChoice == 1) {
            System.out.println("10% Student Discount Applied.");
            hotelBill -= (hotelBill * 0.10);
        } else if (disChoice == 2) {
            System.out.println("15% Corporate Discount Applied.");
            hotelBill -= (hotelBill * 0.15);
        }

        totalBill += hotelBill;
    }

    // Final Invoice
    public void display(String user) {
        String[] citiesArr = {"DELHI", "MUMBAI", "CHENNAI"};
        String[] modeArr = {"Airplane", "Train", "Bus"};

        System.out.println("\n\n\n\t\t*************** INVOICE ***************");
        System.out.println("User: " + user);
        System.out.println("Travel From " + citiesArr[dCity - 1] + " To " + citiesArr[aCity - 1] + " : Rs " + travelBill);
        System.out.println("Mode of Transport: " + modeArr[mode - 1]);
        System.out.println("Return Trip Cost: Rs " + returnTravelBill);
        System.out.println("Hotel Bill: Rs " + hotelBill);
        System.out.println("--------------------------------------");
        System.out.println("Grand Total: Rs " + totalBill);
        System.out.println("****************************************");

        // Save to file
        try (FileWriter fw = new FileWriter("TRIP_PLANNER.txt", true)) {
            fw.write("User: " + user + "\n");
            fw.write("From: " + citiesArr[dCity - 1] + " To: " + citiesArr[aCity - 1] + "\n");
            fw.write("Mode: " + modeArr[mode - 1] + "\n");
            fw.write("Travel Bill: Rs " + travelBill + "\n");
            fw.write("Return Bill: Rs " + returnTravelBill + "\n");
            fw.write("Hotel Bill: Rs " + hotelBill + "\n");
            fw.write("Total: Rs " + totalBill + "\n\n");
        } catch (IOException e) {
            System.out.println("Error Writing File: " + e.getMessage());
        }
    }
}

// Main Program
public class TripPlanner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n\t\t*************************");
        System.out.println("\t\t| TRIP PLANNER SYSTEM |");
        System.out.println("\t\t*************************");

        while (true) {
            System.out.print("\nEnter your name: ");
            String user = sc.nextLine();

            System.out.println("\nDo you want to generate a bill for your journey?");
            System.out.println("1) Yes\n2) No");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            if (choice == 1) {
                Hotel t = new Hotel();
                t.place();
                t.mean();
                t.travelInvoice();

                System.out.println("\nDo You Want A Round Trip? (1=Yes / Any Other Key=No)");
                int ch = sc.nextInt();
                if (ch == 1) {
                    t.returnInvoice();
                }

                System.out.println("\nDo You Want to Book a Hotel? (1=Yes / 2=No)");
                ch = sc.nextInt();
                if (ch == 1) {
                    t.hotelInvoice();
                }

                t.display(user);
                System.out.println("\nThank You for Choosing Our Services.");

            } else {
                System.out.println("\nThank You for Visiting!");
            }

            System.out.println("\nDo you want to book another trip? (1=Yes / Any Other Key=Exit)");
            try {
                int again = sc.nextInt();
                if (again != 1) break;
                sc.nextLine(); // flush
            } catch (InputMismatchException e) {
                break;
            }
        }
        sc.close();
    }
}
