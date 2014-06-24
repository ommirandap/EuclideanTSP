package cc4102.tarea3.geom;

public class Point{

	public double X, Y;

	public Point(double x, double y){
		this.X = x;
		this.Y = y;
	}

	public double getX(){
		return this.X;
	}

	public double getY(){
		return this.Y;
	}

	/**
	 * Distancia euclideana. Mide la distancia euclideana entre este punto y otro
	 * @param p
	 * 		El otro punto para medir la ditancia.
	 * @return
	 * 		La distancia entre este punto y el punto dado.
	 */
	public double distance(Point p) {
		return Math.sqrt(distance2(p));
	}
	
	/**
	 * Distancia al cuadrado entre este punto y otro. Sirve para hacer comparaciones de
	 * distancias.
	 * @param p
	 * 		El otro punto para medir la ditancia.
	 * @return
	 * 		La distancia al cuadrado entre este punto y el punto dado.
	 */
	public double distance2(Point p) {
		
		double absX = Math.abs(this.X + p.getX());
		double absY = Math.abs(this.Y + p.getY());
		double powX = absX * absX;
		double powY = absY * absY;

		return powX + powY;
	}

	public void setX(double x){
		this.X = x;
	}

	public void setY(double y){
		this.Y = y;
	}

	public String toString(){
		return "(" + this.X + "," + this.Y + ")";
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Point){
			Point other = (Point) o;
			return getX() == other.getX() && getY() == other.getY();
		}
		return false;
	}

}