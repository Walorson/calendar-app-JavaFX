package org.example;

public class Month {
    public String name;
    public int days;
    public int index;

    /**
     * Tablica obiektów klasy Month
     */
    static public Month[] months = {
            new Month("January", 31, 0),
            new Month("February", 28, 1),
            new Month("March", 31, 2),
            new Month("April", 30, 3),
            new Month("May", 31, 4),
            new Month("June", 30, 5),
            new Month("July", 31, 6),
            new Month("August", 31, 7),
            new Month("September", 30, 8),
            new Month("October", 31, 9),
            new Month("November", 30, 10),
            new Month("December", 31, 11)
    };

    public Month(String name, int days, int index) {
        this.name = name;
        this.days = days;
        this.index = index;
    }

    /**
     * Funkcja konwertująca indeks miesiąca na liczbę rzymską
     * @return liczbę rzymską jako ciąg znaków
     */
    public String toRoman() {
        switch (this.index) {
            case 0: return "I";
            case 1: return "II";
            case 2: return "III";
            case 3: return "IV";
            case 4: return "V";
            case 5: return "VI";
            case 6: return "VII";
            case 7: return "VIII";
            case 8: return "IX";
            case 9: return "X";
            case 10: return "XI";
            case 11: return "XII";
            default: throw new IllegalArgumentException("Miesiac o takim indeksie nie istnieje.");
        }
    }

    /**
     * konwertuje nazwę miesiąca na Integer
     * @param s nazwa miesiąca
     * @return liczbę całkowitą oznaczającą cyfrę miesiąca
     */
    public static Integer parseInt(String s)
    {
        for(int i = 0; i < months.length; i++)
        {
            if(s.equals(months[i].name))
                return i + 1;
        }

        return -1;
    }
}

