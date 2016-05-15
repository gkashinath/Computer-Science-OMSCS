# Design Choices

Below is some explanation for my design choices.

## Why are there so many strings for IDs and numbers?

IDs and some numbers do not behave like a numeric data types. For example, if an ID begins with a 0, as in 001, the two zeros will be ignored if we used a numeric type. Also the user may prefer to code in letters for certain IDs (in certain fast food joints this is how coupons are coded). Finally, some IDs are long and cannot be coded in standard numeric data types because of overflow.

## Why are rewards referred to by IDs in the Customer and Transaction classes instead of being objects?

The requirements clearly states that the user will be managing rewards. This means, whether they are enabled or disabled, for example is a dynamic property rather than a static property. From the time of there creation or time of use to some point in the future these properties can change. By keeping IDs rather than actual objects we can modify these properties dynamically and not break the records. When a Customer object is created so is a Credit object automatically, and its ID is tracked throughout its life

## Why isn't there an option to delete information?

This would seriously impact the records kept by the system. It would be very confusing if the transactions referred to non-existent rewards, for example. It's better to add new information rather than erase old information if something is out of date.

## Where are the business rules specified by the requirements?

They are coded into the methods of the classes. A class diagram is a static, rather than a dynamic, view of the system.

## How do I use this system?

The CustomerManager and RewardManager are what are plugged into the UI components and called by the user to do what the requirements say.

## How is the user supposed to see transaction and rewards records?

Right now I assume that the system just prints them to the console. If this were a real world application the listing of records would probably plugin to a UI module. But I focused on the core business logic to keep things simple and not gold plate the system with unstated requirements or functions.

## Why is the status an int rather than a hierarchical object in the Customer class?

Because I am assuming that the user might request more statuses in the future and it is easier to change an int if gold users must change in the future than to destroy and recreate an object in a hierarchy. Java really should support type aliases like C++ to make types easier to modify.

## Where are the credit card and customer card scanner objects?

They are not in the diagram. It is assumed that these component are external to the modeled system and are used to generate the ID's, credit card account numbers, etc, and the the UI invokes the manager classes shown with this information.