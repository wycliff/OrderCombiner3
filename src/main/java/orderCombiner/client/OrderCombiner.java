package orderCombiner.client;




import orderCombiner.client.LatLong;
import java.io.IOException;
import java.util.ArrayList;

import com.google.maps.errors.ApiException;


public class OrderCombiner {
    
    DistanceMatrixClient dc = new DistanceMatrixClient();
   
    double radius = 3.5;
    
     
    // constructor
    public OrderCombiner(){
        
    }
      

// Adjacency Matrix maker
   public double[][] adjacencyMaker( ArrayList<LatLong> nodes)throws IOException, ApiException, InterruptedException {
    
         System.out.println("============== The Points from start to end");
         
        for(int n = 0; n < nodes.size(); n++){     
        System.out.println(n+1 + " )" +nodes.get(n).getLatitude()+" "+ nodes.get(n).getLongitude()+ " " + nodes.get(n).getLabel());
        
        } 
           System.out.println("===============================  \n");
           System.out.println("The distances adjacency matrix ");
       
          double adjacency_matrix[][] = new double[nodes.size()][nodes.size()];
//        System.out.println("Enter the adjacency matrix");
          
              // Accepting the adjacency matrix    
            for (int i = 0; i < nodes.size(); i++) // rows 
            {
                for (int j = 0; j < nodes.size(); j++) // columns
                {
                    nodes.get(i).getLatitude();
                    
           double dist = DistanceMatrixClient.getDistance(nodes.get(j).getLatitude(), nodes.get(j).getLongitude(), nodes.get(i).getLatitude(), nodes.get(i).getLongitude());
              
           //Haversine 
           //double dist = distance(nodes.get(j).getLatitude(), nodes.get(i).getLatitude(),nodes.get(j).getLongitude(), nodes.get(i).getLongitude());

                adjacency_matrix[i][j] = dist;
//                      System.out.println(adjacency_matrix[i][j]);
                }
            }
            
     return adjacency_matrix; 
   }
   
   
      
// combiner
   public ArrayList<LatLong> combiner() throws ApiException, InterruptedException, IOException{
       
              double lat_pick_ups[] =  {44.928046 , 44.903280,33.844843};//,44.92057,44.240309};
              double lat_drop_offs[] = {33.695787, 33.675843,33.844847};//,44.920474,44.240304};
              
              
              double long_pick_ups[] = {-94.410307,-94.392008,-116.54911};//,-93.44786,-91.493619};             
              double long_drop_offs[] =  {-116.359998, -116.34911,-116.549069};//,-93.447851,-91.493768};
             
               ArrayList<LatLong> nodes = new ArrayList<>();
               
               LatLong  obj_pick, obj_drop;
                           obj_pick = new LatLong(); 
                           obj_drop = new LatLong();
                                    
                      
                    // *********** First put the starting point *********************     
                    obj_pick.setLatitude(lat_pick_ups[0]);
                    obj_pick.setLongitude(long_pick_ups[0]);
                    obj_pick.setLabel("pd1");
                    
                    obj_drop.setLatitude(lat_drop_offs[0]);
                    obj_drop.setLongitude(long_drop_offs[0]);
                    obj_drop.setLabel("d1");
                   //         
                      nodes.add(obj_pick);
                      
               
           for(int i= 1 ; i< lat_pick_ups.length ; i++){
               
              
                                
                 LatLong  obj_picki;
                           obj_picki = new LatLong(); 
                              
                    obj_picki.setLatitude(lat_pick_ups[i]);
                    obj_picki.setLongitude(long_pick_ups[i]);
                    obj_picki.setLabel("pd"+Integer.toString(i+1));
                    
              
                    nodes.add(obj_picki); 
                    
            }
           
           //First drop off
           nodes.add(obj_drop);
           
           for(int i= 1 ; i< lat_pick_ups.length ; i++){
               
                                
                    LatLong  obj_dropi;
                           
                    obj_dropi = new LatLong();
                       
                 
                    obj_dropi.setLatitude(lat_drop_offs[i]);
                    obj_dropi.setLongitude(long_drop_offs[i]);
                    obj_dropi.setLabel("d"+Integer.toString(i+1));
                              
                    nodes.add(obj_dropi);         
               // }
           }
           
           
           if(nodes.size()>2){
               
           System.out.println("For "+ nodes.size()/2+" combinable orders");
           }
           else
           {
           System.out.println(" There are no combinable orders");
           }
           
              // 
       return nodes;
   
   }// end Combiner
   

   
// Methods formerly in use
   
//   public  double distance(double lat1, double lat2, double lon1,
//	        double lon2) {
//
//	    final int R = 6371; // Radius of the earth
//
//	    double latDistance = Math.toRadians(lat2 - lat1);
//	    double lonDistance = Math.toRadians(lon2 - lon1);
//	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
//	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
//	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
//	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//	    
//	    double distance = R * c ; // in Kilometers
//	    return distance;
//	}
   
   
   // Determines combinable orders from many, here 5
// public ArrayList<LatLong> determiner()throws IOException, ApiException, InterruptedException{
//     
//            double lat_pick_ups[] =  {44.928046 , 44.903280,33.844843,44.92057,44.240309};
//            double lat_drop_offs[] = {33.695787, 33.675843,33.844847,44.920474,44.240304};
//            
//            
//            double long_pick_ups[] = {-94.410307,-94.392008,-116.54911,-93.44786,-91.493619};
//            double long_drop_offs[] =  {-116.359998, -116.34911,-116.549069,-93.447851,-91.493768};
//           
//             ArrayList<LatLong> nodes = new ArrayList<>();
//             
//         for(int i= 1 ; i< lat_pick_ups.length ; i++){
//             
//             // return distance 
//              double dist_pick_ups = DistanceMatrixClient.getDriveDistance(lat_pick_ups[0],long_pick_ups[0],lat_pick_ups[i],long_pick_ups[i]);
//              
//              double dist_drop_offs  = DistanceMatrixClient.getDriveDistance(lat_drop_offs[0],long_drop_offs[0],lat_drop_offs[i],long_drop_offs[i]);
//                                             
//              //Add viable point to the LatLong ArrayList
//              if (dist_pick_ups <= radius && dist_drop_offs <= radius ){
//                    
//                  LatLong  obj_pick, obj_drop,obj_pick2, obj_drop2;
//                         obj_pick = new LatLong(); 
//                         obj_drop = new LatLong();
//                         
//                         obj_pick2 = new LatLong(); 
//                         obj_drop2 = new LatLong();
//                    
//                  // *********** First put the starting point *********************     
//                  obj_pick.setLatitude(lat_pick_ups[0]);
//                  obj_pick.setLongitude(long_pick_ups[0]);
//                  
//                  obj_drop.setLatitude(lat_drop_offs[0]);
//                  obj_drop.setLongitude(long_drop_offs[0]);
//                  
//                  
//                 // For other points (Use a loop)
//                  obj_pick2.setLatitude(lat_pick_ups[i]);
//                  obj_pick2.setLongitude(long_pick_ups[i]);
//                  
//                  obj_drop2.setLatitude(lat_drop_offs[i]);
//                  obj_drop2.setLongitude(long_drop_offs[i]);
//                  
//                  
//                  nodes.add(obj_pick);
//                  nodes.add(obj_pick2);
//                  
//                  nodes.add(obj_drop);
//                  nodes.add(obj_drop2);
//                  
//              }
//         }
//         
//         
//         if(nodes.size()>2){
//             
//         System.out.println("There are "+ nodes.size()/2+" combinable orders from the provided set");
//         }
//         else
//         {
//         System.out.println(" There are no combinable orders");
//         }
//         
//            
//     return nodes;
// 
// }// end new determiner()
// 
 
  
}// End Class
