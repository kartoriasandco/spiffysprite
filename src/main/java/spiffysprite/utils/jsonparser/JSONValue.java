package spiffysprite.utils.jsonparser;

import static spiffysprite.utils.jsonparser.JSONValueTypes.*;

public class JSONValue {
    private final JSONValueTypes valueType;
    private final Object value;

    public JSONValue() {
        valueType = NULL;
        this.value = null;
    }

    public JSONValue(String value) {
        valueType = STRING;
        this.value = value;
    }

    public JSONValue(Number value) {
        valueType = NUMBER;
        this.value = value;
    }

    public JSONValue(boolean value) {
        valueType = BOOLEAN;
        this.value = value;
    }

    public JSONValue(JSONObject value) {
        valueType = JSON_OBJECT;
        this.value = value;
    }

    public JSONValue(JSONArray value) {
        valueType = JSON_ARRAY;
        this.value = value;
    }

    public JSONValueTypes getValueType() {
        return valueType;
    }
    public Object value() {
        return value;
    }

    public String toPrettyString(int indentLevel) {
        if (value == null) {
            return "null";
        }

        return switch(valueType) {
            case NULL -> "null";
            case NUMBER, BOOLEAN -> value.toString();
            case STRING -> "\"" + value + "\"";
            case JSON_OBJECT -> ((JSONObject) value).toPrettyString(indentLevel);
            case JSON_ARRAY -> ((JSONArray) value).toPrettyString(indentLevel);
        };
    }

    @Override
    public String toString() {
        if (value == null) {
            return "null";
        } else if (valueType == STRING) {
            return "\"" + value + "\"";
        } else {
            return value.toString();
        }
    }
}
