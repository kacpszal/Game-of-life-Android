package pl.edu.agh.gameoflife.persistence;

import android.content.Context;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.QueryEnumerator;
import com.couchbase.lite.QueryRow;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by grzegorz on 11/20/17.
 */

public class GridDaoRepository {

    public static void save(Database database, Object object) {
        ObjectMapper m = new ObjectMapper();
        Map<String, Object> props = m.convertValue(object, Map.class);
        String id = (String) props.get("_id");

        Document document;
        if (id == null) {
            document = database.createDocument();
        } else {
            document = database.getExistingDocument(id);
            if (document == null) {
                document = database.getDocument(id);
            } else {
                props.put("_rev", document.getProperty("_rev"));
            }
        }

        try {
            document.putProperties(props);
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
    }

    public static <T> T modelForDocument(Document document, Class<T> aClass) {
        ObjectMapper m = new ObjectMapper();
        return m.convertValue(document.getProperties(), aClass);
    }

    public static List<GridDao> getGrids(Context context) {

        DataManager manager = DataManager.getSharedInstance(context);
        QueryEnumerator questions = null;
        try {
            questions = GridDao.getGrid(manager.database).run();
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

        List<GridDao> data = new ArrayList<>();
        for (QueryRow question : questions) {
            Document document = question.getDocument();
            GridDao model = GridDaoRepository.modelForDocument(document, GridDao.class);
            data.add(model);
        }

        return data;
    }

    public static void delete(GridDao gridDao, Context context) {
        DataManager manager = DataManager.getSharedInstance(context);
        GridDao.removeGrid(gridDao, manager.database);
    }
}
