package by.epamtraining.financial_accounting.dao.impl;

import by.epamtraining.financial_accounting.bean.Record;
import by.epamtraining.financial_accounting.bean.User;
import by.epamtraining.financial_accounting.dao.RecordDAO;
import by.epamtraining.financial_accounting.dao.exception.DAOException;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileRecordDAO implements RecordDAO {

    private String recordFilePath = "classpath://RecordFile.txt";

//    public void addRecord(User currentUser, double sum) throws DAOException{
//        HashMap<Integer, Record> recordsMap = pullRecordsList(recordFilePath);
//        Record newRecord = new Record(currentUser, sum);
//        recordsMap.put(recordsMap.size()+1, newRecord);
//        pushRecordsList(recordsMap);
//        System.out.println("Success! " + sum + " was added!");
//    }

    public void addRecord(Record newRecord) throws DAOException{
        if(newRecord == null){
            throw new DAOException("Can't add null record.");
        }
        List<Record> recordList = pullRecordsList();
        recordList.add(newRecord);
        pushRecordsList(recordList);
    }

//    public List<Record> getRecords(User currentUser) throws DAOException{
//        Map<Integer, Record> recordsMap = pullRecordsList(recordFilePath);
//        List<Record> allRecords = new ArrayList<Record>(recordsMap.values());
//
//        for(int i=0; i<allRecords.size(); i++){
//            if(!allRecords.get(i).getUserLogin().equals(currentUser.getLogin())){
//                allRecords.remove(i);
//                i--;
//            }
//        }
//        return allRecords;
//    }

    public List<Record> getUserRecords(User currentUser) throws DAOException{
        if (currentUser == null){
            throw new DAOException("User can't be null");
        }
        List<Record> allRecords = pullRecordsList();
//        System.out.println("all records size = " + allRecords.size());                                   //testing
        List<Record> currentUserRecords = new ArrayList<>();
//        System.out.println("curr user list size init = " + currentUserRecords.size());                  //testing

        for(Record rec : allRecords){
            if(rec.getUserLogin().equals(currentUser.getLogin())){
                currentUserRecords.add(rec);
            }
        }
        return currentUserRecords;
    }

    @Override
    public List<Record> getAllRecords() throws DAOException{
        return pullRecordsList();
    }

    private List<Record> pullRecordsList() throws DAOException {
        try (InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("RecordFile.txt")){

//            InputStream is = new FileInputStream(path);
            ObjectInput oi = new ObjectInputStream(resourceAsStream);
            List<Record> recordList = (ArrayList<Record>)oi.readObject();
//            HashMap<Integer, Record> usersMap = (HashMap<Integer, Record>) oi.readObject();
            oi.close();
            return recordList;
        } catch (EOFException e1){
            return new ArrayList<Record>();
        }
        catch (Exception e){
            throw new DAOException(e);
        }
    }

    private void pushRecordsList(List<Record> updatedRecords) throws DAOException{
        File file;
        try {
            file = Path.of(this.getClass().getClassLoader().getResource("RecordFile.txt").toURI()).toFile();
        } catch (Exception e){
            throw new DAOException(e);
        }

        try (OutputStream os = new FileOutputStream(file)){
            ObjectOutput oo = new ObjectOutputStream(os);
            oo.writeObject(updatedRecords);
            oo.close();
        } catch (Exception e){
            throw new DAOException(e);
        }

    }
}
