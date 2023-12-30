package view.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author waqas
 */
public class TrainData {

    private SimpleStringProperty name;// space name
    private SimpleDoubleProperty length;// Train Length
    private SimpleDoubleProperty speed;// Max Train speed limit
    private SimpleDoubleProperty acceleration; // Static train Deceleration
    private SimpleDoubleProperty overlap;// OverLap
    private SimpleDoubleProperty sighting; // Sighting
    private SimpleDoubleProperty signalSpacing; // Signal Spacing
    private SimpleDoubleProperty headway; // headway for 3 aspect
    // private SimpleDoubleProperty headway4;

    /*
     * constructor with some initial data
     * 
     * @param name
     * 
     * @param length
     * 
     * @param speed
     * 
     * @param acceleration
     * 
     * @param overlap
     * 
     * @param sighting
     * 
     * @param signalSpacing
     * 
     * @param headway
     */
    public TrainData(String n, double l, double v, double a, double o, double s, double d, double h) {
        this.name = new SimpleStringProperty(n);
        this.length = new SimpleDoubleProperty(l);
        this.speed = new SimpleDoubleProperty(v);
        this.acceleration = new SimpleDoubleProperty(a);
        this.overlap = new SimpleDoubleProperty(o);
        this.sighting = new SimpleDoubleProperty(s);
        this.signalSpacing = new SimpleDoubleProperty(d);
        this.headway = new SimpleDoubleProperty(h);
        // this.headway4 = new SimpleDoubleProperty(h4);
    }

    /*
     * Default constructor
     */
    public TrainData() {
        this(null, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    }

    // Define a getter for the property's value

    public final String getName() {
        return name.get();
    }

    // Define a setter for the property's value
    public final void setName(String value) {
        name.set(value);
    }

    // Define a getter for the property itself
    public SimpleStringProperty NameProperty() {
        return name;
    }
    // Define a getter for the property's value

    public final double getLength() {
        return length.get();
    }

    // Define a setter for the property's value
    public final void setLength(double value) {
        length.set(value);
    }

    // Define a getter for the property itself
    public SimpleDoubleProperty lengthProperty() {
        return length;
    }

    // Define a getter for the property's value
    public final double getSpeed() {
        return speed.get();
    }
    // Define a setter for the property's value

    public final void setSpeed(double value) {
        speed.set(value);
    }
    // Define a getter for the property itself

    public SimpleDoubleProperty speedProperty() {
        return speed;
    }

    // Define a getter for the property's value
    public final double getAcceleration() {
        return acceleration.get();
    }
    // Define a setter for the property's value

    public final void setAcceleration(double value) {
        acceleration.set(value);
    }
    // Define a getter for the property itself

    public SimpleDoubleProperty accelerationProperty() {
        return acceleration;
    }

    // Define a getter for the property's value
    public final double getOverlap() {
        return overlap.get();
    }
    // Define a setter for the property's value

    public final void setOverlap(double value) {
        overlap.set(value);
    }
    // Define a getter for the property itself

    public SimpleDoubleProperty overlapProperty() {
        return overlap;
    }
    // Define a getter for the property's value

    public final double getSighting() {
        return sighting.get();
    }
    // Define a setter for the property's value

    public final void setSighting(double value) {
        sighting.set(value);
    }
    // Define a getter for the property itself

    public SimpleDoubleProperty sightingProperty() {
        return sighting;
    }
    // Define a getter for the property's value

    public final double getSignalSpacing() {
        return signalSpacing.get();
    }

    // Define a setter for the property's value
    public final void setSignalSpacing(double value) {
        signalSpacing.set(value);
    }
    // Define a getter for the property itself

    public SimpleDoubleProperty signalSpacingProperty() {
        return signalSpacing;
    }

    // Define a getter for the property's value
    public final double getHeadway() {
        return headway.get();
    }
    // Define a setter for the property's value

    public final void setHeadway(double value) {
        headway.set(value);
    }
    // Define a getter for the property itself

    public SimpleDoubleProperty headwayProperty() {
        return headway;
    }
    /*
     * //Define a getter for the property's value
     * public final double getHeadway4() {
     * return headway4.get();
     * }
     * //Define a setter for the property's value
     * 
     * public final void setHeadway4(double value) {
     * headway4.set(value);
     * }
     * //Define a getter for the property itself
     * 
     * public SimpleDoubleProperty headway4Property() {
     * return headway4;
     * }
     */

}
