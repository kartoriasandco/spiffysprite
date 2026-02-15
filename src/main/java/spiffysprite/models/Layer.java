//package spiffysprite.models;
//
//import spiffysprite.utils.NumberUtils;
//
//import java.util.HashMap;
//
//public class Layer {
//    private final int index;
//    private final HashMap<Pair<Integer>, EnhancedColour> colourCoordinates;
//    private Layer(HashMap<Pair<Integer>, EnhancedColour> colourCoordinates, int index) {
//        this.index = index;
//        this.colourCoordinates = colourCoordinates;
//    }
//
//    public EnhancedColour getColourAt(int x, int y) throws IllegalArgumentException {
//        if (!NumberUtils.inRange(x, 0, colourCoordinates.length)) {
//            throw new IllegalArgumentException("")
//        }
//    }
//
//    public static Layer fromColourCoordinates(ColourCoordinate[] colourCoordinates, int index) {
//
//        return new Layer(colourCoordinates, index);
//    }
//}
