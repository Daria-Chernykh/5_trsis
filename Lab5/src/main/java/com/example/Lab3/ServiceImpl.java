package com.example.Lab3;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceImpl implements com.example.Lab3.Service {


    @Override
    public void create(String fullName, int taskOption, int number, int rating) {

        DBHelper.db.add(new Student(DBHelper.db.get(DBHelper.db.size()-1).getId()+1,
                fullName,
                taskOption,
                number,
                rating
        ));

    }

    @Override
    public List<Student> getAll() {
        return DBHelper.db;
    }

    @Override
    public List<Student> getByFullName(String fullName) {
        List<Student> les = new ArrayList<>();
        for(int i=0;i<DBHelper.db.size();++i){
            if(DBHelper.db.get(i).getFullName().contentEquals(fullName)){
                les.add(DBHelper.db.get(i));
            }
        }
        return les;
    }

    @Override
    public boolean delete(int id) {
        for(int i=0;i<DBHelper.db.size();++i){
            if(DBHelper.db.get(i).getId() == id){
                DBHelper.db.remove(i);
                return true;
            }
        }
        return false;
    }
}
