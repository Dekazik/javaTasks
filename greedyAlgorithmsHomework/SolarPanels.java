/*
Задача про сонячні панелі
Умова: Встановити якнайбільше панелей, які вміщуються на даху довжиною L.
 */
public class SolarPanels {
    public static void main(String[] args) {
        int[] panels = {3, 2, 5, 1};
        int L = 7;

        //https://www.youtube.com/watch?v=ElNkA4VviQA
        //буль буль буль буль буль
        boolean isEnd = false;
        while(!isEnd){
            isEnd = true;
            for(int i = 0; i < panels.length - 1; i++) {
                int temp;
                if (panels[i] > panels[i + 1]) {
                    temp = panels[i];
                    panels[i] = panels[i + 1];
                    panels[i + 1] = temp;
                    isEnd = false;
                }
            }
        }

        int total = 0;
        int count = 0;
        for (int i = 0; i < panels.length; i++) {
            if (total + panels[i] <= L) {
                total += panels[i];
                count++;
            }
        }

        System.out.println("Установлено панелей: " + count);
    }
}
