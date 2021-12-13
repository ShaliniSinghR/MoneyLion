package Utilities;

import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

import java.util.Set;

public class JsonIgnoreAttribute extends CustomComparator {

    private final Set<String> attributesToIgnore;

    public JsonIgnoreAttribute(JSONCompareMode mode, Set<String> attributesToIgnore, Customization... customizations) {
        super(mode, customizations);
        this.attributesToIgnore = attributesToIgnore;
    }
}
