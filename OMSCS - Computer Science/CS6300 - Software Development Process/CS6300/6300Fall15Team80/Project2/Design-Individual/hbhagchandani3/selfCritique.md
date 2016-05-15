# Pros of design #

1. When the team discussed this design we decided that idea of manager facade objects would be an ideal adaption from this design. This would reduce the coupling of the UI layer so it only has to know about the manager classes and not any internal implementation details of the model layer. It will also allow the internal implementation of the system to changed during maintenance without breaking existing code.

2. Using the aggregation relationships helps us to decide which class should contain which other class. This helps in assigning responsibilities to the various objects of the system and figuring out how to map the design to code.

# Cons of design #

1. A CreditCard class is unnecessary as the team decided that we will scan credit card details on a transaction basis. Thus this design will need to be changed when adopted. This decision will help us to simplify the code and also help users to preserve their privacy, as only what is absolutely needed by the system will be stored.

2. The dependency between the Customer and Credit class and the Discount and Transaction class is unnecessary. Rather, the the Customer should be only depend on the RewardManager. Same with the Transaction class. This reduces unnecessary coupling in the system.

3. In the discussion we realized that that we would need additional utility classes, such as a QR scanner or a payment processor for credit cards. The design is incomplete.