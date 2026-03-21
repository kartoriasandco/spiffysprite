package spiffysprite.utils;

import spiffysprite.models.EnhancedColour;
import spiffysprite.utils.jsonparser.JSONArray;
import spiffysprite.utils.jsonparser.JSONObject;
import spiffysprite.utils.jsonparser.JSONValue;

import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import static spiffysprite.utils.jsonparser.JSONValueTypes.*;

public abstract class JSONUtils {
    /**
     * Stores each pixel of a BufferedImage in JSON format.
     *
     * @param image image whose data is to be stored
     * @return JSON object containing the image data
     * @throws IllegalArgumentException if image is null
     */
    public static JSONObject imageToJSON(BufferedImage image) throws IllegalArgumentException {
        if (image == null) {
            throw new IllegalArgumentException("image is null");
        }

        JSONObject json = new JSONObject();
        JSONArray pixelColourArray = new JSONArray();
        json.add("height", image.getHeight());
        json.add("width", image.getWidth());

        for (int x = 0; x < image.getWidth(); ++x) {
            JSONArray row = new JSONArray();

            for (int y = 0; y < image.getHeight(); ++y) {
                EnhancedColour colour = EnhancedColour.fromInt(image.getRGB(x, y));
                int hsba = colour.hsba;
                String hex = (hsba == 0) ? "00000000" : Integer.toHexString(hsba);
                row.add(hex);
            }

            pixelColourArray.add(row);
            json.add("pixels", pixelColourArray);
        }

        return json;
    }

    /**
     * Creates a BufferedImage from a JSON object containing image data.
     *
     * @param json object in JSON format containing image data
     * @return BufferedImage representation of the data contained in json
     * @throws IllegalArgumentException if json is not correctly formatted
     */
    public static BufferedImage jsonToImage(JSONObject json) throws IllegalArgumentException {
        if (json == null) {
            throw new IllegalArgumentException("json is null");
        }

        JSONValue heightJSONValue = json.get("height");
        if (heightJSONValue == null) {
            throw new IllegalArgumentException("json does not have a height property");
        } else if (heightJSONValue.getValueType() != NUMBER) {
            throw new IllegalArgumentException("json height property is not a number");
        }

        JSONValue widthJSONValue = json.get("width");
        if (widthJSONValue == null) {
            throw new IllegalArgumentException("json does not have a width property");
        } else if (widthJSONValue.getValueType() != NUMBER) {
            throw new IllegalArgumentException("json width property is not a number");
        }

        JSONValue pixelsJSONValue = json.get("pixels");
        if (pixelsJSONValue == null) {
            throw new IllegalArgumentException("json does not have a pixels property");
        } else if (pixelsJSONValue.getValueType() != JSON_ARRAY) {
            throw new IllegalArgumentException("json pixels property is not a JSON array");
        }

        BufferedImage image = new BufferedImage(
                (int) widthJSONValue.value(),
                (int) heightJSONValue.value(),
                BufferedImage.TYPE_INT_ARGB
        );

        JSONValue pixels = json.get("pixels");
        JSONArray pixelGridArray = (JSONArray) pixels.value();

        for (int y = 0; y < pixelGridArray.size(); ++y) {
            JSONArray row = (JSONArray) pixelGridArray.get(y).value();
            for (int x = 0; x < row.size(); ++x) {
                String pixelHex = row.get(x).toString();
                image.setRGB(x, y, Integer.parseInt(pixelHex));
            }
        }

        return image;
    }
}
