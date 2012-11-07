package practice.basic;

public class JPractice {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        JPractice p = new JPractice();
        String s = "abc";
        //assert p.isUniqueChar(s) : "the string does have duplicates.";
        assert p.isUniqueChar1(s) : "the string does have duplicates (by bitstring).";
        int a = 2;
        System.out.print(a++ + ++a);
        int[][] s1 = new int[0][0];
        System.out.print(s1.length);
    }

    public boolean isUniqueChar(String s) {
        boolean [] indicators = new boolean[256];
        for(int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            if(indicators[a]) return false;
            indicators[a] = true;
        }
        return true;
    }
    
    public boolean isUniqueChar1(String s) {
        int indicator = 0;
        for(int i = 0; i < s.length(); i ++) {
            int pos = s.charAt(i) - 'a';
            if((indicator & (1 << pos)) > 0) return false;
            indicator |= (1 << pos);
        }
        return true;
    }
}
