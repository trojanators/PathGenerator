package pathgenerator.gui;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import pathgenerator.Main;
import pathgenerator.trajectory.Path;
import pathgenerator.trajectory.WaypointSequence.Waypoint;
import pathgenerator.util.WaypointTableData;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import pathgenerator.trajectory.WaypointSequence.Waypoint;

public class DrawWaypoints {

    private Pane canvas;
    private Path path;
    private WaypointTableData data;
    private ArrayList<Circle> waypointPointArray = new ArrayList<>();

    public DrawWaypoints(Pane canvas, WaypointTableData data){
        this.canvas = canvas;
        this.data = data;
    }
    
    
    // drawing waypoints based on checking to se if out of bounds
    public void drawWaypoints(int i){
        waypointPointArray.add(i, new Circle(5,Color.WHITE));
     
        double pathX = data.getX();
        double pathY = data.getY();

        if(checkBounds(pathX, pathY)){
            waypointPointArray.get(i).relocate(pathX, pathY);
            this.canvas.getChildren().add(waypointPointArray.get(i));
        } else{
            Main.logger.severe("ERROR CANNOT DRAW WAYPOINT PATH IS "+pathX + pathY);
        }
    }

    public boolean checkBounds(double x, double y) {
        //Convert waypoint convention to JavaFX
        return this.canvas.getLayoutBounds().contains(x, -y);
    }
      
    private void setSelectionColor(int i){
        waypointPointArray.get(i).setFill(Color.GOLD);
    }
   

		tangentLine = new Line();
		tangentLine.getStyleClass().add("tangent");
		tangentLine.startXProperty().bind(x);
		//Convert from WPILib to JavaFX coords
		tangentLine.startYProperty().bind(y.negate());
		setTangent(tangentVector);
		tangentLine.endXProperty().bind(Bindings.createObjectBinding(() -> getTangentX() + getX(), tangentX, x));

		//Convert from WPILib to JavaFX coords
		tangentLine.endYProperty().bind(Bindings.createObjectBinding(() -> -getTangentY() + -getY(), tangentY, y));
	}

	public void enableSubchildSelector(int i) {
		FxUtils.enableSubchildSelector(this.icon, i);
		getIcon().applyCss();
	}

	private void setupIcon() {
		icon.setLayoutX(-(icon.getLayoutBounds().getMaxX() + icon.getLayoutBounds().getMinX()) / 2 - ICON_X_OFFSET);
		icon.setLayoutY(-(icon.getLayoutBounds().getMaxY() + icon.getLayoutBounds().getMinY()) / 2);

		icon.translateXProperty().bind(x);
		//Convert from WPILib to JavaFX coords
		icon.translateYProperty().bind(y.negate());
		FxUtils.applySubchildClasses(this.icon);
		this.icon.rotateProperty()
				.bind(Bindings.createObjectBinding(
						() -> getTangent() == null ? 0.0 : Math.toDegrees(Math.atan2(-getTangentY(), getTangentX())),
						tangentX, tangentY));
		icon.getStyleClass().add("waypoint");
	}

	/**
	 * Convenience function for math purposes.
	 *
	 * @param other
	 *            The other Waypoint.
	 *
	 * @return The coordinates of this Waypoint relative to the coordinates of
	 *         another Waypoint.
	 */
	public Point2D relativeTo(Waypoint other) {
		return new Point2D(this.getX() - other.getX(), this.getY() - other.getY());
	}

	public boolean isLockTangent() {
		return lockTangent.get();
	}

	public BooleanProperty lockTangentProperty() {
		return lockTangent;
	}

	public void setLockTangent(boolean lockTangent) {
		this.lockTangent.set(lockTangent);
	}

	public boolean isReversed() {
		return reversed.get();
	}

	public BooleanProperty reversedProperty() {
		return reversed;
	}

	public void setReversed(boolean reversed) {
		this.reversed.set(reversed);
	}

	public Line getTangentLine() {
		return tangentLine;
	}

	public Point2D getTangent() {
		return new Point2D(tangentX.get(), tangentY.get());
	}

	public void setTangent(Point2D tangent) {
		this.tangentX.set(tangent.getX());
		this.tangentY.set(tangent.getY());
	}

	public double getTangentX() {
		return tangentX.get();
	}

	public double getTangentY() {
		return tangentY.get();
	}

	public void setTangentX(double tangentX) {
		this.tangentX.set(tangentX);
	}

	public void setTangentY(double tangentY) {
		this.tangentY.set(tangentY);
	}

	public Polygon getIcon() {
		return icon;
	}

	public double getX() {
		return x.get();
	}

	public DoubleProperty xProperty() {
		return x;
	}

	public void setX(double x) {
		this.x.set(x);
	}

	public double getY() {
		return y.get();
	}

	public DoubleProperty yProperty() {
		return y;
	}

	public void setY(double y) {
		this.y.set(y);
	}

	public Point2D getCoords() {
		return new Point2D(getX(), getY());
	}

	public void setCoords(Point2D coords) {
		setX(coords.getX());
		setY(coords.getY());
	}

	public String getName() {
		return name.get();
	}

	public StringProperty nameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public DoubleProperty tangentXProperty() {
		return tangentX;
	}

	public DoubleProperty tangentYProperty() {
		return tangentY;
	}

	public void reverseTangent() {
		tangentX.set(tangentX.get()*-1);
		tangentY.set(tangentY.get()*-1);
	}

	/**
	 * Converts the unit system of a this Waypoint.
	 *
	 * @param from
	 *            Unit to convert from.
	 * @param to
	 *            Unit to convert to.
	 */
	public void convertUnit(Unit<Length> from, Unit<Length> to) {
		var converter = from.getConverterTo(to);
		x.set(converter.convert(x.get()));
		y.set(converter.convert(y.get()));
		tangentX.set(converter.convert(tangentX.get()));
		tangentY.set(converter.convert(tangentY.get()));
	}



	@Override
	public String toString() {
		return String.format("%s (%f,%f), (%f,%f), %b %b", getName(), getX(), getY(), getTangentX(), getTangentY(), isLockTangent(), isReversed());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (getClass() != o.getClass()) {
			return false;
		}
		Waypoint point = (Waypoint) o;

		return x.get() == point.x.get() && y.get() == point.y.get() && tangentX.get() == point.tangentX.get()
				&& tangentY.get() == point.tangentY.get() && name.get().equals(point.name.get())
				&& isLockTangent() == point.isLockTangent() && isReversed() == point.isReversed();
	}
}