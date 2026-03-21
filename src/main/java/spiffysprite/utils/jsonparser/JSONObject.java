package spiffysprite.utils.jsonparser;

import java.util.LinkedHashMap;

public class JSONObject extends LinkedHashMap<String, JSONValue> {
    public static final String NEW_LINE = System.lineSeparator();

    public void add(String label, String value) { put(label, new JSONValue(value)); }
    public void add(String label, Number value) { put(label, new JSONValue(value)); }
    public void add(String label, Boolean value) { put(label, new JSONValue(value)); }
    public void add(String label, JSONObject value) { put(label, new JSONValue(value)); }
    public void add(String label, JSONArray value) { put(label, new JSONValue(value)); }
    public void add(String label) { put(label, null); }

    public boolean containsKey(String key) { return super.containsKey(key); }

    public boolean containsValue(String value) { return this.containsValue(new JSONValue(value)); }
    public boolean containsValue(Number value) { return this.containsValue(new JSONValue(value)); }
    public boolean containsValue(Boolean value) { return this.containsValue(new JSONValue(value)); }
    public boolean containsValue(JSONObject value) { return this.containsValue(new JSONValue(value)); }
    public boolean containsValue(JSONArray value) { return this.containsValue(new JSONValue(value)); }
    public boolean containsValue() { return this.containsValue(new JSONValue()); }
    private boolean containsValue(JSONValue value) { return super.containsValue(value); }

    public JSONValue get(String key) { return super.get(key); }

    /**
     * Returns a String representation of this JSON object in an easily-readable format. The output is valid JSON.
     *
     * @return formatted representation of this JSON object
     */
    public String toPrettyString() {
        return toPrettyString(0);
    }

    /**
     * Internal method that returns a formatted String representation of this JSON object with correct indentation. By
     * default, no indents are added. The output is valid JSON.
     *
     * @param indentLevel number of indents to prefix this object with
     * @return formatted representation of this JSON object
     */
    String toPrettyString(int indentLevel) {
        StringBuilder indent = new StringBuilder();
        indent.append("    ".repeat(Math.max(0, indentLevel)));
        StringBuilder sb = new StringBuilder();
        String[] keys = keySet().toArray(new String[0]);

        sb.append("{");
        sb.append(NEW_LINE);

        for (int i = 0; i < keys.length; ++i) {
            JSONValue value = get(keys[i]);
            String formattedValue = (value == null) ? "null" : value.toPrettyString(indentLevel + 1);

            sb.append(indent);
            sb.append("    \"")
                    .append(keys[i])
                    .append("\": ")
                    .append(formattedValue);

            if (i < keys.length - 1) {
                sb.append(",");
            }

            sb.append(NEW_LINE);
        }

        sb.append(indent);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String[] keys = keySet().toArray(new String[0]);

        sb.append("{");

        for (int i = 0; i < keys.length; ++i) {
            sb.append("\"")
                    .append(keys[i])
                    .append("\":")
                    .append(get(keys[i]));
            if (i < keys.length - 1) {
                sb.append(",");
            }
        }

        sb.append("}");

        return sb.toString();
    }
}
