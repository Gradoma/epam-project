package by.epamtraining.financial_accounting.dao.impl;

import by.epamtraining.financial_accounting.bean.Record;
import by.epamtraining.financial_accounting.bean.User;
import by.epamtraining.financial_accounting.dao.RecordDAO;
import by.epamtraining.financial_accounting.dao.exception.DAOException;
import by.epamtraining.financial_accounting.dao.exception.DAOValidationException;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileRecordDAO implements RecordDAO {

    public void addRecord(Record newRecord) throws DAOException{
        if(newRecord == null){
            throw new DAOValidationException("Null reference to Record object.");
        }
        List<Record> recordList = pullRecordsList();
        recordList.add(newRecord);
        pushRecordsList(recordList);
    }

    public List<Record> getUserRecords(User currentUser) throws DAOException{
        if (currentUser == null){
            throw new DAOValidationException("Null reference to User object");
        }
        List<Record> allRecords = pullRecordsList();
        List<Record> currentUserRecords = new ArrayList<>();
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
            ObjectInput oi = new ObjectInputStream(resourceAsStream);
            List<Record> recordList = (ArrayList<Record>)oi.readObject();
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
