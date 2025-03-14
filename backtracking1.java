class backtracking1 {
    public static void printpermutations(String str, String newstr, int idx) {
        if(str.length()==0){
            System.out.println(newstr);
            return;
        }
        for(int i = 0; i<str.length(); i++) {
            char curr = str.charAt(i);
            String newchar = str.substring(0, i) + str.substring(i+1);
            printpermutations(newchar, newstr+curr, idx+1);

        }
    }
    public static void main(String[] args) {
        String str = "ABC";
        printpermutations(str, "", 0);

    }
}