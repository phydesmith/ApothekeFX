package com.javasmithy.data.names;

import java.util.List;

/**
 *
 * A helper class for loading names from json format.
 */
public class NameList {
    private List<String> names;

    public NameList(List<String> names) {
        this.names = names;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public String getNameAt(int index){
        return this.names.get(index);
    }
}
