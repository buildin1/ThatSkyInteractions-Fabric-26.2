package net.quepierts.thatskyinteractions.common.block;

public enum CandleType {
    STANDARD,
    FRAMED_STANDARD,
    T_3X3,
    T_4X4,
    T_5X5A,
    T_5X5B,
    T_6X6A,
    T_6X6B,
    T_8X8,
    FRAMED_T_3X3,
    FRAMED_T_4X4,
    FRAMED_T_5X5A,
    FRAMED_T_5X5B,
    FRAMED_T_6X6A,
    FRAMED_T_6X6B,
    FRAMED_T_8X8;

    private final String serializedName;

    CandleType() {
        this.serializedName = name().toLowerCase();
    }

    public String getSerializedName() {
        return serializedName;
    }
}
