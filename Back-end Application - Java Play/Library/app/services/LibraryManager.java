package services;

import com.fasterxml.jackson.databind.JsonNode;
import models.LibraryItem;

import java.util.List;

public interface LibraryManager {
    public static int MAXBOOKAMT = 100;
    public static int MAXDVDAMT = 50;

    public boolean addItem(LibraryItem book);
    public Integer deleteItem(String itemId);
    public void deleteByIsbn(String isbn);
    public List<LibraryItem> display();
    public LibraryItem get(String id);
    public JsonNode borrow(JsonNode itemId);
    public JsonNode returnItem(String itemId);
    public List<LibraryItem> generateReport();
}