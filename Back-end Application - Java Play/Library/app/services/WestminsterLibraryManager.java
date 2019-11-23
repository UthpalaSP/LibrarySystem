package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.*;
import play.libs.Json;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WestminsterLibraryManager implements LibraryManager {

    List<LibraryItem> itemList;
    List<Reservation> reservationList;
    List<HistoryRecord> borrowalHistory;

    public WestminsterLibraryManager() {

        itemList = new ArrayList<>();
        reservationList = new ArrayList<>();
        borrowalHistory = new ArrayList<>();
    }

    public boolean isbnExists(String isbn){
        var item = itemList.stream().filter(i -> i.getIsbn().equals(isbn)).findAny().orElse(null);
        return item != null ? true : false;
    }

    @Override
    public boolean addItem(LibraryItem item) {

        Map<Class<? extends LibraryItem>, List<LibraryItem>> counted = itemList.stream().collect(Collectors.groupingBy(libraryItem -> {
            if (libraryItem instanceof Book) return Book.class;
            if (libraryItem instanceof Dvd) return Dvd.class;
            return null;
        }));

        //counted is null initially
        if (item instanceof Book) {
            if (counted.get(Book.class) != null) {
                if (counted.get(Book.class).size() < LibraryManager.MAXBOOKAMT) {
                    itemList.add(item);
                    return true;
                }
            }else {
                itemList.add(item);
                return true;
            }
        } else {
            if (counted.get(Dvd.class) != null) {
                if (counted.get(Dvd.class).size() < LibraryManager.MAXDVDAMT) {
                    itemList.add(item);
                    return true;
                }
            }else {
                itemList.add(item);
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer deleteItem(String itemId) {

        LibraryItem delItem = get(itemId);
        try {
            itemList.remove(delItem);
        } catch (Exception e) {
            throw e;
        }
        return delItem.getItemType();
    }

    public void deleteByIsbn(String isbn) {
        try{

            Predicate<LibraryItem> itemPredicate = p-> (p.getIsbn().equals(isbn));
            itemList.removeIf(itemPredicate);

        } catch (Exception e){
            throw e;
        }

    }

    @Override
    public List<LibraryItem> display() {
        return itemList;
    }

    public LibraryItem get(String id) {
        LibraryItem item = itemList.stream().filter(i -> i.getIsbn().equals(id)).findFirst().orElse(null);
        return item;
    }

    public ArrayList<Integer> spaceLeft() {
        Map<Class<? extends LibraryItem>, List<LibraryItem>> counted = itemList.stream().collect(Collectors.groupingBy(libraryItem -> {
            if (libraryItem instanceof Book) return Book.class;
            if (libraryItem instanceof Dvd) return Dvd.class;
            return null;
        }));

        ArrayList<Integer> spacesLeft = new ArrayList();
        if (counted.get(Book.class) != null) {
            spacesLeft.add(LibraryManager.MAXBOOKAMT - counted.get(Book.class).size()); //Book first
        } else {
            spacesLeft.add(LibraryManager.MAXBOOKAMT);
        }

        if (counted.get(Dvd.class) != null) {
            spacesLeft.add(LibraryManager.MAXDVDAMT - counted.get(Dvd.class).size()); //Dvd second
        } else {
            spacesLeft.add(LibraryManager.MAXDVDAMT);
        }

        return spacesLeft;
    }

    @Override
    public JsonNode borrow(JsonNode json) {
        LibraryItem item1 = itemList.stream().filter(i -> i.getIsbn().equals(json.get("isbn").asText())).findFirst().orElse(null);

        int i = itemList.indexOf(item1);
        if(item1 != null){
            if (item1.getDateBorrowed() == null) {
                //set by custom Date method
                DateTime borrowedDate = new DateTime();
                itemList.get(i).setDateBorrowed(borrowedDate);

                Reader newReader = new Reader();
                newReader.setName(json.get("borrower").toString());
                newReader.setMobileNo(json.get("borrowerNo").toString());

                itemList.get(i).setBorrower(newReader);

                //add to history list
                HistoryRecord newRecord = new HistoryRecord(json.get("isbn").asText(),newReader,borrowedDate);
                borrowalHistory.add(newRecord);

                return Json.toJson(true);
            }else {
                return Json.toJson(item1.getDateBorrowed());
            }
        }
        return Json.toJson(false);
    }

    @Override
    public JsonNode returnItem(String itemId) {
        ObjectNode result = Json.newObject();

        LibraryItem item1 = itemList.stream().filter(i -> i.getIsbn().equals(itemId)).findFirst().orElse(null);
        int i = itemList.indexOf(item1);

        if(item1 != null){
            if (item1.getDateBorrowed() != null) {
                //set by custom Date method
                DateTime returnDate = new DateTime();
                var hrsTookSoFar = item1.getDateBorrowed().substract(returnDate);
                itemList.get(i).setDateBorrowed(null);
                itemList.get(i).setBorrower(null);

                //update record in history list
                var prevRecord = borrowalHistory.stream().filter(h -> h.isbn.equals(itemId) && h.borrowedDate.equals(returnDate)).findFirst();
                int index = borrowalHistory.indexOf(prevRecord);
                borrowalHistory.get(index).returnDate = returnDate;
                borrowalHistory.get(index).hrsOccupied = returnDate.substract(borrowalHistory.get(index).borrowedDate);

                result.set("status", Json.toJson(true));
                result.set("itemType", Json.toJson(item1.getItemType()));
                result.set("hours", Json.toJson(hrsTookSoFar));

                return result;
            }
        }
        result.set("status", Json.toJson(false));
        result.set("hours", Json.toJson(0));
        return result;
    }

    @Override
    public List<LibraryItem> generateReport() {
        List<LibraryItem> dueList = new ArrayList<>();

        for (LibraryItem libraryItem : itemList) {
            int lateHrs = 0;
            if (libraryItem.getDateBorrowed() != null){
                var hrsTookSoFar = libraryItem.getDateBorrowed().substract(new DateTime());
                lateHrs = libraryItem instanceof Book ? hrsTookSoFar - 168 : hrsTookSoFar - 72;

                //lateHrs = 60; //testing purposes
                if(lateHrs > 0){
                    libraryItem.setLateHrs(lateHrs);
                    dueList.add(libraryItem);
                }
            }

        }

       Collections.sort(dueList, Comparator.comparingInt(LibraryItem::getLateHrs).reversed());
        return dueList;
    }

    public JsonNode reserve(String isbn){
        //estimate the availability
        LibraryItem item1 = itemList.stream().filter(i -> i.getIsbn().equals(isbn)).findFirst().orElse(null);

        List<Integer> hrsList = borrowalHistory.stream().filter(i -> i.isbn.equals(isbn))
                .map((student) -> student.hrsOccupied).collect(Collectors.toList());

        double avg = (double) hrsList.stream().mapToInt(i -> i).average().orElse(-1);
        int days = (int) (avg/24);
        DateTime availableDate = item1.getDateBorrowed().add(days);

        //more reservations; more time
        var existingRes = reservationList.stream().filter(i -> i.isbn.equals(isbn)).collect(Collectors.toList());

        for(Reservation i : existingRes){
            availableDate.add(days);
        }

        Reservation newRes = new Reservation(isbn, item1.getBorrower(), item1.getDateBorrowed(),availableDate);
        reservationList.add(newRes);

        ObjectNode result = Json.newObject();
        result.set("avaialableDate", Json.toJson(availableDate));

        return result;
    }
}
