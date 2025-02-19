#include <iostream>
#include <string>
#include <fstream>
using namespace std;

int dis_choice;

// Class Bill For Total Bill.
class bill {
public:
    float total_bill;

    // Initialization Using Constructors.
    bill() {
        total_bill = 0;
    }
};

// Class Travel Inheriting Bill Class.
class travel : public bill {
public:
    int d_city, a_city, mode;
    float travel_bill, return_travel_bill = 0;

    // Functions in Travel Class.
    void place();
    void mean();
    void travel_invoice();
    void return_invoice();
};

// Class Hotel Inheriting Travel Class.
class hotel : public travel {
public:
    float hotel_bill = 0;

    // Functions in Hotel Class.
    void hotel_invoice();
    void display(string user);
};

// Travel Class Place Function.
void travel::place() {
    int p;
    while (true) {
        cout << "\nDestinations: \n1)Delhi\n2)Mumbai\n3)Chennai";
        cout << "\n\nEnter The Choice of City (Departure): ";
        cin >> d_city;
        cout << "\nEnter The Choice of city (Arrival): ";
        cin >> a_city;

        // Handling Exception Using Try-Catch Block.
        try {
            if (a_city == d_city) {
                p = 1;
                throw(p);
            }
        } catch (int p) {
            cout << "\n\t\t !!!exception occurred!!!" << endl
                 << "Arrival and departure city cannot be the same, you will get a round trip option later." << endl;
            continue; // Loop again for valid city input
        }
        break; // Exit loop if cities are valid
    }
}

// Travel Class mean Function.
void travel::mean() {
    cout << "\nMode Of Transport:\n1)Airplane\n2)Train\n3)Bus";
    cout << "\nEnter Choice: ";
    cin >> mode;

    // Setting travel bill based on departure city and mode of transport
    if (a_city == 1) {
        if (mode == 1) { cout << "\nCost = 5000/-"; travel_bill = 5000; }
        else if (mode == 2) { cout << "\nCost = 4000/-"; travel_bill = 4000; }
        else if (mode == 3) { cout << "\nCost = 3000/-"; travel_bill = 3000; }
    }
    else if (a_city == 3) {
        if (mode == 1) { cout << "\nCost = 4000/-"; travel_bill = 4000; }
        else if (mode == 2) { cout << "\nCost = 2500/-"; travel_bill = 2500; }
        else if (mode == 3) { cout << "\nCost = 2000/-"; travel_bill = 2000; }
    }
    else if (a_city == 2) {
        if (mode == 1) { cout << "\nCost = 6000/-"; travel_bill = 6000; }
        else if (mode == 2) { cout << "\nCost = 5500/-"; travel_bill = 5500; }
        else if (mode == 3) { cout << "\nCost = 2500/-"; travel_bill = 2500; }
    }
}

// Travel Class travel_invoice Function.
void travel::travel_invoice() {
    while (true) {
        cout << "\n\nAre you a: \n1)Student\n2)Business\n3)Tourist\n";
        cin >> dis_choice;

        if (dis_choice == 1) {
            cout << "\nYou are eligible for 15% Student's Discount.";
            travel_bill -= (travel_bill * 0.15);
            break;
        } else if (dis_choice == 2) {
            cout << "\nYou are eligible for 20% Co-operate Discount.";
            travel_bill -= (travel_bill * 0.2);
            break;
        } else if (dis_choice == 3) {
            cout << "\nSorry! Being Tourist, You are not eligible for any Discount.";
            break;
        } else {
            cout << "Wrong Choice. Try again.\n";
        }
    }
    total_bill += travel_bill;
}

// Travel Class return_invoice Function.
void travel::return_invoice() {
    if (d_city == 1) {
        if (mode == 1) { cout << "\nCost = 3500/-"; return_travel_bill = 3500; }
        else if (mode == 2) { cout << "\nCost = 2800/-"; return_travel_bill = 2800; }
        else if (mode == 3) { cout << "\nCost = 2100/-"; return_travel_bill = 2100; }
    } else if (d_city == 3) {
        if (mode == 1) { cout << "\nCost = 2800/-"; return_travel_bill = 2800; }
        else if (mode == 2) { cout << "\nCost = 1750/-"; return_travel_bill = 1750; }
        else if (mode == 3) { cout << "\nCost = 1400/-"; return_travel_bill = 1400; }
    } else if (d_city == 2) {
        if (mode == 1) { cout << "\nCost = 4200/-"; return_travel_bill = 4200; }
        else if (mode == 2) { cout << "\nCost = 3850/-"; return_travel_bill = 3850; }
        else if (mode == 3) { cout << "\nCost = 1750/-"; return_travel_bill = 1750; }
    }
    total_bill += return_travel_bill;
}

// Travel Class hotel_invoice Function.
void hotel::hotel_invoice() {
    int hotel_choice, stay;
    float base_price;

    cout << "\n\nHotel in Your Arrival City: ";
    if (a_city == 1) {
        cout << "\n1)JW Marriot\n2)Le Meridian\n3)Hyatt";
    } else if (a_city == 2) {
        cout << "\n1)Taj Hotel\n2)Oberoy\n3)Hotel Diamond";
    } else if (a_city == 3) {
        cout << "\n1)Double Tree Hilton\n2)Keys\n3)Regency";
    }

    cout << "\nEnter Hotel Choice: ";
    cin >> hotel_choice;
    cout << "\nEnter the number of days: ";
    cin >> stay;

    switch (hotel_choice) {
        case 1: base_price = (a_city == 1) ? 1500 : (a_city == 2) ? 2500 : 1850; break;
        case 2: base_price = (a_city == 1) ? 1250 : (a_city == 2) ? 2000 : 1500; break;
        case 3: base_price = (a_city == 1) ? 1000 : (a_city == 2) ? 1800 : 1300; break;
        default: cout << "Wrong Choice"; return;
    }

    cout << "\n1-Night Stay Cost: " << base_price;
    hotel_bill = base_price * stay;

    if (dis_choice == 1) {
        cout << "\nYou are eligible for 10% Student's Discount.";
        hotel_bill -= (hotel_bill * 0.1);
    } else if (dis_choice == 2) {
        cout << "\nYou are eligible for 15% Co-operate Discount.";
        hotel_bill -= (hotel_bill * 0.15);
    } else if (dis_choice == 3) {
        cout << "\nSorry! Being Tourist, You are not eligible for any Discount.";
    }

    total_bill += hotel_bill;
}

void hotel::display(string user) {
    fstream f;
    string cities_arr[3] = {"DELHI", "MUMBAI", "CHENNAI"};
    string mode_arr[3] = {"Airplane", "Train", "Bus"};

    cout << "\n\n\n\t\t\t\t\tINVOICE\n";
    cout << "\tYOUR TRAVEL BILL FROM " << cities_arr[d_city - 1] << " TO " << cities_arr[a_city - 1] 
         << " IS :Rs " << travel_bill;
    cout << "\n\tYOUR OPTED MODE OF TRANSPORTATION IS : " << mode_arr[mode - 1];
    cout << "\n\tYOUR RETURN TRAVEL BILL IS :Rs " << return_travel_bill;
    cout << "\n\tYOUR HOTEL BILL IS :Rs " << hotel_bill;
    cout << "\n\n\tGRAND TOTAL :Rs " << total_bill;

    // Opening File, and Writing Data into it.
    f.open("TRIP PLANNER.txt", ios::app | ios::out);
    f << "User name: " << user << endl
      << "Departure city: " << cities_arr[d_city - 1] << endl
      << "Arrival city: " << cities_arr[a_city - 1] << endl
      << "Mode of transport: " << mode_arr[mode - 1] << endl
      << "Travel bill:Rs " << travel_bill << endl
      << "Return bill:Rs " << return_travel_bill << endl
      << "Hotel bill:Rs " << hotel_bill << endl
      << "Total bill:Rs " << total_bill << endl
      << " " << endl;
    f.close();
}

int main() {
    // Menu and UI.
    cout << endl
         << "\t\t*************************" << endl
         << "\t\t| TRIP PLANNER SYSTEM |" << endl
         << "\t\t*************************" << endl;

    int choice, ch;
    string user;
    cout << endl << "Enter your name: ";
    cin >> user;

    cout << "\nDo you want to generate a bill for your journey?";
    cout << "\nEnter: \n1)Yes\n2)No\n";
    cin >> choice;

    if (choice == 1) {
        hotel t;
        t.place();
        t.mean();
        t.travel_invoice();
        cout << "\n\nDo You Want A Round Trip?\n1)Yes\nAny Other Key for No\n";
        cin >> ch;
        if (ch == 1) {
            cout << "\nYou will get 30% discount on Return Tickets.";
            t.return_invoice();
        }

        while (true) {
            cout << "\n\nDo You Want to Generate a bill for a Hotel?\n1)yes\n2)No\n";
            cin >> ch;
            if (ch == 1) {
                t.hotel_invoice();
                t.display(user);
                cout << "\n\nThank You for Choosing Our Services.";
                return 0;
            } else if (ch == 2) {
                t.display(user);
                cout << "\n\nThank You for Choosing Our Services.";
                return 0;
            } else {
                cout << "\nWrong Option. Try again.\n";
            }
        }
    } else if (choice == 2) {
        cout << "\n\nThank You for Choosing Our Services.";
        return 0;
    } else {
        cout << "\nWrong Option.";
        return 0;
    }
}
