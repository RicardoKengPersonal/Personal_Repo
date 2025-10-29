package pt.ipp.isep.dei.domain;

public interface ClassType {

    String Class();

    public enum BuildingType {
        Telegraph,
        Telephone,
        Cafe_Small,
        Cafe_Large,
        Customs,
        Post_Office,
        Hotel_Small,
        Hotel_Large,
        Silo,
        Liquid_Storage;
    }

    public enum CargoType {
        Passenger(0),
        Mail(0),
        Coal(80),
        Iron_Ore(70),
        Steel(0),
        Cars(0),
        Vegetables(90),
        Cereals(100),
        Wool(50),
        Coffe(60),
        Rubber(40),
        Cattle(30),
        Bread(0),
        Meat(0),
        Textil(0),
        Automobile(0);

        private final int baseAmount;

        CargoType(int baseAmount) {
            this.baseAmount = baseAmount;
        }

        public int getBaseAmount() {
            return baseAmount;
        }
    }


    public enum PortBehaviour {
        IMPORT,
        EXPORT,
        IMPORT_EXPORT,
        TRANSFORM;
    }

    public enum CarriageType {
        Passenger,
        Cargo;

        public boolean supports(CargoType type) {
            return switch (this) {
                case Passenger -> type == CargoType.Passenger || type == CargoType.Mail;
                case Cargo -> type != CargoType.Passenger && type != CargoType.Mail;
            };
        }

    }

    public enum IndustryType {
        Primary,
        Transforming,
        Mixed;
    }

    public enum IndustryGenerationFactor {
        Heavy(3),
        Mid(2),
        Low(1);

        private final int generationFactor;

        IndustryGenerationFactor(int generationFactor) {
            this.generationFactor = generationFactor;
        }

        public int getGenerationFactor() {
            return generationFactor;
        }
    }

    public enum ResourceType {
        Coal(true),
        Iron(true),
        Vegetables(true),
        Cereals(true),
        Wool(true),
        Coffe(true),
        Rubber(true),
        Cattle(true),
        Steel(false),
        Bread(false),
        Meat(false),
        Textil(false),
        Automobile(false);

        private boolean flag;

        ResourceType(boolean flag) {
            this.flag = flag;
        }

        public boolean getFlag() {
            return flag;
        }
    }

    public enum ProductionBuff {
        Small(0.05),
        Mid(0.1),
        Big(0.15);

        private double factor;

        ProductionBuff(double factor) {
            this.factor = factor;
        }

        public double getProductionBuff() {
            return factor;
        }
    }

    public enum LocomotiveType {
        Steam,
        Disel,
        Eletric;
    }

    public enum CargoMode {
        Full,
        Half,
        Available;
    }

    public enum LineType {
        Single(6),
        Double(6),
        Electrified_Single(11),
        Electrified_Double(12);

        private int cost;

        LineType(int cost) {
            this.cost = cost;
        }

        public int getCost() {
            return cost;
        }

        public boolean isDoubleTrack() {
            return this == Double || this == Electrified_Double;
        }
    }


    public enum StationType {
        Depot(1000,3),
        Station(2500,4),
        Terminal(5000,5);

        private final double constructionCost;
        private final int radius;

        StationType(double constructionCost,int radius) {
            this.constructionCost = constructionCost;
            this.radius = radius;
        }

        public double getConstructionCost() {
            return constructionCost;
        }

        public int getRadius(){
            return radius;
        }
    }

    public enum Orientation {
        North,
        East,
        South,
        West;
    }

    public enum Ground {
        Grass;
    }

    public enum Building {
        None,
        City,
        House,
        Industry,
        Depot,
        Station,
        Terminal,
        Railway_left,
        Railway_right,
        Railway_turn_left_up,
        Railway_turn_left,
        Railway_turn_right_up,
        Railway_turn_right,
        Railway_intesection;
    }
}