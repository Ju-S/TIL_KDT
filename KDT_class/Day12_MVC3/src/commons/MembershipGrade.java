package commons;

public enum MembershipGrade {
    Unknown(0), Silver(1), Gold(2), Topaz(3);

    private final int code;

    MembershipGrade(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static MembershipGrade fromCode(int code) {
        for (MembershipGrade m : MembershipGrade.values()) {
            if (m.code == code) {
                return m;
            }
        }
        return MembershipGrade.Unknown;
    }

    public static String getMembershipGradeList() {
        String gradeList = "";
        for (MembershipGrade m : MembershipGrade.values()) {
            if (m.getCode() == 0) {
                continue;
            }
            gradeList += m.getCode() + "." + m + " ";
        }
        return gradeList;
    }
}
