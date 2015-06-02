package utilities;

// mutable 2D vectors
public final class Vector2D {

// fields
private double x, y; 

// construct a null vector
public Vector2D(){
	setX(0);
	setY(0);
} 

// construct a vector with given coordinates
public Vector2D(double x, double y){
	setX(x);
	setY(y);
}

// construct a vector that is a copy of the argument
public Vector2D(Vector2D v){
	this(v.getX(),v.getY());	
}

// set coordinates 
public void set (double x, double y) {
	this.setX(x);
	this.setY(y);
} 

// set coordinates to argument vector coordinates
public void set (Vector2D v) {
	this.set(v.getX(),v.getY());
}

// compare for equality (needs to allow for Object type argument...) 
public boolean equals(Object o) {
	if((Double.compare(((Vector2D)o).getX(), this.getX()) == 0) &&
			(Double.compare(((Vector2D)o).getY(), this.getY()) == 0)){
		return true;
	}
	else{
		return false;
	}
}

//  magnitude (= "length") of this vector 
public double mag() {
	return Math.hypot(getX(), getY());
}

// angle between vector and horizontal axis in radians
public double theta() { 
	return Math.atan2(getY(), getX())*(180/Math.PI);
}

// String for displaying vector as text 
public String toString() {
	String x = "X coordinate: " + this.getX() + ".";
	String y = "Y coordinate: " + this.getY() + ".";
	String t = "Theta: " + this.theta() + " degree.";
	String r = "Magnitude: " + this.mag() + ".";
	return x + "\n" + y + "\n" + t + "\n" + r + "\n";
}

//add coordinate values 
public void add(double x, double y) {
	this.setX(x + this.getX());
	this.setY(y + this.getY());
}
// add argument vector 
public void add(Vector2D v) {
	add(v.getX(),v.getY());
}

// weighted add - frequently useful 
public void add(Vector2D v, double fac) {
	add(v.getX()*fac,v.getY()*fac);
}

// multiply with factor 
public void mult(double fac) {
	this.setX(getX()*fac);
	this.setY(getY()*fac);
}

// "wrap" vector with respect to given positive values w and h
// method assumes that x >= -w and y >= -h
public void wrap(double h, double w) {
	if(this.getX() > w){
		this.setX(this.getX()-w);
	}
	else if(this.getX() < 0){
		this.setX(this.getX()+w);
	}
	
	if(this.getY() > h){
		this.setY(this.getY()-h);
	}
	else if(this.getY() < 0){
		this.setY(this.getY()+h);
	}
}

public void wrapWidth(double h, double w,double radius) {
	if(this.getX() > w){
		this.setX(this.getX()-w);
	}
	else if(this.getX() < 0){
		this.setX(this.getX()+w);
	}
	if(this.getY()+radius >= h){
		this.setY(h-radius);
	}
	else if(this.getY()-radius <= 0){
		this.setY(+radius);
	}
}

// rotate by angle given in radians 
public void rotate(double theta) {
	double xP = 0;
	double yP = 0;
	xP = this.getX()*Math.cos(theta) - this.getY()*Math.sin(theta);
	yP = this.getX()*Math.sin(theta) + this.getY()*Math.cos(theta);
	this.setX(xP);
	this.setY(yP);
}

// scalar product with argument vector 
public double scalarProduct(Vector2D v) {
	return v.getX()*this.getX() + v.getY()*this.getY();
}

// distance to argument vector 
public double dist(Vector2D v) {
	double xd = v.getX() - this.getX();
	double yd = v.getY() - this.getY();
	return Math.sqrt(xd*xd+yd*yd);
}

// normalise vector so that mag becomes 1
// direction is unchanged  
public void normalise() {	
	double xn = (this.getX() / this.mag());
	double yn = (this.getY() / this.mag());	
	this.setX(xn);
	this.setY(yn);
}

public double getX() {
	return x;
}

public void setX(double x) {
	this.x = x;
}

public double getY() {
	return y;
}

public void setY(double y) {
	this.y = y;
}

}