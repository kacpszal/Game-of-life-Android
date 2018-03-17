package pl.edu.agh.gameoflife.persistence;

import android.support.annotation.NonNull;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Emitter;
import com.couchbase.lite.Mapper;
import com.couchbase.lite.Query;
import com.couchbase.lite.View;

import java.util.Date;
import java.util.Map;

/**
 * Created by grzegorz on 11/20/17.
 */

public class GridDao implements Comparable<GridDao> {
    int[][] cellsState;
    int sizeY;
    int sizeX;
    Date date;

    private String _id;
    private String _rev;

    public static Query getGrid(Database database) {
        View view = database.getView("app/grids");
        if (view.getMap() == null) {
            view.setMap(new Mapper() {
                @Override
                public void map(Map<String, Object> document, Emitter emitter) {

                    emitter.emit(document.get("_id"), null);
                }
            }, "1");
        }
        return view.createQuery();
    }

    public static void removeGrid(GridDao gridDao, Database database) {
        Document document = database.getDocument(gridDao.get_id());
        if (document != null) {
            try {
                document.delete();
            } catch (CouchbaseLiteException e) {
                e.printStackTrace();
            }
        }
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_rev() {
        return _rev;
    }

    public void set_rev(String _rev) {
        this._rev = _rev;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int[][] getCellsState() {
        return cellsState;
    }

    public void setCellsState(int[][] cellsState) {
        this.cellsState = cellsState;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int compareTo(@NonNull GridDao gridDao) {
        return gridDao.date.compareTo(this.date);
    }
}
