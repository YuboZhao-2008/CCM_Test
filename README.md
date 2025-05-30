# Community Centre Management System
### **2A. Data Structures**

**Text File Formatting:**  
*members, events, facilities, staff*  
*“\#\#” separates for readability (will not be present in the real file)*  
members.txt \- txt file containing information about every member
```
\<num members\>  
\<id\>  
\<member type\>  
\<age\>  
\<name\>  
If member type is adult:  
\<contactPhone\>  
\<address\>  
\<billAmount\>  
\<billPaid\>  
\<num children\>  
\<child ids …\>

If member type is youth:
\<guardian id\>
```
```
3  
\#\#  
12  
youth  
8  
Sean Yang  
1  
\#\#  
13  
adult  
24  
Mansour Abdelsalam  
647-999-9999  
123 Apple Boulevard  
27138  
outstanding  
2  
50  
51  
\#\#  
14  
Adult  
99  
Yubo Zhao  
416-111-1111  
931 Box Street  
9999  
paid  
0  
\#\#  
…
```

events.txt \- txt file containing information about every event
```
\<num events\>  
\<event type\>

If fundraiser:  
\<goal amount\>

If competition:  
\<prize\>  
\<participationCost\>

\<facility id\>  
\<day month year\>  
\<startHour\>  
\<endHour\>  
\<host id or 0 if no host\>  
\<num staff supervising\>  
\<supervising staff ids…\>  
\<num members registered\>  
\<registered member ids…\>
```
```
2  
\#\#  
fundraiser  
10000  
1  
10 Jan 2026  
8  
16  
0  
2  
1  
2  
3  
12  
13  
14  
\#\#  
competition  
1000  
5  
2  
5 June 2025  
9  
18  
13  
3  
1  
2  
3  
2  
12  
14
```

facilities.txt \- txt file containing information about all facilities
```
\<num facilities\>  
\<id\>  
\<type\>  
\<room num\>  
\<max capacity\>

If sports facility:  
\<rating\>

If meeting room:  
\<size\>
```
```
2  
\#\#  
1  
meeting  
101  
20  
400.5  
\#\#  
2  
sports  
200  
30  
9.5
```

staff.txt \- txt file containing information on all staff
```
\<num staff\>  
\<id\>  
\<type\>  
\<name\>  
If full time:  
\<yearsWorked\>

If part time:  
\<hoursWorked\>  
\<hourlySalary\>  
\<maxWeeklyHours\>
```
```
2  
\#\#  
1  
fulltime  
John Doe  
12  
\#\#  
2  
parttime  
John Cena  
10  
20  
30
```

**File Organisation:**  
Project folder: CommunityCentreManager/

* data / members.txt, events.txt, facilities.txt, staff.txt  
* src / Main.java  
  * other classes

---

**Class Definitions:**  
*Schedule, TimeBlock, Facility, SportsFacility, MeetingFacility, Event, Competition, Fundraiser, Staff, FullTimeStaff, PartTimeStaff, Member, YouthMember, AdultMember, MemberManager, EventManager, StaffManager, FacilityManager, TimeManager, Main*

**Class**: Schedule  
**Purpose**: Represents a schedule  
**Fields:**

* eventSchedule: ArrayList\<Event\> // maintain sorted by time

**Methods:**

* add(Event): boolean  
* freeBlocksWithin(TimeBlock): ArrayList\<TimeBlock\>  
* freeBlocksByTime(double, double): ArrayList\<TimeBlock\> // startHour and duration  
* isBlockFree(TimeBlock): boolean  
* eventsWithin(TimeBlock): ArrayList\<Event\>  
* advanceHours(int): void  
* cancelEvent(int): boolean  
* toString(): String  
* All accessor and mutator methods

**Class:** TimeBlock  
**Purpose:** Represents a day of the year as well as a time range  
**Fields:**

* year: int  
* month: Month (enum)  
* day: int  
* startHour: double  
* endHour: double

**Methods:**

* TimeBlock(int, Month (enum), int)  
* TimeBlock(int, Month (enum), int, double)  
* TimeBlock(int, Month (enum), int, double, double) // startHour and duration  
* duration(): double  
* compareToStart(TimeBlock): double  
* compareToEnd(TimeBlock): double  
* hoursFrom(TimeBlock): double  
* isConflicting(TimeBlock): boolean  
* toString(): String  
* All accessor and mutator methods

**Class:** Facility  
**Purpose:** Represents a community facility that can be booked  
**Type:** Abstract  
**Fields:** 

* id: int  
* roomNum: int  
* maxCapacity: int  
* bookings: Schedule

**Methods:**

* Facility(int, int)  
* book(Event): boolean  
* *calcCost(TimeBlock): double*  
* toString(): String  
* All accessor mutators

**Class:** SportsFacility  
**Purpose:** represents a facility for sports  
**Type:** Child class of Facility  
**Fields:** 

* rating: double

**Methods:**

* SportsFacility(int, int, double)  
* calcEventCost(TimeBlock): double  
* toString(): String  
* All accessor mutators

**Class:** MeetingFacility  
**Purpose:** represents a room for meetings or seminars  
**Type:** Child class of Facility  
**Fields:** 

* size: double

**Methods:**

* MeetingFacility(int, int, double)  
* calcEventCost(TimeBlock): double  
* toString(): String  
* All accessor mutators

**Class:** Event  
**Purpose:** Represents a scheduled event  
**Type:** Abstract  
**Fields:** 

* facility: Facility  
* timeBlock: TimeBlock  
* Id: int  
* host: Member // can be null  
* staffSupervising: ArrayList\<Staff\>  
* participants: ArrayList\<Member\>

**Methods:**

* Event(Facility, TimeBlock, Member)  
* isFull(): boolean  
* registerParticipant(Member): boolean  
* assignStaff(Staff): boolean  
* *advanceHours(int): void*  
* toString(): String  
* All accessor mutators

**Class:** Competition  
**Purpose:** Represents a competitive event with participants and scoring  
**Type:** Child class of Events  
**Fields:** 

* prize: double  
* participationCost: double

**Methods:**

* Competition(Facility, TimeBlock, Member, double)  
* toString(): String  
* setWinner(Member): boolean  
* advanceHours(int): void  
* toString(): String  
* All accessor mutators

**Class:** Fundraiser  
**Purpose:** Represents an event to raise money  
**Type:** Child class of Event  
**Fields:** 

* goal: double

**Methods:**

* Fundraiser(Facility, TimeBlock, Member, double)  
* setTotalRaised(double): boolean  
* advanceHours(int): void  
* toString(): String  
* All accessor mutators

**Class:** Staff  
**Purpose:** Manages community operations and events  
**Type:** Abstract  
**Fields:** 

* id: int  
* name:String  
* shifts: Schedule

**Methods:**

* Staff(String)  
* *calculatePay(): double*  
* scheduleShift(Event): boolean  
* *printPayroll()*  
* toString(): String  
* All accessor mutators

**Class:** FullTimeStaff  
**Purpose:** Salaried permanent employee  
**Type:** Child class of Staff  
**Fields:** 

* BASE\_SALARY: double  
* YEARLY\_RAISE: double  
* yearsWorked: int

**Methods:**

* FullTimeStaff(String, int)  
* calculatePay(): double  
* printPayroll()  
* toString(): String  
* All accessor mutators

**Class:** PartTimeStaff  
**Purpose:** Hourly employee with limited hours  
**Type:** Child class of Staff  
**Fields:** 

* hoursWorked: int  
* hourlySalary: double  
* maxWeeklyHours: int

**Methods:**

* PartTimeStaff(int, String, int, double, int)  
* calculatePay(): double  
* printPayroll()  
* toString(): String  
* All accessor mutators


**Class:** Member  
**Purpose:** Tracks membership status and privileges  
**Type:** Abstract  
**Fields:** 

* BIWEEKLY\_BASE: double  
* MONTHLY\_BASE: double  
* ANNUAL\_BASE: double  
* id: int  
* age: int  
* name: String  
* planType: PlanType (enum)  
* registrations: Schedule  
* dateRegistered: TimeBlock

**Methods:**

* Member(int, String, PlanType (enum))  
* *calculateBill(): double*  
* membershipDetails(): String  
* advanceHours(int): void  
* *printBill()*  
* toString(): String  
* registerFor(Event): boolean  
* All accessor mutators

**Class:** YouthMember  
**Purpose:** Represents a youth member with a guardian and different fees  
**Type:** Child class of Member  
**Fields:** 

* DISCOUNT\_RATE: double  
* guardian: AdultMember

**Methods:**

* YouthMember(int, String, PlanType, AdultMember)  
* calculateBill(): double  
* printBill()  
* toString(): String  
* All accessor mutators

**Class:** AdultMember  
**Purpose:** Represents	 an adult with variable fees  
**Type:** Child class of Member  
**Fields:**

* contactPhone: String  
* address: String  
* billAmount: double  
* billPaid: boolean  
* children: ArrayList\<YouthMember\>  
* hostings: ArrayList\<Event\>

**Methods:**

* AdultMember(int, String, PlanType, String, String, double, boolean)  
* calculateBill(): double  
* payBill(double): double  
* printBill()  
* toString(): String  
* addHosting(Event)  
* All accessor mutators

**Class:** MemberManager  
**Purpose:** Manages members  
**Fields:** 

* members: ArrayList\<Member\>

**Methods:**

* addMember(Member)  
* printAllBills()  
* generateId(): int  
* searchById(int): Member

**Class:** EventManager  
**Purpose:** Implements and manages event functionalities  
**Fields:** 

* events: ArrayList\<Event\>

**Methods:**

* book(Event): boolean // false if event type cannot be booked for the specified facility type  
* generateId(): int  
* printAllEvents()  
* printPastEvents()  
* printFutureEvents()  
* printOngoingEvents()  
* advanceHours(int): void  
* printAlphabetical()  
* cancelEvent(int): boolean

**Class:** StaffManager  
**Purpose:** Implements and manages staff functionalities  
**Fields:** 

* staff: ArrayList\<Staff\>

**Methods:**

* addStaff(Staff)  
* generateId(): int  
* searchById(int): Staff  
* printAllPayrolls()  
* availableStaff(TimeBlock): ArrayList\<Staff\>  
* printAlphabetical()

**Class:** FacilityManager  
**Purpose:** Implements and manages facility functionalities  
**Fields:** 

* facility: ArrayList\<Facility\>

**Methods:**

* addFacility(Facility)  
* generateId(): int  
* searchById(int): Facility  
* printAllFacilities()  
* availableFacilities(TimeBlock): ArrayList\<Facility\>

**Class:** TimeManager  
**Purpose:** Controls the time of the community centre  
**Fields:** 

* year: int  
* month: Month (enum)  
* date: int  
* eventSchedule: Schedule  
* staffManager: StaffManager  
* memberManager: MemberManager

**Methods:**

* advanceHour(): void  
* advanceHours(int): void  
* advanceToDay(int, Month (enum), int) // advances to 12:00 AM on that day  
* getCurrentTimeBlock(): TimeBlock // returns a TimeBlock with duration of 0 (start and end time are equal to current time)

**Class:** Main  
**Purpose:** Main method to run all Managers  
**Methods:**

* main(String\[\])

---

### **2B. Algorithms**

All searchById methods use binary search (IDs are unique and generated in ascending order).  
**Add/Delete/Modify member, event, staff, and facility records & prevent double bookings::**  
Method: addMember \- within MemberManager  
Purpose: Adds a new member to the arraylist of members  
Parameters:

* Member: the member object containing the information of the member to be added

Return Value: void  
Algorithm (pseudocode):  
memberList.add(new member object)  
*Similar functionality for addStaff*

Method: book \- within EventManager  
Purpose: Schedules a new event for a facility  
Parameters:

* Event: the event object containing the information of the event to be scheduled

Return Value: boolean \- false if event type does not match specified facility type or if the room is already booked over the timeblock or if the event would be booked in the past  
Algorithm (pseudocode):  
If facility type is invalid for event type:  
	return false  
If event.getHost() \!= null:  
If event.getHost() not instanceof adultMember:  
		Return false  
event.getHost().addHosting(event)

If (event.getFacility().getBookings.add(Event)): // add event to facility schedule  
events.add(Event) // add event to event manager  
Else: return false  
*Return true*

Method: add \- within Schedule  
Purpose: Adds event to schedule if the event’s schedule is not conflicting with any other timeblocks within the schedule  
Parameters:

* Event: the event to add

Return Value: boolean \- false if the timeblock already has another booking conflicting with it  
Algorithm (pseudocode):  
If (this.isBlockFree(event.getTimeBlock())):  
	timeBlocks.add(Event)  
	Return true

Return false  
Bubble sort from top

Method: isBlockFree \- within Schedule  
Purpose: Checks if the timeblock specified is not conflicting with any other timeblocks within the schedule  
Parameters:

* TimeBlock: the timeblock to check is free

Return Value: boolean \- false if the timeblock already has another booking conflicting with it  
Algorithm (pseudocode):  
Looping through every event within schedule object:  
	If event.getTimeBlock().isConflicting(TimeBlock):  
		Return false

Return true

**Assign staff to work events:**  
Method: boolean assignStaff (Staff staff) \- within Event  
Purpose: Add a staff member to supervise this event and books the same time slot in the staff member’s shift schedule, but only if the staff member is available and not already assigned.  
Parameters:

* Staff- the Staff object to assign

Return Value:

-  true if assignment (and shift booking) succeeded  
- False if staff is null, already supervising, has a schedule conflict, or (for part-time) would exceed weekly hours

Algorithm (pseudocode):  
assignStaff(staff):  
	if staff \== null:  
		return false  
	if staffSupervising.contains(staff):  
		return false  
	if not staff.scheduleShift(this):  
		return false  
	staffSupervising.add(staff)  
	return true

**Register attendees for events:**  
Method: boolean registerParticipant (Member member) \- within Event  
Purpose: Registers a member for the event if they are not already registered, the event is not full, and the time block does not conflict with their personal schedule.  
Parameters:

* member \- Member object requests to register

Return Value: 

- true if registration is successful  
- False if   
  - Member is null   
  - Member is already registered  
  - Event is full  
  - The member has a conflicting event at the sametime

Algorithm (pseudocode):  
registerParticipant(member):  
    if member \== null:  
        return false

    if participants.contains(member):  
        return false // already registered

    if isFull():  
        return false // event at max capacity

    if not member.getRegistrations().isBlockFree(this.getTimeBlock()):  
        return false // time conflict with another event  
    participants.add(member)  
    member.getRegistrations().add(this)

    return true

**Calculate staff payroll based on type:**  
Method: double calculatePay() \- within Staff  
Purpose: Calculates and returns the pay for a staff member, based on whether they are full time or part time.  
Parameters: 

* none

Return Value: double \- the calculated pay amount   
Algorithm (pseudocode):  
FullTimeStaff:  
calculatePay():  
    return (BASE\_SALARY+YEARLY\_RAISE\*yearsWorked)/12

PartTimeStaff:  
calculatePay():  
    if hoursWorked \> maxWeeklyHours:  
        hoursWorked \= maxWeeklyHours 

    return hourlySalary \* hoursWorked

**Calculate member bill:**  
Method: double calculateBill() \- within Member  
Purpose: Calculates the total bill for the member based on their plan type, and includes any additional charges like event registration fees or dependent fees.  
Parameters: 

* none

Return Value: double \- total bill amount owed by the member  
Algorithm (pseudocode):  
AdultMember:  
calculateBill():  
  switch(planType)  
   case(BIWEEKLY):  
	base \= BIWEEKLY\_BASE  
	break  
   case(MONTHLY):  
   	base \= MONTHLY\_BASE  
	break  
   case(ANNUAL):  
	base \= ANNUAL\_BASE  
	break  
   default:  
base \= 0.00  
break

    for each event in registrations:  
        if event instanceof Competition:  
            base \+= event.getParticipationCost()

    return base

YouthMember  
calculateBill():  
   switch(planType)  
   case(BIWEEKLY):  
	base \= BIWEEKLY\_BASE  
	break  
   case(MONTHLY):  
   	base \= MONTHLY\_BASE  
	break  
   case(ANNUAL):  
	base \= ANNUAL\_BASE  
	break  
   default:  
base \= 0.00  
break

    base\*=(1-DISCOUNT\_RATE)

    for each event in registrations:  
        if event instanceof Competition:  
	eventCost \= event.getParticipationCost()  
            base \+= eventCost-(eventCost\*DISCOUNT\_RATE)

    return base

**Print staff payrolls and membership bills**  
Method: void printAllPayrolls() \- within StaffManager  
Purpose: Displays the calculated pay for each staff member in the system, using their overridden calculatePay() method  
Parameters: 

* none

Return Value: void  
Algorithm (pseudocode):  
printAllPayrolls():  
    for each staff in staffList:  
        name \= staff.getName()  
        id \= staff.getId()  
        pay \= staff.calculatePay()  
        print "Staff ID:", id, "Name:", name, "Pay: $", pay

Method: printAllBills()  
Purpose: Displays the bill amount for each member by calling their calculateBill() method.  
Parameters: 

* none

Return Value: void  
Algorithm (pseudocode):  
printAllBills():  
    for each member in members:  
        name \= member.getName()  
        id \= member.getId()  
        amount \= member.calculateBill()  
        print "Member ID:", id, "Name:", name, "Bill: $", amount

**Cancel Event:**  
Method: boolean cancelEvent(int id) \- within EventManager  
Purpose: Removes the event given its id, freeing up the facility.  
Parameters:

* id – the TimeBlock identifying which event to cancel

Return Value:

- true if an event was found and successfully cancelled  
- false if no event exists with that time block

Algorithm (pseudocode):  
cancelEvent(id):  
    for each event in events:  
        if event.getID() \== id:  
            event.getFacility()  
                 .getBookings()   
                 .cancelEvent(id)  
              
            if event.getHost() \!= null and event.getHost() instanceof AdultMember:  
                event.getHost().getHostings().cancelEvent(id)  
              
            for staff in event.getStaffSupervising():  
                staff.getShifts().cancelEvent(id)  
              
            events.remove(event)  
            return true

    return false

**Print Future Events:**  
Method: void printFutureEvents() \- withinEventManager  
Purpose: Prints details of all events that are scheduled to occur after the current time maintained by the system  
Parameters:

* none

Return Value: void  
Algorithm (pseudocode):  
printFutureEvents():  
    currentTime \= TimeManager.getCurrentTimeBlock()

    for each event in events:  
        if event.getTimeBlock().compareToStart(currentTime) \> 0:  
            print(event.toString())

**Print Past Events:**  
Method: void printPastEvents() \- withinEventManager  
Purpose: Displays all events that ended before the current time tracked by the system  
Parameters:

* none

Return Value: void  
Algorithm (pseudocode):  
printPastEvents():  
    currentTime \= TimeManager.getCurrentTimeBlock()

    for each event in events:  
        if event.getTimeBlock().compareToEnd(currentTime) \< 0:  
            print(event.toString())

**Print Ongoing Events:**  
Method: void printOngoingEvents() \- within EventManager  
Purpose: Displays all events that are currently taking place  
Parameters:

* none

Return Value: void  
Algorithm (pseudocode):  
printOngoingEvents():  
    currentTime \= TimeManager.getCurrentTimeBlock()

    for each event in events:  
        eventBlock \= event.getTimeBlock()  
        if eventBlock.compareToStart(currentTime) \<= 0 and eventBlock.compareToEnd(currentTime) \> 0:  
            print(event.toString())

**Advance Time:**  
Method: void advanceHours(int time) \- within TimeManager  
Purpose: Advances time by a specified amount in hours  
Parameters:

* time: int

Return Value: void  
Algorithm (pseudocode):

- Calls eventSchedule.advanceHours(time)  
  - Loop through all events within this schedule, calling event.advanceHours(time)  
    - This checks if the event has passed, notifying the user while asking them to enter the amount raised if fundraiser or the winner if competition.


- Checks the amount of weeks if any that have passed since last week (using Jan 1 XXXX as the baseline), paying all part time staff based on the time passed  
- Checks the amount of years if any that have passed since the last year, paying all full time staff based on the time passed  
- Does similar checks for members, checking for biweekly monthly and annual and billing according members

*Also used in advanceHour(), which passes in a time value of 1\.*  
*advanceToDay(int, Month, int) will calculate the amount of hours passed from one date to another and call this method while passing in that value.*

**Text-based interface for all operations:**  
This will include:

* Saving and loading all information to and from files members.txt, events.txt, facilities.txt, and staff.txt  
* Listing, creating, searching for, modifying, and deleting members, events, facilities, and staff  
* Listing staff and members in alphabetical order, listing staff by highest pay and alphabetical order, listing sports facilities by rating or meeting facilities by size, etc.  
* Listing past, future, and ongoing events, or all events that will occur within a specified time period.  
* Assigning staff to events given staff id  
* Booking events in a facility given event details and facility id  
* Registering members to events given member id  
* Calculating and printing individual staff pay and community centre payroll  
* Advancing system time by an amount of hours to simulate time passing.  
  * If an event occurs in this time period, print the information of the event that has passed  
  * You are asked to enter the amount raised if it was a fundraiser, and the member who won if it was a competition.  
  * Also pays staff and bills members based on time passed
