    public String convertToTitle(int n) {
        StringBuffer sb = new StringBuffer();
        while(n != 0) {
                n = n%26;
                sb.append(0, getChar(n));
                n = n / 26;
        }
        return sb.toString();
    }
    public char getChar(int n) {
        char c = (char) (n + 64);
        return c;
    }
