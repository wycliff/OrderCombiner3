package orderCombiner.client;



public class LatLong {
    
    private String label;
    private double latitude;
    private double longitude;

    // Constructors
    
    public LatLong(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

     public LatLong(double latitude, double longitude, String label) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.label = label;
        }
     
     
    public LatLong(){
    
    }
    
     public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude= longitude;
    }
    
}
