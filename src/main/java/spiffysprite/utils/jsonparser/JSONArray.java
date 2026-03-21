package spiffysprite.utils.jsonparser;

import java.util.ArrayList;
import java.util.Arrays;

import static spiffysprite.utils.jsonparser.JSONObject.NEW_LINE;

public class JSONArray extends ArrayList<JSONValue> {
    public void add() { super.add(null); }
    public void add(String value) { super.add(new JSONValue(value)); }
    public void add(Number value) { super.add(new JSONValue(value)); }
    public void add(Boolean value) {
        super.add(new JSONValue(value));
    }
    public void add(JSONObject value) {
        super.add(new JSONValue(value));
    }
    public void add(JSONArray value) { super.add(new JSONValue(value)); }
    public void add(JSONValue[] values) { super.addAll(Arrays.asList(values)); }

    public boolean containsValue(String value) { return this.containsValue(new JSONValue(value)); }
    public boolean containsValue(Number value) { return this.containsValue(new JSONValue(value)); }
    public boolean containsValue(Boolean value) { return this.containsValue(new JSONValue(value)); }
    public boolean containsValue(JSONObject value) { return this.containsValue(new JSONValue(value)); }
    public boolean containsValue(JSONArray value) { return this.containsValue(new JSONValue(value)); }
    public boolean containsValue() { return this.containsValue(new JSONValue()); }
    private boolean containsValue(JSONValue value) { return super.contains(value); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[");

        for (int i = 0; i < size() - 1; ++i) {
            sb.append(get(i));
            sb.append(",");
        }

        sb.append(getLast());
        sb.append("]");

        return sb.toString();
    }

    public String toPrettyString() {
        return toPrettyString(0);
    }

    String toPrettyString(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        StringBuilder indent = new StringBuilder();
        indent.append("    ".repeat(Math.max(0, indentLevel)));

        sb.append("[");
        sb.append(NEW_LINE);

        for (int i = 0; i < size() - 1; ++i) {
            sb.append(indent);
            sb.append("    ");
            sb.append(get(i).toPrettyString(indentLevel + 1));
            sb.append(",");
            sb.append(NEW_LINE);
        }

        sb.append(indent);
        sb.append("    ");
        sb.append(getLast().toPrettyString(indentLevel + 1));
        sb.append(NEW_LINE);
        sb.append(indent);
        sb.append("]");

        return sb.toString();
    }
}
