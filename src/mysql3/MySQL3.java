package mysql3;

public class MySQL3 {

    public static void main(String[] args) {
        SingletonConnection sc = SingletonConnection.getInstance();
        sc.fillTable();
        
        Sorting sort = new Sorting();
        long timeSQL;
        long timeJava;
        
        timeSQL = sort.sortSQL();
        sort.dataToTable();
        timeJava = sort.sortJava();
        
        System.out.println("Czas sortowania SQL to " + timeSQL + " ns");
        System.out.println("Czas sortowania Java to " + timeJava + " ns");
    }
}
