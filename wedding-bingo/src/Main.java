import Bingo.Bingo;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        Bingo myBingo = null;
        try {
            myBingo = new Bingo();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        myBingo.addSquare("Is newly single");
        myBingo.addSquare("Is banished to the snore room (aka snore-cophagus)");
        myBingo.addSquare("Is running a 100 mile race this month");
        myBingo.addSquare("Was born in a country other than the United States");
        myBingo.addSquare("Has played field hockey with Kaitlin");
        myBingo.addSquare("Has sang karaoke with Kaitlin and/or Dan");
        myBingo.addSquare("Has lived with Kaitlin and/or Dan");
        myBingo.addSquare("Shares the same birthday as Kaitlin or Dan");
        myBingo.addSquare("Was often found cavorting with a demon named Zharkresh");
        myBingo.addSquare("Has worked with Kaitlin or Dan");
        myBingo.addSquare("Lives in Colorado");
        myBingo.addSquare("Lives outside of Colorado");
        myBingo.addSquare("Has been audibly or visually featured on 'The Basketball Jones' or 'The Starters'");
        myBingo.addSquare("Has caught over 35000 pounds of fish this summer");
        myBingo.addSquare("Has appeared on a radio show with Dan");
        myBingo.addSquare("Has helped produce a child in the last twoish years");
        myBingo.addSquare("Has been a part of a co-op or commune");
        myBingo.addSquare("Lives in their employer's basement");
        myBingo.addSquare("Went to college in the buckeye state");
        myBingo.addSquare("Has spent time in Murder-apolis Money-sota");
        myBingo.addSquare("Watched the eclipse at 99.99% totality or greater");
        myBingo.addSquare("Has traveled internationally with Kaitlin and/or Dan");
        myBingo.addSquare("Has extensively studied Latin and/or ancient Greek grammar");
        myBingo.addSquare("Loves to drink Zima");
        myBingo.addSquare("Lives or has lived in New England");
        myBingo.addSquare("Is a prolific sweater (i.e. one who sweats a lot)");
        myBingo.addSquare("First met Kaitlin under unusual circumstances");
        myBingo.addSquare("Has lived in a state that touches an ocean");
        myBingo.addSquare("Has made their own alcoholic beverages");
        myBingo.addSquare("Is from Fort Collins");
        myBingo.addSquare("Was married on the fourth of July");
        myBingo.addSquare("Has gone camping this summer");
        myBingo.addSquare("Was or currently is in a band");

        try {
            myBingo.run();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
