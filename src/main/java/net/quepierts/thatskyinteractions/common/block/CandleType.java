package net.quepierts.thatskyinteractions.common.block;

public enum CandleType {
    STANDARD(2, 8, false),
    FRAMED_STANDARD(2, 8, true),
    T_3X3(3, 9, false),
    FRAMED_T_3X3(3, 9, true),
    T_4X4(4, 8, false),
    FRAMED_T_4X4(4, 8, true),
    T_5X5A(5, 10, false),
    FRAMED_T_5X5A(5, 10, true),
    T_5X5B(5, 12, false),
    FRAMED_T_5X5B(5, 12, true),
    T_6X6A(6, 16, false),
    FRAMED_T_6X6A(6, 16, true),
    T_6X6B(6, 18, false),
    FRAMED_T_6X6B(6, 18, true),
    T_8X8(8, 22, false),
    FRAMED_T_8X8(8, 22, true);

    private final int size;
    private final int height;
    private final boolean framed;
    private final String serializedName;

    CandleType(int size, int height, boolean framed) {
        this.size = size;
        this.height = height;
        this.framed = framed;
        this.serializedName = name().toLowerCase();
    }

    public int getSize() {
        return size;
    }

    public int getHeight() {
        return height;
    }

    public boolean isDoubleBlock() {
        return height > 16;
    }

    public boolean isFramed() {
        return framed;
    }

    public String getSerializedName() {
        return serializedName;
    }
}
