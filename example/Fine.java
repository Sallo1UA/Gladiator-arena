package org.example;

public class Fine {
    public static class Unlucky {
        public void imposeFine(Gladiator gladiator) {
            System.out.println("Штраф за невдачу накладено.");
            gladiator.subtractGold(10);
        }
    }

    public static class Coward {
        public void imposeFine(Gladiator gladiator) {
            System.out.println("Штраф за боягузтво накладено.");
            gladiator.subtractGold(15);
        }
    }

    public static class Weakling {
        public void imposeFine(Gladiator gladiator) {
            System.out.println("Штраф за слабкість накладено.");
            gladiator.subtractGold(20);
        }
    }

    public static class Heartless {
        public void imposeFine(Gladiator gladiator) {
            System.out.println("Штраф за безсердечність накладено.");
            gladiator.subtractGold(20);
        }
    }
}
