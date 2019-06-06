package e.mi.work1;

public class IntToWords {
    private static final String dig1[][] = {{"одна", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
            {"один", "два"}}; //dig[0] - female, dig[1] - male
    private static final String dig10[]  = {"десять","одиннадцать", "двенадцать", "тринадцать", "четырнадцать",
            "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};
    private static final String dig20[]  = {"двадцать", "тридцать", "сорок", "пятьдесят",
            "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
    private static final String dig100[] = {"сто","двести", "триста", "четыреста", "пятьсот",
            "шестьсот", "семьсот", "восемьсот", "девятьсот"};
    private static final String leword[][] = { {"", "", "", "0"},
            {"", "", "", "1"},
            {"тысяча", "тысячи", "тысяч", "0"},
            {"миллион", "миллиона", "миллионов", "1"},
            {"миллиард", "миллиарда", "миллиардов", "1"},
            {"триллион", "триллиона", "триллионов", "1"}};

    //рекурсивная функция преобразования целого числа num в рубли
    public static String num2words(long num, int level) {
        StringBuilder words = new StringBuilder(50);
        if (num==0) words.append("ноль ");         //исключительный случай
        int sex = leword[level][3].indexOf("1")+1; //не красиво конечно, но работает
        int h = (int)(num%1000);    //текущий трехзначный сегмент
        int d = h/100;              //цифра сотен
        if (d>0) words.append(dig100[d-1]).append(" ");
        int n = h%100;
        d = n/10;                   //цифра десятков
        n = n%10;                   //цифра единиц
        switch(d) {
            case 0: break;
            case 1: words.append(dig10[n]).append(" ");
                break;
            default: words.append(dig20[d-2]).append(" ");
        }
        if (d==1) n=0;              //при двузначном остатке от 10 до 19, цифра едициц не должна учитываться
        switch(n) {
            case 0: break;
            case 1:
            case 2: words.append(dig1[sex][n-1]).append(" ");
                break;
            default: words.append(dig1[0][n-1]).append(" ");
        }
        switch(n) {
            case 1: words.append(leword[level][0]);
                break;
            case 2:
            case 3:
            case 4: words.append(leword[level][1]);
                break;
            default: if((h!=0)||((h==0)&&(level==1)))  //если трехзначный сегмент = 0, то добавлять нужно только "рублей"
                words.append(leword[level][2]);
        }
        long nextnum = num/1000;
        if(nextnum>0) {
            return (num2words(nextnum, level+1) + " " + words.toString()).trim();
        } else {
            return words.toString().trim();
        }
    }


}