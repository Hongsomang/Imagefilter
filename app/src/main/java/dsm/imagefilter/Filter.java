package dsm.imagefilter;

/**
 * Created by ghdth on 2018-09-20.
 */

public interface Filter extends Cloneable {
    public abstract Filter createClone();

}
