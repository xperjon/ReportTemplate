package com.blinfosoft.report.domain;

/**
 *
 * @author jel
 */
public enum RowType {
    RR1,
    RR2,
    RR3,
    SR1,
    SR2,
    SR3,
    D,
    BLANK;

    public static RowType get(String typeName) {
        for (RowType rowType : RowType.values()) {
            if (rowType.name().equalsIgnoreCase(typeName)) {
                return rowType;
            }
        }
        return BLANK;
    }
}
