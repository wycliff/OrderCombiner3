package orderCombiner.client;

import java.io.IOException;

import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;

public class DistanceMatrixClient {
	
	  
	 public static long getDistance(double lat1, double long1, double lat2, double long2) throws ApiException, InterruptedException, IOException{
			
		 String API_KEY = "AIzaSyCQB6hAaF8KyG9CZ-mOpCN_5UPbw3iqwqE";
		 String addr0ne, addrTw0;
		 
		addr0ne = Double.toString(lat1)+","+Double.toString(long1);
		addrTw0  = 	Double.toString(lat2)+","+Double.toString(long2);	
		 
			//set up key
		   	GeoApiContext distCalcer = new GeoApiContext.Builder()
				    .apiKey(API_KEY)
				    .build();
		   	
		   	DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(distCalcer); 
		       DistanceMatrix result = req.origins(addr0ne)
		               .destinations(addrTw0)
		               .units(Unit.METRIC)
//		               .mode(TravelMode.DRIVING)
//		               .avoid(RouteRestriction.TOLLS)
		               .language("en-US")
		               .await();
		       
					long distApart = result.rows[0].elements[0].distance.inMeters;
			
			return distApart;
		}
	
	 
	 
		
//	 public  long getDriveDist(String addrOne, String addrTwo) throws ApiException, InterruptedException, IOException{
//			
//		 String API_KEY = "AIzaSyCQB6hAaF8KyG9CZ-mOpCN_5UPbw3iqwqE";
//		 
//			//set up key
//		   	GeoApiContext distCalcer = new GeoApiContext.Builder()
//				    .apiKey(API_KEY)
//				    .build();
//		   	
//		   	DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(distCalcer); 
//		       DistanceMatrix result = req.origins(addrOne)
//		               .destinations(addrTwo)
//		               .units(Unit.METRIC)
//		               .mode(TravelMode.DRIVING)
//		               .avoid(RouteRestriction.TOLLS)
//		               .language("en-US")
//		               .await();
//		       
//					long distApart = result.rows[0].elements[0].distance.inMeters;
//			
//			return distApart;
//		}	 
	 
	 
	 
	 
//	 public static void main(String... arg) throws ApiException, InterruptedException, IOException
//	    {
//		 
//		 System.out.println("Hello Gradle" );
//		 
//		 DistanceMatrixClient  driver = new DistanceMatrixClient();
//		 long distance = driver.getDriveDistance(44.928046,-94.410307, 33.695787,-116.359998);
//		 
//		 System.out.println("Hello Gradle:   " + distance );
//	    }
	 
}
