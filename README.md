
**OrderCombiner3**

This project is used to find combinable orders for riders.

Distances between locations are calculated using google's *Distance Matrix API*.

#Order Combination
Given a set of orders ( Pick up , Dropoff ) Combinable orders are determined using a radius metric from the first order.

#TSP 
Once combinable orders are determined, the traveling salesman approach is solved using a Nearest Neighbour algorithm,
this is in turn used to get the shortest distance through nodes
with a constraint that ensures a drop-off is only traversed following the traversal of its corresponding pick-up.
