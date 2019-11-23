package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Book;
import models.DateTime;
import models.Dvd;
import models.LibraryItem;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.WestminsterLibraryManager;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class LibraryController extends Controller {

    private WestminsterLibraryManager westminterManager;

    @Inject
    public LibraryController(WestminsterLibraryManager libraryManager) {
        westminterManager = libraryManager;
    }


    //all items
    public Result index() {
        List<LibraryItem> list = westminterManager.display();
        return ok(Json.toJson(list));
    }

    public Result get(String id) {
        LibraryItem item = westminterManager.get(id);
        return ok(Json.toJson(item));
    }

    //create an item
    public Result create() {
        //return empty spaces to the create form
        ArrayList<Integer> spaces = westminterManager.spaceLeft();
        return ok(Json.toJson(spaces));
    }

    //add an item to the list
    public Result save() {
        JsonNode json = request().body().asJson();

        ObjectNode result = Json.newObject();

        try {
            LibraryItem item;
            if (json.get("itemType").toString().equals("1")) {
                item = Json.fromJson(json, Book.class);
            } else {
                item = Json.fromJson(json, Dvd.class);
            }
            //check if isbn already exists
            boolean isbnExists = westminterManager.isbnExists(json.get("isbn").asText());
            boolean result1 = false;
            if(!isbnExists) {

                //add the datePublished
                var dateStr = json.get("datePublished").asText();
                if (dateStr != null) {

                    int y = Integer.parseInt(dateStr.substring(0, 4));
                    int m = Integer.parseInt(dateStr.substring(5, 7));
                    int d = Integer.parseInt(dateStr.substring(8, 10));

                    item.setPublishedDate(new DateTime(d, m, y, 0, 0));
                }

                result1 = westminterManager.addItem(item);
            }
            if (result1) {
                result.put("status", "OK");
                result.put("message", "Library Item is successfully added. ");
            } else {
                result.put("status", "FAIL");
                if(isbnExists)
                    result.put("message", "ISBN is not unique. ");
                else
                    result.put("message", "Library Item is not added. ");
            }

        } catch (Exception e) {
            throw e;
        }
        return ok(result);
    }

    public Result delete(String id) {

        Integer itemType = westminterManager.deleteItem(id);
        ArrayList<Integer> spaces = westminterManager.spaceLeft();

        ObjectNode result = Json.newObject();

        result.put("status", "OK");
        result.put("message", "Library Item is successfully deleted. ");
        result.put("itemType", itemType);
        result.set("spaceLeft", Json.toJson(spaces));
        return ok(result);

    }

    public Result deleteIsbn(String id) {

        westminterManager.deleteByIsbn(id);
        ArrayList<Integer> spaces = westminterManager.spaceLeft();

        ObjectNode result = Json.newObject();

        result.put("status", "OK");
        result.put("message", "Library Item is successfully deleted. ");
        result.put("itemType", "1");
        result.set("spaceLeft", Json.toJson(spaces));
        return ok(result);
    }

    public Result borrowIsbn(){

        JsonNode json = request().body().asJson();
        ObjectNode result = Json.newObject();

        try{
            var status = westminterManager.borrow(json);
            ArrayList<Integer> spaces = westminterManager.spaceLeft();
            result.set("status", status);
            //result.put("message", "Library Item is successfully borrowed. ");
            //result.put("itemType", "1");
            result.set("spaceLeft", Json.toJson(spaces));
        }catch (Exception e){
            throw e;
        }
        return ok(result);
    }

    public Result returnItem(String isbn){
        ObjectNode result = Json.newObject();
        try{
            var lateHrs = westminterManager.returnItem(isbn);
            ArrayList<Integer> spaces = westminterManager.spaceLeft();
            result.set("lateHrs", Json.toJson(lateHrs));
            //result.put("message", "Library Item is successfully returned. ");
            //result.put("itemType", "1");
            result.set("spaceLeft", Json.toJson(spaces));
        }catch (Exception e){
            throw  e;
        }
        return  ok(result);
    }

    public Result report(){
        ObjectNode result = Json.newObject();
        try{
            var dueList = westminterManager.generateReport();
            ArrayList<Integer> spaces = westminterManager.spaceLeft();
            result.set("dueList", Json.toJson(dueList));
            result.set("spaceLeft", Json.toJson(spaces));
        }catch (Exception e){
           throw e;
        }
        return ok(result);
    }

    public void reserve(String isbn){

    }

    //extra
    public Result edit(String id) {
        return TODO;
    }

    public Result update() {
        return TODO;
    }


}
