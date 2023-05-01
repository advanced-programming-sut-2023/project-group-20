package enums;

public enum TroopTypes {
    Archer(10);
    int hitpoint;

    TroopTypes(int hitpoint) {
        this.hitpoint = hitpoint;
    }
}
