package orderCombiner.client;


import orderCombiner.client.LatLong;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Stack;

import com.google.maps.errors.ApiException;


public class TSPNearestNeighbour {
    
	 
	 
    private int numberOfNodes;
    private Stack<Integer> stack,myStack,correctorStack;
    ArrayList<Integer> myList, correctorList;
 
    public TSPNearestNeighbour()
    {
        stack = new Stack<Integer>();
        myStack = new Stack<Integer>();
        correctorStack = new Stack<Integer>();
        
        myList = new ArrayList<>();
        correctorList = new ArrayList<>();
        
    }
 
    public void tsp(double adjacencyMatrix[][], ArrayList<LatLong> nodes)
    {
        numberOfNodes = adjacencyMatrix[1].length - 1; //
        int[] visited = new int[numberOfNodes + 1];
        
        visited[1] = 1;
        stack.push(1);
        myStack.push(1);
        correctorStack.push(1);
        
        myList.add(1);
        correctorList.add(1);
        
        int element, dst = 0, i;
        //int min = Integer.MAX_VALUE;
        double min = Double.MAX_VALUE;
        
        boolean minFlag = false;
        //System.out.print(1 + "\n");
 
        while (!stack.isEmpty())
        {
            element = stack.peek();
            i = 1;
            min = Integer.MAX_VALUE;
            
            while (i <= numberOfNodes)
            {
                if (adjacencyMatrix[element][i] > 1 && visited[i] == 0)
                {
                    if (min > adjacencyMatrix[element][i])
                    {
                        min = adjacencyMatrix[element][i];
                        dst = i;
                        minFlag = true;
                    }
                }
                i++;
            }
            
            if (minFlag)
            {
                  
                stack.push(dst);
                
                if(dst<=nodes.size()/2){
                correctorStack.push(dst); 
                correctorList.add(dst);
                }
                
               // System.out.print(dst + "\n");
                minFlag = false;
                visited[dst] = 1; 
                
                //=========================================   Constraint ========================
                  for (Integer item: myStack){
                  
                  //System.out.println("my stack " + item);
                  //Compare with current item
                  if (nodes.get(item-1).getLabel().toLowerCase().contains(nodes.get(dst-1).getLabel().toLowerCase()) == true){
                   
                  //System.out.println("dst "+ Integer.toString(dst-1));    
                  //System.out.println("The pickup "+ item +" for dropoff:  "+ dst +" Does Exist");
                 
                  //correctorStack.pop();    
                  correctorStack.push(dst); 
                  correctorList.add(dst);
                  
                  continue;
                  }                        
                  }
                                
                 myStack.push(dst); 
                 myList.add(dst);
             //======================================================================================================
                 
                 
                //myStack.push(dst);
                continue;              
            }
            
            stack.pop();
        }
        
        //Getting the final best/ viable route
           
//        for (Integer item: myList){
//                   System.out.println("myList: "+ item);
//                   }
        
        List<Integer> c = new ArrayList<>(myList);
        c.removeAll(correctorList);
        
        
        for (Integer item: c){
                   correctorList.add(item);
                   }
        
        for (Integer item: correctorList){
                   System.out.println(" "+ item + "\t");
                   }
                
    } //end TSP
    
    
    
    
    
    
    
    public static void main(String... arg) throws IOException, ApiException, InterruptedException
    {
        int number_of_nodes;
        //Scanner scanner = null;
       
      try
        {   

    	//======= From Order Combination
          
          OrderCombiner orderCombiner = new OrderCombiner();
                   
          // ============== Calling necessary methods      
           ArrayList<LatLong> nodes = new ArrayList<>();
           
           nodes = orderCombiner.combiner(); //Determines combinable, returns combinable nodes
           number_of_nodes = nodes.size();
           
           System.out.println("number of nodes  " + nodes.size());
           
           double adjacency_matrix[][] = new double[number_of_nodes + 1][number_of_nodes + 1];
           double adjacency_matrix2[][] = new double[number_of_nodes + 1][number_of_nodes + 1];
          
           
          // ========================
           adjacency_matrix  = orderCombiner.adjacencyMaker(nodes);
           
          // Reading from the 2D matrix
            for(int i = 0 ; i <nodes.size() ; i++){

                for(int j = 0 ; j<nodes.size() ; j++){
                    
                System.out.println(adjacency_matrix[i][j]);
              
                }
            }
            
 //=======================================================================================  
          
     // Accepting the adjacency matrix
       
       for (int i = 1; i <= number_of_nodes; i++) // rows 
       {
           for (int j = 1; j <= number_of_nodes; j++) // columns
           {
               adjacency_matrix2[i][j] = adjacency_matrix[i-1][j-1];                 
           }
       }

       
       for (int i = 1; i <= number_of_nodes; i++)
       {
           for (int j = 1; j <= number_of_nodes; j++)
           {
               if (adjacency_matrix2[i][j] == 1 && adjacency_matrix2[j][i] == 0)
               {
                   adjacency_matrix[j][i] = 1.0;                     
               }
           }
       }
      
             
       System.out.println("the locations are visited as follows");
       TSPNearestNeighbour tspNearestNeighbour = new TSPNearestNeighbour();
       tspNearestNeighbour.tsp(adjacency_matrix2,nodes);
       
       
            
        } catch (InputMismatchException inputMismatch)
         {
             System.out.println("Wrong Input format");
         }

    }
 
}

