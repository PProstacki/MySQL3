package mysql3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sorting {

    SingletonConnection sc = SingletonConnection.getInstance();
    long data[][] = new long[2000000][2];

    long sortSQL() {
        long startTime = System.nanoTime();
        try {
            sc.statement.executeQuery("select * from speedtest order by DATA;");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return System.nanoTime() - startTime;
    }

    long sortJava() {
        long startTime = System.nanoTime();

        boolean swap = true;
        for (int i = 0; i < data.length && swap; i++) {
            swap = false;
            for (int k = 0; k < data.length - 1; k++) {
                if (data[k][1] > data[k + 1][1]) {
                        long x = data[k][0];
                        long y = data[k][1];
                        data[k][0] = data[k + 1][0];
                        data[k][1] = data[k + 1][1];
                        data[k + 1][0] = x;
                        data[k + 1][1] = y;
                        swap = true;
                }
            }
        }
        return System.nanoTime() - startTime;
    }
    
    public void dataToTable(){
        try {
            ResultSet rs = sc.statement.executeQuery("select * from speedtest;");
            int i = 0;
            while(rs.next()){
               data[i][0] = rs.getLong(1);
               data[i][1] = rs.getLong(2);
               ++i;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void showTable(){
        for(int i = 0;i < data.length;i++){
            System.out.println("id: " + data[i][0] + "   Data: " + data[i][1]);
        }
    }
    
}
