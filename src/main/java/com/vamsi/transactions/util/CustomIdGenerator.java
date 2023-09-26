package com.vamsi.transactions.util;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        String tableName = "transactions";
        String prefix = "TRN"; // Custom prefix for the transaction ID

        int nextId = getNextId(tableName, session);
        long updatedNextId = nextId + 1;
      
        

        String generatedId = prefix + String.format("%03d", updatedNextId); // Adjust the format as needed (e.g., "%04d" for 4-digit numbers)

        String insertSql = "INSERT INTO trans_id_generator (table_name, next_id) VALUES (?, ?) " +
                           "ON CONFLICT (table_name) DO UPDATE SET next_id = EXCLUDED.next_id";
        
        
        try {
            Connection con= session.connection();
            PreparedStatement statement=con.prepareStatement(insertSql);
                statement.setString(1, tableName);
                statement.setLong(2, updatedNextId);
                statement.executeUpdate();
            }
         catch (Exception e) {
        	 
            e.printStackTrace();
        }
        
        return generatedId;
    }

    private int getNextId(String tableName, SharedSessionContractImplementor session) {
        String selectSql = "SELECT next_id FROM trans_id_generator WHERE table_name = ?";
        try {
        	Connection con= session.connection();
            PreparedStatement statement=con.prepareStatement(selectSql);
            
                statement.setString(1, tableName);
                
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt("next_id");
                }
            }
         catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
