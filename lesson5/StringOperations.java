public class StringOperations {

    public int getSum(int a, int b) {
        return a + b;
    }

    public double getSqrt(int a){
        return Math.sqrt(a);
    }

    public int getDivision(int a, int b) {
        return  a / b;
    }

    public double getAverage(int a, int b) {
        return (double) (a + b) / 2;
    }

    public String getSumUpperCase(String a, String b) {
        return (a + b).toUpperCase();
    }

    public String getReverseSumUpperCase(String a, String b) {
        return (b + a).toUpperCase();
    }
    public String getSum(String line1, String line2) {
        return line1 + line2;
    }

    public String getReverseSum(String line1, String line2, 	String line3) {
        return line3 + line2 + line1;
    }

    public int getLength(String line) {
        return line.length();
    }

}
