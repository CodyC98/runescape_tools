import java.io.IOException;

public class masterworkProfit {

    private static saveHTML saver = new saveHTML();

    private static String url = "https://runescape.wiki/w/Module:Exchange/";
    private static String[] ores = {"Dark_animica", "Light_animica"};
    private static String[] bars = {null};

    public static void main(String[] args) {
        String source = null;
        String price = null;
        try {
            for(int i = 0; i < ores.length; i++){
                source = saver.savePage(url + ores[i]);
                price = findItemPrice(source);
                System.out.print(price + "\n");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        //System.out.print(source + "\n");
    }

    public static String findItemPrice(String search) {
        int num;
        String cost = null;
        boolean found = false;
        num = search.indexOf("price"); //find first occurrence
        num = search.indexOf("price", num + 1); //find second occurrence
        for(int i = num; i < search.length(); i++){
            if(!found) {
                if(Character.isDigit(search.charAt(i))) {
                    found = true;
                    cost = String.valueOf(search.charAt(i));
                }
            } else if(Character.isDigit(search.charAt(i))) {
                cost += search.charAt(i);
            } else {
                break;
            }
        }
        return cost;
    }

}