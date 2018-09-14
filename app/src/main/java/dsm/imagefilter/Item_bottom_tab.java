package dsm.imagefilter;

import android.graphics.drawable.Drawable;

/**
 * Created by ghdth on 2018-09-14.
 */

public class Item_bottom_tab {
    private int image;
    private String name;

    public Item_bottom_tab(int image, String name){
        this.image=image;
        this.name=name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
