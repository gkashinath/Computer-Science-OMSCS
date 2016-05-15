# Design 1 #

![Design 1][design1]

## Pros ##

1. When the team discussed this design we decided that idea of manager facade objects would be an ideal adaption from this design. This would reduce the coupling of the UI layer so it only has to know about the manager classes and not any internal implementation details of the model layer. It will also allow the internal implementation of the system to changed during maintenance without breaking existing code.

2. Using the aggregation relationships helps us to decide which class should contain which other class. This helps in assigning responsibilities to the various objects of the system and figuring out how to map the design to code.

## Cons ##

1. A CreditCard class is unnecessary as the team decided that we will scan credit card details on a transaction basis. Thus this design will need to be changed when adopted. This decision will help us to simplify the code and also help users to preserve their privacy, as only what is absolutely needed by the system will be stored.

2. The dependency between the Customer and Credit class and the Discount and Transaction class is unnecessary. Rather, the the Customer should be only depend on the RewardManager. Same with the Transaction class. This reduces unnecessary coupling in the system.

3. In the discussion we realized that that we would need additional utility classes, such as a QR scanner or a payment processor for credit cards. The design is incomplete.

# Design 2 #

![Design 2][design2]

## Pros ##

1. Using the aggregation relationships helps to break down the classes and find relationships between them.
 
2. This aggregation helps break down the code and make it more manageable and optimize the UI modules.

3. Computed quatities were missing in other designs which will be incorporated in the new design.

4. Helped identify that the credit card class doesn't need storage and also is independent of other classes like rewards and discount in the class diagram.

## Cons ##

1. The design was more flowchart like and was missing methods in the classes.

2. Although the class diagram captured the class relations, it was missing the UML format or methods within classes.

3. QR code and ID generations could have been utilities rather than classes.

4. Missing utilities like money and date.

# Design 3 #

![alt text][design3]

## Pros ##

1. Simple and easy to implement design based on the classes derived out of critical objects.

2. Attributes and functions clubbed into classes based on transactional model.

## Cons ##

1. As there is no mention of credit card information storage in requirements, credit card class may or may not be needed.

2. Missing function to scan QR card.

3. Ignored the fact that gold status is calculated based on transactions from last 12 months.

4. Remove customer information function: not needed based on requirements.

# Design 4 #

![alt text][design4]

## Pros ##

1. The Cusomer Class, Transaction Class, and Rewards Class are consistent with the teams agreed upon implementation

2. The external utilities are mostly consistent with the agree upon strategy.

3. The Reward class hierarchy allows for adding additional rewards in the future and changing the values associated with the existing rewards. 

## Cons ##

1. The CreditCard class is not needed since the attributes of the credit card are not stored and only needed for the immediate tranaction.  Therefore the Credit Card class was removed and the creditDate attribute was removed from the Customer class.

2. The sumOfPurcases method does not belong in the Customer Class and should be handled by the Rewards Class.

3. In our team discussion, there was a question about how the QR card is obtained.  The method for obtaining the QR code is not shown here.

4.  The method "listRewards" is not needed since it is not explicitly listed in the requirements and the details about the rewards are associated with the Transaction class.

5.  viewCustomerData is not needed since the capability to specifically list the data about a customer is not listed as a requirement.

6. The "Items" class is not required since only the final transaction amount is needed for proper functionality.

# Team Design #

![alt text][design-team]

## Commonalities and Differences ##

### Diagram 1 ###

The final design incorporates the basic structure of this diagram. The key commonalities between diagram 1 and the team design are the aggregation relationships and the manager classes. By having the UI layer communicate only with the manager objects we increase the maintainability of the model layer, because the internal design can be changed without breaking the interface presented to the UI layer. The aggregation relationships make is easier to map the design to the code.

There are also a few differences. First, there is no credit card class. We decided that nowhere in the requirements does it specify that we need to store credit card information; it only specifies that we must use it for transactions. Another difference is that the customer and transaction classes depend only on the reward manager, and not on reward subclasses. This reduces unnecessary coupling. In essence it makes the customer management one module and the rewards management another module in the model layer. Finally there are a few attributes and operations that are different. These are changed to incorporate compromises between all 4 designs.

### Diagram 2 ###

The final design has similar main classes with improvements which includes; adding of methods to classes and streamlined communication between layers. These improvements were needed for design 2 to modify the flow-chart type structure. The modular structure of customer manager, transaction manager and rewards manager was retained but the credit card class was removed because it was an independent entity not linked to any of the other classes. This was a key finding during our discussions and was unanimously accepted.

Finer details like utilities were identified and added along with labeling for the connectors. Overall the final design had the best features of all four designs after evaluating the pros and cons for each design.

### Diagram 3 ###

The final design and design 3 share almost similar Customer and Transaction classes, credit card class has been removed as we don’t need to store that information, there are three additional classes viz. customer manager, reward manager and gold status discount as compare to design 3 which will make the final design more aligned with the implementation.

### Diagram 4 ###

There are many similarities between this diagram and the final design.  The Customer and Transaction Classes are almost identical and the Reward class structure is very similar.  Similar to the other diagrams, the CreditCard class was removed entirely.  The use of "Class Managers" was not implemented in this diagram but I think that it is a great way to interface with the data.  A lot of the class methods were replaced by external packages utilities as we realized that we can utilize esiting pakages.

# Summary #

* Even if you try to avoid gold plating, you might individually read in implied requirements that don't exist.

* Discussing design with teammates helps to make a design simpler and more effective.

* Discussing design with teammates also helps iron out any misunderstandings of the requirements.

* There are multiple ways to do things and yours is not necessarily the best.

* There is no such thing as a perfect UML diagram but the collaboration involved in creating one helps the team members wrap their heads around the product.

* Keeping the UML diagram at a high level leaves room for interpretation during design.  A highly detailed UML diagram can constrict the team members during development.

* The UML diagram is not set in stone and can be tweaked during development.  The most important thing is to have an architecture plan that is abided by to improve the quality, cohesion and maintainability of the final product.

* Brainstorming over design diagram and collaborative team effort brought in wide range of design perspectives and resulted in final design which will more closely depict the implementation and will be easier to translate into code. 



[design1]: ..//Design-Individual/hbhagchandani3/diagram-critque.png "Design 1"
[design2]: ..//Design-Individual/gkashinath3/diagram-critique.png "Design 2"
[design3]: ..//Design-Individual/jbajwa7/diagram-critique.png "Design 3"
[design4]: ..//Design-Individual/jhenry38/diagram-critique.png "Design 4"
[design-team]: design-team.png "Team-Design"
