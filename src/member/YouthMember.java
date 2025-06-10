package member;

public class YouthMember extends Member {
    public static final double DISCOUNT_RATE = 0.5;
    private AdultMember guardian;

    public YouthMember(int age, String name, PlanType planType, AdultMember guardian) {
        super(age, name, planType);
        this.guardian = guardian;
        guardian.addChild(this);
    }

    public double calculateBill() {
        double base;
        switch (planType) {
            case BIWEEKLY_BASE:
                base = BIWEEKLY_BASE;
                break;
            case MONTHLY_BASE:
                base = MONTHLY_BASE;
                break;
            case ANNUAL_BASE:
                base = ANNUAL_BASE;
                break;
            default:
                base = 0.0;
                break;
        }
        return base * (1 - DISCOUNT_RATE);
    }

    public void printBill() {
        super.printBill();
    }

    public String toString() {
        return super.toString();
    }

    public AdultMember getGuardian() {
        return guardian;
    }

    public void setGuardian(AdultMember guardian) {
        this.guardian = guardian;
    }
}
