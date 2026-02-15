class Solution {
    public String addBinary(String a, String b) {
        int N = Math.min( a.length(), b.length() );
        int i = 0;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int al = a.length();
        int bl = b.length();
        while( i < N ){
            int n1 = a.charAt(al-1-i)-'0';
            int n2 = b.charAt(bl-1-i)-'0';
            int val = n1+n2+carry;
            // System.out.println(val);
            if( val == 2 ){
                sb.append("0");
                carry = 1;
            }
            else if( val == 3 ){
                sb.append("1");
                carry = 1;
            }
            else{
                sb.append(""+val);
                carry = 0;
            }
            i++;
        }
        int j = a.length()-i-1;
        while( j >= 0 && a.length() > N ){
            int n1 = a.charAt(j)-'0';
            int val = n1+carry;
            System.out.println(val);
            if( val == 2 ){
                sb.append("0");
                carry = 1;
            }
            else if( val == 3 ){
                sb.append("1");
                carry = 1;
            }
            else{
                sb.append(""+val);
                carry = 0;
            }
            j--;
        }
        j = b.length()-i-1;
        while( j >= 0 && b.length() > N ){
            int n1 = b.charAt(j)-'0';
            int val = n1+carry;
            if( val == 2 ){
                sb.append("0");
                carry = 1;
            }
            else if( val == 3 ){
                sb.append("1");
                carry = 1;
            }
            else{
                sb.append(""+val);
                carry = 0;
            }
            j--;
        }
        if( carry != 0 ) sb.append(carry+"");
        return sb.reverse().toString();
    }
}