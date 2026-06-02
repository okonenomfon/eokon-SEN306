package week12;

public class QuarterlyDataProcessor {

    // Named constants replacing magic numbers (100, 4.0, 12) [cite: 51]
    private static final int MAX_RECORDS = 100;
    private static final int MONTHS_IN_YEAR = 12;
    private static final double ESTIMATED_REVENUE_MULTIPLIER = 4.0;
    
    // Dummy global arrays mentioned in the original messy routine [cite: 44]
    private static double[][] corpExpense = new double[5][MAX_RECORDS]; // Assuming 1-indexed quarters
    private static double[] profit = new double[MONTHS_IN_YEAR];
    private static ExpenseData expenseData = new ExpenseData();

    // Main routine: Unused parameters 'screenX' and 'screenY' removed [cite: 51]
    // Returns estimated revenue to fix Java pass-by-value limitations on primitive updates [cite: 37]
    public static double processQuarterlyData(InputRecord inputRec, int quarter, EmpRecord empRec, 
                                              double ytdRevenue, int newColor, int prevColor, int expenseType) {
        
        validateQuarter(quarter);
        
        initRevenueAndExpense(inputRec, quarter);
        
        updateCorpDatabase(empRec);
        
        double estimatedRevenue = calculateEstimatedRevenue(ytdRevenue, quarter);
        
        // Note: Reassigning primitives like newColor/prevColor/status here has no effect outside this scope[cite: 37], 
        // so those local assignments from the original routine have been intentionally dropped.
        
        calculateProfit(expenseType, inputRec.revenue);
        
        return estimatedRevenue;
    }

    // 1. Functional Cohesion: Throws exception if quarter is 0 [cite: 50, 51]
    private static void validateQuarter(int quarter) {
        if (quarter == 0) {
            throw new IllegalArgumentException("Quarter cannot be zero.");
        }
    }

    // 2. Functional Cohesion: Zeros out revenue and loads expense data [cite: 47, 53]
    private static void initRevenueAndExpense(InputRecord inputRec, int quarter) {
        for (int i = 0; i < MAX_RECORDS; i++) {
            inputRec.revenue[i] = 0;
            inputRec.expense[i] = corpExpense[quarter][i];
        }
    }

    // 3. Functional Cohesion: Calculates and returns estimated revenue [cite: 47, 54]
    private static double calculateEstimatedRevenue(double ytdRevenue, int quarter) {
        return (ytdRevenue * ESTIMATED_REVENUE_MULTIPLIER) / quarter;
    }

    // 4. Logical -> Functional Cohesion: Calculates profit uniformly [cite: 47]
    // Fixes the missing loops for expense types 2 and 3 [cite: 46, 52] and the undefined 'revenue' bug [cite: 42]
    private static void calculateProfit(int expenseType, double[] revenue) {
        for (int i = 0; i < MONTHS_IN_YEAR; i++) {
            double currentExpense = switch (expenseType) {
                case 1 -> expenseData.type1[i];
                case 2 -> expenseData.type2[i];
                case 3 -> expenseData.type3[i];
                default -> throw new IllegalArgumentException("Invalid expense type.");
            };
            profit[i] = revenue[i] - currentExpense; 
        }
    }

    // --- Dummy stubs to allow the file to compile ---
    
    private static void updateCorpDatabase(EmpRecord empRec) { 
        // Existing routine logic here [cite: 47]
    }

    public static class InputRecord {
        public double[] revenue = new double[MAX_RECORDS];
        public double[] expense = new double[MAX_RECORDS];
    }

    public static class EmpRecord {}

    public static class ExpenseData {
        public double[] type1 = new double[MONTHS_IN_YEAR];
        public double[] type2 = new double[MONTHS_IN_YEAR];
        public double[] type3 = new double[MONTHS_IN_YEAR];
    }
}