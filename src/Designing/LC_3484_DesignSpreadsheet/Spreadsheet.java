package Designing.LC_3484_DesignSpreadsheet;

class Spreadsheet {

    private int[][] sheet ;

    public Spreadsheet(int rows) {
        sheet = new int[rows][26];
    }
    
    public void setCell(String cell, int value) {
        char col = cell.charAt(0);
        int row = Integer.parseInt( cell.substring(1) );
        sheet[row-1][col-'A'] = value;
    }
    
    public void resetCell(String cell) {
        char col = cell.charAt(0);
        int row = Integer.parseInt( cell.substring(1) );
        sheet[row-1][col-'A'] = 0;
    }
    
    public int getValue(String formula) {
        // String[] arr = formula.substring( 1 ).split("\\+");
        int ind = formula.indexOf("+");
        String cell1 = formula.substring(1,ind);
        String cell2 = formula.substring(ind+1);
        int val1 , val2 ;
        try{
            val1 = Integer.parseInt( cell1 );
        }
        catch( NumberFormatException e ){
            char col = cell1.charAt(0);
            int row = Integer.parseInt( cell1.substring(1) );
            val1 = sheet[row-1][col-'A'];
        }
        try{
            val2 = Integer.parseInt( cell2 );
        }
        catch( NumberFormatException e ){
            char col = cell2.charAt(0);
            int row = Integer.parseInt( cell2.substring(1) );
            val2 = sheet[row-1][col-'A'];
        }
        return val1 + val2;
    }
}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.setCell(cell,value);
 * obj.resetCell(cell);
 * int param_3 = obj.getValue(formula);
 */